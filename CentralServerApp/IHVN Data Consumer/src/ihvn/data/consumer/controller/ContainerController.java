/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.controller;

import ihvn.data.consumer.model.dao.ErrorDAO;
import ihvn.data.consumer.model.dao.FacilityDAO;
import ihvn.data.consumer.model.dao.Misc;
import ihvn.data.consumer.model.dao.PatientBiometricDAO;
import ihvn.data.consumer.model.dao.PatientDAO;
import ihvn.data.consumer.model.dao.PatientIdentifierDAO;
import ihvn.data.consumer.model.dao.PatientProgramDAO;
import ihvn.data.consumer.model.dao.VisitDAO;
import ihvn.data.consumer.model.xml.Container;
import ihvn.data.consumer.model.xml.DemographicsType;
import ihvn.data.consumer.model.xml.MessageDataType;
import ihvn.data.consumer.model.xml.MessageHeaderType;
import ihvn.data.consumer.model.xml.ObsType;
import ihvn.data.consumer.model.xml.PatientBiometricType;
import ihvn.data.consumer.model.xml.PatientIdentifierType;
import ihvn.data.consumer.model.xml.PatientProgramType;
import ihvn.data.consumer.model.xml.VisitType;
import ihvn.data.consumer.models.Patient;
import ihvn.data.consumer.models.Radet;
import ihvn.data.consumer.models.ValidationError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;

/**
 *
 * @author rsuth
 */
public class ContainerController {
    
    private int facilityId = 0;
    private String datimId = "";
    private String messageUUID = "";
    private Container container;
    private String patientUUID = "";
    
    public static Map<String, Map<String, String>> validationData = new HashMap<>();
    public static Map<String, JSONObject> lookupData = new HashMap<>();
    //so i had to add and extra map, to avoid the concurrent modification exception. What was happening is that because 
    //we are multithreading, while looping through the list/map of errors, the map is also growing at tbe same time because another patient is adding to it
    //to solve this, we add an outer map whose index will be the patient uuid.
    public static Map<String, Map<String, Map<String, JSONObject>>> encounterValidationData = new HashMap<>();
    public static Map<String, Map<String, Map<String, String>>> visitValidationData = new HashMap<>();
    
    //list of patients and radet for analytic table
    public static Map<String, Patient>allPatients = new HashMap<>();//key is patient uuid
    public static Map<String, Radet>allRadet = new HashMap<>();//key is patient uuid
    //visit 
    public void saveContainer(Container cnt)
    {
        this.container = cnt; 
        MessageHeaderType messageHeader = this.container.getMessageHeader(); 
        MessageDataType messageData = this.container.getMessageData();
        //get the facility
        patientUUID = messageData.getDemographics().getPatientUuid();
        datimId = messageHeader.getFacilityDatimCode();
        messageUUID = messageHeader.getMessageUniqueID();
        
        facilityId = 0;//FacilityDAO.getFacilityWithDatimCode(messageHeader.getFacilityDatimCode());
        
        //save patient demographics details
        DemographicsType demo = messageData.getDemographics();
        
        //patientUUID = PatientDAO.getPatientIdWithUUID(demo.getPatientUUID());
        
        //setup patient validation
        Validator.setupPatientValidationData(patientUUID);
        
        //validate patient
        Validator.validatePatient(demo);
        Validator.validateProgram(demo, messageData.getPatientPrograms());
        Validator.validatePEPFARID(demo, messageData.getPatientIdentifiers());
        //Validate
        PatientDAO.insertOrUpdatePatient(demo, datimId, messageUUID);
        
        //save patient biometrics
        if(this.container.getMessageData().getPatientBiometrics() != null && this.container.getMessageData().getPatientBiometrics().size() > 0){
             this.savePatientBiometrics();
             
             //that biometric is captured
             ContainerController.allPatients.get(demo.getPatientUuid()).setBiometricCaptured("yes");
             ContainerController.allPatients.get(demo.getPatientUuid()).setBiometricCaptureDate(new DateTime(this.container.getMessageData().getPatientBiometrics().get(0).getDateCreated().toGregorianCalendar()));
        }
       
        //save the patient identifiers
        this.savePatientIdentifiers();
        
        //save the patient programs
        this.savePatientPrograms();
        
        //save visits
        this.saveVisits();
        
        //we can calculate the art status at this point
        String artStatus = Misc.getARTStatus(ContainerController.allPatients.get(demo.getPatientUuid()), ContainerController.allRadet.get(demo.getPatientUuid()));
        ContainerController.allRadet.get(demo.getPatientUuid()).setCurrentArtStatus(artStatus);
        //save any errors we might have noticed
        this.saveError();
        
        // we can go ahead and save the patient and 
        
        
        //we need to nullify stuff for this patient
        
        
        
    }
    
    
    private void savePatientBiometrics()
    {
        List<PatientBiometricType> biometrics = this.container.getMessageData().getPatientBiometrics();
        //first delete any existing biometrics record for ths patient 
        //PatientBiometricDAO.deleteExisitingBiometrics(patientId);
        //now insert the biometrics afresh
        PatientBiometricDAO.savePatientBiometrics(patientUUID, datimId, messageUUID,  biometrics);
    }
    
    private void savePatientIdentifiers()
    {
        List<PatientIdentifierType> identifiers = this.container.getMessageData().getPatientIdentifiers();
        //PatientIdentifierDAO.deleteExisitingIdentifiers(patientId);
        PatientIdentifierDAO.savePatientIdentifiers(datimId, messageUUID, identifiers);
    }
    private void savePatientPrograms()
    {
        List<PatientProgramType> programs = this.container.getMessageData().getPatientPrograms();
        PatientProgramDAO.savePatientProgram(datimId, messageUUID, programs);
        
    }
    
    private void saveVisits()
    {
        List<VisitType> allVisits = this.container.getMessageData().getVisits();
        //VisitDAO.deleteVisits(patientId);
      
        //long firstVisitId = VisitDAO.saveVisits(patientId, facilityId, allVisits);
        VisitDAO.saveVisits(this.container.getMessageData().getDemographics(), datimId, messageUUID, allVisits);

        // save the encounters
        String patientUUID = this.container.getMessageData().getDemographics().getPatientUuid();
        long firstEncounterId = VisitDAO.saveEncounters(this.container.getMessageData().getDemographics(), patientUUID, datimId, messageUUID, this.container.getMessageData().getEncounters());

        //save obs
        VisitDAO.saveObs(datimId, messageUUID, this.container.getMessageData().getObs());  
    }
    
   private void saveError()
   {
       List<ValidationError> errorList = new ArrayList<>();
       //get this patient error data
       Map<String, String> patientValidationData = ContainerController.validationData.get(this.container.getMessageData().getDemographics().getPatientUuid());
       Iterator hmIterator = patientValidationData.entrySet().iterator(); 
  
        // Iterate through the hashmap 
        // and add some bonus marks for every student 
        
        while (hmIterator.hasNext()) { 
            Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
            ValidationError temp = new ValidationError();
            temp.setDatimID(datimId);
            temp.setEncounterUUID("");
            temp.setErrorCode("");
            temp.setErrorId(0);
            temp.setErrorMessage(mapElement.getValue().toString());
            temp.setIgnoreForm(0);
            temp.setIgnorePatient(0);
            temp.setIgnoreVariable(0);
            temp.setMessageUUID(messageUUID);
            temp.setObsUUID("");
            temp.setPatientUUID(patientUUID);
            temp.setPmmForm("");
            temp.setVariableName("");
            temp.setVariableValue("");
            temp.setVisitDate(null);
            temp.setVisitUUID("");
            errorList.add(temp);
        } 
        //once done, remote the patient validation data from the map
        ContainerController.validationData.remove(this.container.getMessageData().getDemographics().getPatientUuid());
        
        
        //next up, loop through visit data and add to validation list
         //Iterator vIterator = ContainerController.visitValidationData.entrySet().iterator();
         Map<String, Map<String, String>> patientVisitValidation = ContainerController.visitValidationData.get(patientUUID);
         for(Map.Entry<String,Map<String, String>> visitValidation : patientVisitValidation.entrySet())
         {
            String validationKey = visitValidation.getKey();
            if(validationKey.contains(patientUUID))
            {
                 //split the key to get the visit uuid
                String [] splitKeys = validationKey.split("__");
                for(Map.Entry<String, String> visitErrors : visitValidation.getValue().entrySet())
                {
                    ValidationError temp = new ValidationError();
                    temp.setDatimID(datimId);
                    temp.setEncounterUUID("");
                    temp.setErrorCode("");
                    temp.setErrorId(0);
                    temp.setErrorMessage(visitErrors.getValue());
                    temp.setIgnoreForm(0);
                    temp.setIgnorePatient(0);
                    temp.setIgnoreVariable(0);
                    temp.setMessageUUID(messageUUID);
                    temp.setObsUUID("");
                    temp.setPatientUUID(patientUUID);
                    temp.setPmmForm("");
                    temp.setVariableName("");
                    temp.setVariableValue("");
                    temp.setVisitDate(null);
                    temp.setVisitUUID(splitKeys[1]);
                    errorList.add(temp);
                }
            }
            //remove the encounter validation from the map
            
         }
         ContainerController.visitValidationData.remove(patientUUID);
         
         
         Map<String,Map<String, JSONObject>> patientEncounterValidation = ContainerController.encounterValidationData.get(patientUUID);
         //encounter validation
         for(Map.Entry<String,Map<String, JSONObject>> encounterValidation : patientEncounterValidation.entrySet())
         {
            String validationKey = encounterValidation.getKey();
            if(validationKey.contains(patientUUID))
            {
                 //split the key to get the visit uuid
                String [] splitKeys = validationKey.split("__");
                for(Map.Entry<String, JSONObject> encounterErrors : encounterValidation.getValue().entrySet())
                {
                    JSONObject errors = encounterErrors.getValue();
                    String errorMessage = (String)errors.get("error");
                    String obsUUID = "";
                    if(errors.containsKey("obs"))
                    {
                        obsUUID = ((ObsType)errors.get("obs")).getObsUuid();
                    }
                    ValidationError temp = new ValidationError();
                    temp.setDatimID(datimId);
                    temp.setEncounterUUID(splitKeys[1]);
                    temp.setErrorCode("");
                    temp.setErrorId(0);
                    temp.setErrorMessage(errorMessage);
                    temp.setIgnoreForm(0);
                    temp.setIgnorePatient(0);
                    temp.setIgnoreVariable(0);
                    temp.setMessageUUID(messageUUID);
                    temp.setObsUUID(obsUUID);
                    temp.setPatientUUID(patientUUID);
                    temp.setPmmForm("");
                    temp.setVariableName("");
                    temp.setVariableValue("");
                    temp.setVisitDate(null);
                    temp.setVisitUUID("");
                    errorList.add(temp);
                }
                 
            }
            
         }
         
         //remove the patient error data
         ContainerController.encounterValidationData.remove(patientUUID);
          
         //lets even see how many validation data we have at this point
        System.out.println("Patient Error list "+errorList.size());
        //lets save the error
        ErrorDAO.saveErrors(patientUUID, datimId, messageUUID, errorList);
        
          
        
          
      
   }
    
    
}
