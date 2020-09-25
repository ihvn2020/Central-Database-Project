/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.controller;

import ihvn.data.consumer.model.dao.Misc;
import ihvn.data.consumer.model.xml.DemographicsType;
import ihvn.data.consumer.model.xml.EncounterType;
import ihvn.data.consumer.model.xml.ObsType;
import ihvn.data.consumer.model.xml.PatientIdentifierType;
import ihvn.data.consumer.model.xml.PatientProgramType;
import ihvn.data.consumer.model.xml.VisitType;
import ihvn.data.consumer.models.Constant;
import ihvn.data.consumer.models.Constant;
import ihvn.data.consumer.models.Patient;
import ihvn.data.consumer.models.Radet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;

/**
 *
 * @author lordmaul
 */
public class Validator {
    
    public static void setupPatientValidationData(String patientUUID)
    {
        Map<String, String> patientValidationData = new HashMap<>();
        Map<String, Map<String, String>> visitValData = new HashMap<>();
        Map<String, Map<String, JSONObject>> encounterValData = new HashMap<>();
        patientValidationData.put(Constant.GENDER_MISSING, "Gender cannot be missing");
        patientValidationData.put(Constant.INVALID_GENDER, "Gender can only be 'M' and 'F'");
        patientValidationData.put(Constant.BIRTHDATE_EMPTY, "Birthdate cannot be empty");
        patientValidationData.put(Constant.BIRTHDATE_IN_FUTURE, "Birthdate cannot be in the future");
        patientValidationData.put(Constant.AGE_ABOVE_200, "Current Age cannot be more than 200");
        patientValidationData.put(Constant.NO_PROGRAM, "All patient must be enrolled into at least one program");
        patientValidationData.put(Constant.NO_PEPFAR_ID, "All patients should have PepfarID");
        ContainerController.validationData.put(patientUUID, patientValidationData);
        ContainerController.visitValidationData.put(patientUUID, visitValData);
         ContainerController.encounterValidationData.put(patientUUID, encounterValData);
        //add whatever we need to the lookup data
        //ContainerController
        
        //setup a few other things here
        JSONObject obj = new JSONObject();
        obj.put("encounters", new JSONObject());
        
        //lets also create keys for date lookups
        obj.put("lastPickupDate", null);
        obj.put("lastDaysOfARVRefill", null);
        obj.put("initialFirstLineRegimen", null);
        obj.put("initialSecondLineRegimen", null);
        obj.put("initialRegimen", null);
        obj.put("currRegimen", null);
        obj.put("currFirstLineRegimen", null);
        obj.put("currSecondLineRegimen", null);
        obj.put("pregnancyStatusDate", null);
        obj.put("currVlDate", null);
        obj.put("vlSampleCollectionDate", null);
        obj.put("vlReportDate", null);
        obj.put("vlIndicationDate", null);
        //obj.put("currARTStatusDate", null);
        obj.put("transferOutDate", null);
        //obj.put("deathDate", null);
        obj.put("currWeightDate", null);
        obj.put("tbStatusDate", null);
        obj.put("tbSt", null);
        obj.put("inhStartDate", null);
        obj.put("inhStopDate", null);
        obj.put("tbTreatmentStartDate", null);
        obj.put("tbTreatmentStopDate", null);
        obj.put("lastVLSampleCollectionFormDate", null);
        ContainerController.lookupData.put(patientUUID, obj);
        
    }
    
    public static void setupVisitValidation(String patientUUID, String visitUUID)
    {
        Map<String, String> visitValidationData = new HashMap<>();
        visitValidationData.put(Constant.VISIT_START_DATE_IN_FUTURE, "date started cannot be in the future");
        visitValidationData.put(Constant.VISIT_START_DATE_MISSING, "date started cannot be empty");
        visitValidationData.put(Constant.VISIT_START_DATE_EARLIER_THAN_DOB, "date started cannot be earlier than birthdate");
        visitValidationData.put(Constant.VISIT_START_DATE_EARLIER_THAN_1990, "date_started cannot be earlier than 1990");
        visitValidationData.put(Constant.VISIT_END_DATE_MISSING, "date ended cannot be empty");
        visitValidationData.put(Constant.VISIT_END_DATE_IN_FUTURE, "date ended cannot be in the future");
        visitValidationData.put(Constant.VISIT_START_DATE_EARLIER_THAN_1990, "date ended cannot be earlier than 1990");
        visitValidationData.put(Constant.VISIT_START_DATE_EARLIER_THAN_DOB, "date ended cannot be earlier than birthdate");
        //get the patient visit validation data 
         Map<String, Map<String, String>> visitValData = ContainerController.visitValidationData.get(patientUUID);
         visitValData.put(patientUUID+"__"+visitUUID, visitValidationData);
        ContainerController.visitValidationData.put(patientUUID, visitValData);
        
        
    }
    
    public static void setEncounterValidation(String patientUUID, EncounterType encounter)
    {
        JSONObject obj ;
        
        Map<String, JSONObject> encounterValidationData = new HashMap<>();
        obj = new JSONObject();
        obj.put("error", "Encounter date time cannot be in the future");
        encounterValidationData.put(Constant.ENCOUNTER_DATE_IN_FUTURE, obj );
        obj = new JSONObject();
        obj.put("error", "Encounter date time cannot be missing");
        encounterValidationData.put(Constant.ENCOUNTER_DATE_EMPTY, obj);
        obj = new JSONObject();
        obj.put("error", "Encounter date time cannot be earlier than Birthdate");
        encounterValidationData.put(Constant.ENCOUNTER_DATE_EARLIER_THAN_DOB, obj);
        //get the patient encounter validation data
        
        Map<String, Map<String, JSONObject>> encValidationData = ContainerController.encounterValidationData.get(patientUUID);
        encValidationData.put(patientUUID+"__"+encounter.getEncounterUuid(), encounterValidationData);
        ContainerController.encounterValidationData.put(patientUUID, encValidationData);
        
        JSONObject patientLookupData = ContainerController.lookupData.get(patientUUID);
        JSONObject encounterObjs = (JSONObject)patientLookupData.get("encounters");//get the encounters and add a new entry to it
        encounterObjs.put(encounter.getEncounterUuid(), encounter);
        patientLookupData.put("encounters", encounterObjs);
        ContainerController.lookupData.put(patientUUID, patientLookupData);
        
        
    }
    
    public static void setupPharmacyEncounterValidation(String patientUUID, String encounterUUID)
    { 
        Map<String, Map<String, JSONObject>> encValidationData = ContainerController.encounterValidationData.get(patientUUID);
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = encValidationData.get(patientUUID+"__"+encounterUUID);
        obj = new JSONObject();
        obj.put("error", "Current Regimen Line is missing in Pharmacy forms");
        encounterValidationData.put(Constant.CURRENT_REGIMEN_LINE_MISSING, obj );
        obj = new JSONObject();
        obj.put("error", "Adult or Pediatric Regimen Line is missing");
        encounterValidationData.put(Constant.ADULT_PED_REGIMEN_LINE_MISSING, obj);
        obj = new JSONObject();
        obj.put("error", "Medication duration for an ARV Medication cannot be empty");
        encounterValidationData.put(Constant.MEDICATION_DURATION_EMPTY, obj);
        obj = new JSONObject();
        obj.put("error", "Medication duration cannot be more than 6 months");
        encounterValidationData.put(Constant.MEDICATION_DURATION_MORE_THAN_180_DAYS, obj);
        obj = new JSONObject();
        obj.put("error", "Return Visit Date is missing in Pharmacy Form");
        encounterValidationData.put(Constant.RETURN_VISIT_DATE_MISSING, obj );
        
        
        encValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData);
        ContainerController.encounterValidationData.put(patientUUID, encValidationData);
        
         
    }
    public static void setupLabEncounterValidation(String patientUUID, String encounterUUID){
        
        Map<String, Map<String, JSONObject>> encValidationData = ContainerController.encounterValidationData.get(patientUUID);
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = encValidationData.get(patientUUID+"__"+encounterUUID);
        obj = new JSONObject();
        obj.put("error", "Ordered date is missing");
        encounterValidationData.put(Constant.ORDER_DATE_MISSING, obj);
        obj = new JSONObject();
        obj.put("error", "Ordered date in the future");
        encounterValidationData.put(Constant.ORDER_DATE_IN_FUTURE, obj);
        
        obj = new JSONObject();
        obj.put("error", "Reported date cannot be before Ordered date");
        encounterValidationData.put(Constant.REPORTED_DATE_BEFORE_ORDER_DATE, obj);
        obj = new JSONObject();
        obj.put("error", "Reported date cannot be in the future");
        encounterValidationData.put(Constant.REPORTED_DATE_IN_FUTURE, obj);
        obj = new JSONObject();
        obj.put("error", "Reported date is missing");
        encounterValidationData.put(Constant.REPORTED_DATE_MISSING, obj);
       // ContainerController.encounterValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData); 
       encValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData);
       ContainerController.encounterValidationData.put(patientUUID, encValidationData);
    }
    public static void setupClinicalEncounterValidation(String patientUUID, String encounterUUID)
    {
        Map<String, Map<String, JSONObject>> encValidationData = ContainerController.encounterValidationData.get(patientUUID);
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = encValidationData.get(patientUUID+"__"+encounterUUID);
         obj = new JSONObject();
        obj.put("error", "Weight is out of range (more than 250kg)");
        encounterValidationData.put(Constant.WEIGHT_OUT_OF_RANGE, obj );
        obj = new JSONObject();
        obj.put("error", "Weight is too small for Adult (below 10kg)");
        encounterValidationData.put(Constant.WEIGHT_TOO_SMALL_ADULT, obj);
        obj = new JSONObject();
        obj.put("error", "Height is out of range (more than 400cm)");
        encounterValidationData.put(Constant.HEIGHT_OUT_OF_RANGE, obj );
        obj = new JSONObject();
        obj.put("error", "Height is too small for Adult (below 10cm)");
        encounterValidationData.put(Constant.HEIGHT_TOO_SMALL_ADULT, obj);
        obj = new JSONObject();
        obj.put("error", "WHO Staging is missing");
        encounterValidationData.put(Constant.WHO_STAGE_MISSING, obj);
        obj = new JSONObject();
        obj.put("error", "TBStatus is missing");
        encounterValidationData.put(Constant.TB_STATUS_MISSING, obj);
        //ContainerController.encounterValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData);
       encValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData);
       ContainerController.encounterValidationData.put(patientUUID, encValidationData);
    }
    
    public static void setupAdultPedInitialEvaluationForm(String patientUUID, String encounterUUID)
    {
        Map<String, Map<String, JSONObject>> encValidationData = ContainerController.encounterValidationData.get(patientUUID);
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = encValidationData.get(patientUUID+"__"+encounterUUID);
        obj = new JSONObject();
        obj.put("error", "Weight is out of range (more than 250kg)");
        encounterValidationData.put(Constant.WEIGHT_OUT_OF_RANGE, obj );
        obj = new JSONObject();
        obj.put("error", "Weight is too small for Adult (below 10kg)");
        encounterValidationData.put(Constant.WEIGHT_TOO_SMALL_ADULT, obj);
        obj = new JSONObject();
        obj.put("error", "Height is out of range (more than 400cm)");
        encounterValidationData.put(Constant.HEIGHT_OUT_OF_RANGE, obj );
        obj = new JSONObject();
        obj.put("error", "Height is too small for Adult (below 10cm)");
        encounterValidationData.put(Constant.HEIGHT_TOO_SMALL_ADULT, obj);
        obj = new JSONObject();
        obj.put("error", "WHO Staging is missing");
        encounterValidationData.put(Constant.WHO_STAGE_MISSING, obj);
        obj = new JSONObject();
        obj.put("error", "TBStatus is missing");
        encounterValidationData.put(Constant.TB_STATUS_MISSING, obj);
        //ContainerController.encounterValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData); 
       encValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData);
       ContainerController.encounterValidationData.put(patientUUID, encValidationData);
    }
    public static void setupEnrollmentFormEncounterValidation(String patientUUID, EncounterType encounter)
    {
        Map<String, Map<String, JSONObject>> encValidationData = ContainerController.encounterValidationData.get(patientUUID);
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = encValidationData.get(patientUUID+"__"+encounter.getEncounterUuid());
        obj = new JSONObject();
        obj.put("error", "Date of HIV diagnosis is missing");
        encounterValidationData.put(Constant.DIAGNOSIS_DATE_MISSING, obj);
        obj = new JSONObject();
        obj.put("error","Date of HIV diagnosis earlier than birthdate");
        encounterValidationData.put(Constant.DIAGNOSIS_DATE_EARLIER_THAN_DOB, obj);
        obj = new JSONObject();
        obj.put("error", "Date of HIV diagnosis in the future");
        encounterValidationData.put(Constant.DIAGNOSIS_DATE_IN_FUTURE, obj);
        obj = new JSONObject();
        obj.put("error", "Missing Enrollment date");
        encounterValidationData.put(Constant.ENROLLMENT_DATE_MISSING, obj);
        obj = new JSONObject();
        obj.put("error", "Enrollment date in the future ");
        encounterValidationData.put(Constant.ENROLLMENT_DATE_IN_FUTURE, obj);
        
        obj = new JSONObject();
        obj.put("error", "Missing Method of enrollment");
        encounterValidationData.put(Constant.MISSING_ENROLLMENT_METHOD, obj);
        
        
        encValidationData.put(patientUUID+"__"+encounter.getEncounterUuid(), encounterValidationData);
       ContainerController.encounterValidationData.put(patientUUID, encValidationData);
        //ContainerController.encounterValidationData.put(patientUUID+"__"+encounter.getEncounterUuid(), encounterValidationData); 
        
        //we also want to add the hiv enrollment date to lookup data
        JSONObject patientLookupData = ContainerController.lookupData.get(patientUUID);
        patientLookupData.put(Constant.HIV_ENROLLMENT_DATE, encounter.getEncounterDatetime());
        ContainerController.lookupData.put(patientUUID, patientLookupData);
    }
    
    public static void setupArtCommencementForm(String patientUUID, String encounterUUID)
    {
        //JSONObject obj ;
        //Map<String, JSONObject> encounterValidationData = ContainerController.encounterValidationData.get(patientUUID+"__"+encounterUUID);
        Map<String, Map<String, JSONObject>> encValidationData = ContainerController.encounterValidationData.get(patientUUID);
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = encValidationData.get(patientUUID+"__"+encounterUUID);
        obj = new JSONObject();
        obj.put("error", "ART Start Date is missing");
        encounterValidationData.put(Constant.ART_START_DATE_MISSING, obj);
        obj = new JSONObject();
        obj.put("error", "ART Start Date in the future");
        encounterValidationData.put(Constant.ART_START_DATE_IN_FUTURE, obj);
        obj = new JSONObject();
        obj.put("error", "ART Start Date earlier than birthdate");
        encounterValidationData.put(Constant.ART_START_DATE_EARLIER_THAN_DOB, obj);
        obj = new JSONObject();
        obj.put("error", "ART Start Date earlier than date_enrolled");
        encounterValidationData.put(Constant.ART_START_DATE_EARLIER_THAN_ENROLLMENT_DATE, obj);
        
        encValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData);
       ContainerController.encounterValidationData.put(patientUUID, encValidationData);
    }
    
    public static void setupPriorARTValidation(String patientUUID, String encounterUUID)//if prior art is transferred in with records
    {
        Map<String, Map<String, JSONObject>> encValidationData = ContainerController.encounterValidationData.get(patientUUID);
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = encValidationData.get(patientUUID+"__"+encounterUUID);
        obj = new JSONObject();
        obj.put("error", "Date Transferred in invalid");
        encounterValidationData.put(Constant.TRANSFER_DATE_MISSING, obj);
         obj = new JSONObject();
        obj.put("error", "Date Transferred in cannot be in the future");
        encounterValidationData.put(Constant.TRANSFER_DATE_IN_FUTURE, obj);
         obj = new JSONObject();
        obj.put("error", "Facility Transferred from is missing");
        encounterValidationData.put(Constant.FAC_TRANSFERRED_FROM_MISSING, obj);
         encValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData);
        ContainerController.encounterValidationData.put(patientUUID, encValidationData);
        //ContainerController.encounterValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData);
    }
    
    public static void validatePatient(DemographicsType patientDemo)
    {
        
        
        //get the patient validator
        Map<String, String> patientValidator = ContainerController.validationData.get(patientDemo.getPatientUuid());
        if(patientDemo.getGender() != null)
        {
            patientValidator.remove(Constant.GENDER_MISSING);
            if(patientDemo.getGender().equalsIgnoreCase("F") || patientDemo.getGender().equalsIgnoreCase("M"))
            {
                patientValidator.remove(Constant.INVALID_GENDER);
            }
        }
        else{
            patientValidator.remove(Constant.INVALID_GENDER);//if no gender is supplied, it cannot be invalid
        }
        if(patientDemo.getBirthdate() != null)
        {
            //put this in look up data
            JSONObject obj = ContainerController.lookupData.get(patientDemo.getPatientUuid());
            obj.put("dob", patientDemo.getBirthdate());
            ContainerController.lookupData.put(patientDemo.getPatientUuid(), obj);
            
            patientValidator.remove(Constant.BIRTHDATE_EMPTY);
            if(!Misc.isInFuture(patientDemo.getBirthdate().toGregorianCalendar()))
            {
                patientValidator.remove(Constant.BIRTHDATE_IN_FUTURE);
                
                //if patient birthdate is correct, then we can add this patient to patient and radet map
                if(patientDemo.getVoided() == 0){
                    Patient p = Patient.newInstance(patientDemo);
                    Radet r = Radet.newInstance(patientDemo);
                    ContainerController.allPatients.put(patientDemo.getPatientUuid(), p);
                    ContainerController.allRadet.put(patientDemo.getPatientUuid(), r);
                }
                
            }
            if(!Misc.isGreaterThan200(patientDemo.getBirthdate().toGregorianCalendar()))
            {
                 patientValidator.remove(Constant.AGE_ABOVE_200);
            }
            
        }
        else
        {
             patientValidator.remove(Constant.BIRTHDATE_IN_FUTURE);
             patientValidator.remove(Constant.AGE_ABOVE_200);
        }
        
        ContainerController.validationData.put(patientDemo.getPatientUuid(), patientValidator);
        
    }
    
    
    public static void validateProgram(DemographicsType patientDemo, List<PatientProgramType> allPrograms)
    {
        Map<String, String> patientValidator = ContainerController.validationData.get(patientDemo.getPatientUuid());
            
        if(allPrograms.size() > 0)
        {
            patientValidator.remove(Constant.NO_PROGRAM);
            //check if the art program is here
            boolean onART = false;
            for(int i=0; i<allPrograms.size(); i++)
            {
                if(allPrograms.get(i).getProgramId() == 1)
                {
                    onART = true;
                }
            }
            if(!onART)
            {
                ContainerController.allRadet.get(patientDemo.getPatientUuid()).setCurrentArtStatus("Not on ART");
            }
        }
        
        ContainerController.validationData.put(patientDemo.getPatientUuid(), patientValidator);
    }
    
    public static void validatePEPFARID(DemographicsType patientDemo, List<PatientIdentifierType> identifiers)
    {
        Map<String, String> patientValidator = ContainerController.validationData.get(patientDemo.getPatientUuid());
        for(int i=0; i<identifiers.size(); i++)
        {
            if(identifiers.get(i).getIdentifierType() == 4)
            {
                
                patientValidator.remove(Constant.NO_PEPFAR_ID);
                //lets also add the art number/pepfar id to the patient
                ContainerController.allPatients.get(patientDemo.getPatientUuid()).setPatientUniqueID(identifiers.get(i).getIdentifier());
            }
            if(identifiers.get(i).getIdentifierType() == 1)//omrs id
            {
                ContainerController.allPatients.get(patientDemo.getPatientUuid()).setPatientOMRSID(identifiers.get(i).getIdentifier());
            }
            if(identifiers.get(i).getIdentifierType() == 5)
            {
                ContainerController.allPatients.get(patientDemo.getPatientUuid()).setHospitalID(identifiers.get(i).getIdentifier());
            }
            
            
        }
        ContainerController.validationData.put(patientDemo.getPatientUuid(), patientValidator);
    }
    
    
    
    public static void validateVisit(DemographicsType patientDemo, VisitType visit)
    {
         Map<String, Map<String, String>> patientVisitValidationData = ContainerController.visitValidationData.get(patientDemo.getPatientUuid());
         Map<String, String> visitValidator = patientVisitValidationData.get(patientDemo.getPatientUuid()+"__"+visit.getVisitUuid());
        //validate visit start date
        if(visit.getDateStarted() != null)
        {
            visitValidator.remove(Constant.VISIT_START_DATE_MISSING);
            if(!Misc.isBefore1990(visit.getDateStarted().toGregorianCalendar()))
            {
                visitValidator.remove(Constant.VISIT_START_DATE_EARLIER_THAN_1990);
            }
            if(!Misc.isBeforeDate(visit.getDateStarted().toGregorianCalendar(), patientDemo.getBirthdate().toGregorianCalendar())){
                visitValidator.remove(Constant.VISIT_START_DATE_EARLIER_THAN_DOB);
            }
            
            if(!Misc.isInFuture(visit.getDateStarted().toGregorianCalendar()))
            {
                visitValidator.remove(Constant.VISIT_START_DATE_IN_FUTURE);
            }
        }
        else{//visit date started is missing then
           visitValidator.remove(Constant.VISIT_START_DATE_EARLIER_THAN_1990);
           visitValidator.remove(Constant.VISIT_START_DATE_EARLIER_THAN_DOB);
           visitValidator.remove(Constant.VISIT_START_DATE_IN_FUTURE);
        }
        
        //validate visit 
        //validate visit start date
        if(visit.getDateStopped()!= null)
        {
            visitValidator.remove(Constant.VISIT_END_DATE_MISSING);
            if(!Misc.isBefore1990(visit.getDateStopped().toGregorianCalendar()))
            {
                visitValidator.remove(Constant.VISIT_END_DATE_EARLIER_THAN_1990);
            }
            if(!Misc.isBeforeDate(visit.getDateStopped().toGregorianCalendar(), patientDemo.getBirthdate().toGregorianCalendar())){
                visitValidator.remove(Constant.VISIT_END_DATE_EARLIER_THAN_DOB);
            }
            
            if(!Misc.isInFuture(visit.getDateStopped().toGregorianCalendar()))
            {
                visitValidator.remove(Constant.VISIT_END_DATE_IN_FUTURE);
            }
        }
        else{//visit date started is missing then
           visitValidator.remove(Constant.VISIT_END_DATE_EARLIER_THAN_1990);
           visitValidator.remove(Constant.VISIT_END_DATE_EARLIER_THAN_DOB);
           visitValidator.remove(Constant.VISIT_END_DATE_IN_FUTURE);
        }
        
        patientVisitValidationData.put(patientDemo.getPatientUuid()+"__"+visit.getVisitUuid(), visitValidator);
        ContainerController.visitValidationData.put(patientDemo.getPatientUuid(), patientVisitValidationData);    
    }
    
    public static void validateEncounter(DemographicsType patientDemo, EncounterType encounter)
    {
         Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(patientDemo.getPatientUuid());
         Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(patientDemo.getPatientUuid()+"__"+encounter.getEncounterUuid());
        //validate visit start date
        if(encounter.getEncounterDatetime() != null)
        {
            encounterValidator.remove(Constant.ENCOUNTER_DATE_EMPTY);
            boolean earlierThanDob = true;
            if(!Misc.isBeforeDate(encounter.getEncounterDatetime().toGregorianCalendar(), patientDemo.getBirthdate().toGregorianCalendar()))
            {
                encounterValidator.remove(Constant.ENCOUNTER_DATE_EARLIER_THAN_DOB);
                earlierThanDob = false;
            }
            
            boolean inFuture = true;
            if(!Misc.isInFuture(encounter.getEncounterDatetime().toGregorianCalendar()))
            {
                encounterValidator.remove(Constant.ENCOUNTER_DATE_IN_FUTURE);
                inFuture = false;
                
            }
            
            
            //let validate hiv enrollment form 
            if(encounter.getEncounterTypeId() == Constant.HIV_ENROLLMENT_FORM)
            {
                //since date time is not empty already, we can do
                encounterValidator.remove(Constant.ENROLLMENT_DATE_MISSING);
                //at least it is not missing. lets add it to the patients map
                ContainerController.allPatients.get(patientDemo.getPatientUuid()).setEnrollmentDate(new DateTime(encounter.getEncounterDatetime().toGregorianCalendar()));
                
                if(!inFuture)
                {
                    encounterValidator.remove(Constant.ENROLLMENT_DATE_IN_FUTURE);
                }
            }
            
           
        }
        else{//visit date started is missing then
           encounterValidator.remove(Constant.ENCOUNTER_DATE_EARLIER_THAN_DOB);
           encounterValidator.remove(Constant.ENCOUNTER_DATE_IN_FUTURE);
           
        }
        
        patientEncounterValidator.put(patientDemo.getPatientUuid()+"__"+encounter.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(patientDemo.getPatientUuid(), patientEncounterValidator);
        //lets validate enrollment form encounter
        
    }
    
    public static void ValidateObs(ObsType obs)
    {
        if(obs.getConceptId() == Constant.HIV_DIAGNOSIS_DATE_CONCEPT)
        {
           // System.out.println("diagnosis date vo")
            Validator.validateHIVDiagnosisDate(obs);
        }
        else if(obs.getConceptId() == Constant.ENROLLMENT_METHOD_CONCEPT)
        {
            Validator.validateEnrollmentMethod(obs);
        }
        else if(obs.getConceptId() == Constant.PRIOR_ART_CONCEPT)
        {
            Validator.validatePriorART(obs);
        }
        else if(obs.getConceptId() == Constant.FAC_TRANSFERRED_FROM_CONCEPT)
        {
            Validator.validateFacTransferredFrom(obs);
        }
        else if(obs.getConceptId() == Constant.DATE_TRANSFERRED_CONCEPT)
        {
            Validator.validateDateTransferred(obs);
        }
        else if(obs.getConceptId() == Constant.ART_START_DATE_CONCEPT)
        {
            Validator.validateARTStartDate(obs);
        }
        else if(obs.getConceptId() == Constant.CURRENT_REGIMEN_LINE_CONCEPT)
        {
            Validator.validateCurrentRegimenLine(obs);
        }
        else if(obs.getConceptId() == Constant.REGIMEN_DURATION_CONCEPT)
        {
            Validator.validateDuration(obs);
        }
        else if(obs.getConceptId() == Constant.REGIMEN_FOR_DURATION_CONCEPT)
        {
            Validator.validateRegimenForDuration(obs);
        }
        else if(obs.getConceptId() == Constant.RETURN_VISIT_DATE_CONCEPT)
        {
            Validator.validateReturnVisitDate(obs);
        }
        else if(obs.getConceptId() == Constant.DATE_REPORTED_CONCEPT)
        {
            Validator.validateDateReported(obs);
            Validator.saveVLReportDate(obs);
        }
        else if(obs.getConceptId() == Constant.DATE_ORDERED_CONCEPT)
        {
            Validator.validateDateOrdered(obs);
        }
        else if(obs.getConceptId() == Constant.WEIGHT_CONCEPT)
        {
            Validator.validateWeight(obs);
        }
        else if(obs.getConceptId() == Constant.HEIGHT_CONCEPT)
        {
            Validator.validateHeight(obs);
        }
        else if(obs.getConceptId() == Constant.TB_STATUS_CONCEPT)
        {
            Validator.validateTBStatus(obs);
        }
        else if(obs.getConceptId() == Constant.WHO_CONCEPT)
        {
            Validator.validateWHOStaging(obs);
        }
        else if(obs.getConceptId() == Constant.ADULT_FIRST_LINE_REGIMEN_CONCEPT || obs.getConceptId() == Constant.PED_FIRST_LINE_REGIMEN_CONCEPT)
        {
            //no validation at this point. Just save the initial and current first line regimen and dates
            Validator.saveFirstLineRegimen(obs);
        }
        else if(obs.getConceptId() == Constant.ADULT_SECOND_LINE_REGIMEN_CONCEPT || obs.getConceptId() == Constant.PED_SECOND_LINE_REGIMEN_CONCEPT)
        {
            Validator.saveSecondLineRegimen(obs);
        }
        else if(obs.getConceptId() == Constant.PREGNANCY_STATUS_CONCEPT)
        {
            Validator.savePregnancyStatus(obs);
        }
        else if(obs.getConceptId() == Constant.VIRAL_LOAD_RESULT_CONCEPT)
        {
            Validator.saveCurrentVl(obs);
        }else if(obs.getConceptId() == Constant.VL_SAMPLE_COLLECTION_DATE)
        {
            Validator.saveVLSampleCollectionDate(obs);
        }
        else if(obs.getConceptId() == Constant.INDICATION_FOR_VL)
        {
            Validator.saveVLindication(obs);
        }
        else if(obs.getConceptId() == Constant.TERMINATION_DATE)
        {
            Validator.saveDead(obs);
        }else if(obs.getConceptId() == Constant.INH_DOSE_CONCEPT)
        {
            Validator.saveInhDates(obs);
        }
        
    }
   
    public static void saveDead(ObsType obs)
    {
        ContainerController.allRadet.get(obs.getPatientUuid()).setDeathDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
    }
    
    
    
    public static void saveFirstLineRegimen(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("initialFirstLineRegimen") == null)//this is the first time
        {
            //we will use this as the initial and current regimen line until another comes through
           ContainerController.allRadet.get(obs.getPatientUuid()).setInitialFirstLineRegimen(obs.getVariableValue());
           ContainerController.allRadet.get(obs.getPatientUuid()).setInitialFirstLineRegimenDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
           ContainerController.lookupData.get(obs.getPatientUuid()).put("initialFirstLineRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));

           
           ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentFirstLineRegimen(obs.getVariableValue());
           ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentFirstLineRegimenDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
           ContainerController.lookupData.get(obs.getPatientUuid()).put("currFirstLineRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));

        }
        else{
             DateTime initialRegimenDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("initialFirstLineRegimen");
             DateTime currentRegimenDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("currFirstLineRegimen");
             if(Misc.isBeforeDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), initialRegimenDate))
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("initialFirstLineRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setInitialFirstLineRegimenDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setInitialFirstLineRegimen(obs.getVariableValue());
                 //ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
             }

             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), currentRegimenDate))
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("currFirstLineRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentFirstLineRegimen(obs.getVariableValue());
                 ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentFirstLineRegimenDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
           
                 //ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
             }
        }
    }

    public static void saveSecondLineRegimen(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("initialSecondLineRegimen") == null)//this is the first time
        {
            //we will use this as the initial and current regimen line until another comes through
           ContainerController.allRadet.get(obs.getPatientUuid()).setInitialSecondLineRegimen(obs.getVariableValue());
           ContainerController.allRadet.get(obs.getPatientUuid()).setInitialSecondLineRegimenDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
           ContainerController.lookupData.get(obs.getPatientUuid()).put("initialSecondLineRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));

           
           ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentSecondLineRegimen(obs.getVariableValue());
           ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentSecondLineRegimenDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
           ContainerController.lookupData.get(obs.getPatientUuid()).put("currSecondLineRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));

        }
        else{
             DateTime initialRegimenDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("initialSecondLineRegimen");
             DateTime currentRegimenDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("currSecondLineRegimen");
             if(Misc.isBeforeDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), initialRegimenDate))
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("initialSecondLineRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setInitialSecondLineRegimenDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setInitialSecondLineRegimen(obs.getVariableValue());
                 //ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
             }

             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), currentRegimenDate))
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("currSecondLineRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentSecondLineRegimen(obs.getVariableValue());
                 ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentSecondLineRegimenDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
           
                 //ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
             }
        }
    }
    
    public static void savePregnancyStatus(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("pregnancyStatusDate") == null)//this is the first time
        {
            //we will use this as the initial and current regimen line until another comes through
           ContainerController.allRadet.get(obs.getPatientUuid()).setPregnancyStatus(obs.getVariableValue());
           ContainerController.lookupData.get(obs.getPatientUuid()).put("pregnancyStatusDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
        }
        else{
             DateTime prgnancyStatusDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("pregnancyStatusDate");
           

             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), prgnancyStatusDate))//then use this one
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("pregnancyStatusDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setPregnancyStatus(obs.getVariableValue());
             }
        }
    }
    
    public static void saveCurrentVl(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("currVlDate") == null)//this is the first time
        {
            //we will use this as the initial and current regimen line until another comes through
           ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentViralLoad(obs.getValueNumeric().floatValue());
           ContainerController.lookupData.get(obs.getPatientUuid()).put("currVlDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
        }
        else{
             DateTime currVlDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("currVlDate");
           

             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), currVlDate))//then use this one
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("currVlDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentViralLoad(obs.getValueNumeric().floatValue());
             }
        }
    }
    
    public static void saveVLReportDate(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("vlReportDate") == null)//this is the first time
        {
            //we will use this as the initial and current regimen line until another comes through
           ContainerController.allRadet.get(obs.getPatientUuid()).setViralLoadReportedDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
           ContainerController.lookupData.get(obs.getPatientUuid()).put("vlReportDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
        }
        else{
             DateTime currVlDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("vlReportDate");
           

             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), currVlDate))//then use this one
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("vlReportDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setViralLoadReportedDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
             }
        }
    }
    
    
    
    public static void saveVLSampleCollectionDate(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("vlSampleCollectionDate") == null)//this is the first time
        {
            //we will use this as the initial and current regimen line until another comes through
           ContainerController.allRadet.get(obs.getPatientUuid()).setViralLoadSampleCollectionDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
           ContainerController.lookupData.get(obs.getPatientUuid()).put("vlSampleCollectionDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
        }
        else{
             DateTime currVlDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("vlSampleCollectionDate");
           

             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), currVlDate))//then use this one
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("vlSampleCollectionDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setViralLoadSampleCollectionDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
             }
        }
    }

    
    public static void saveVLindication(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("vlIndicationDate") == null)//this is the first time
        {
            //we will use this as the initial and current regimen line until another comes through
           ContainerController.allRadet.get(obs.getPatientUuid()).setViralLoadIndication(obs.getValueText());
           ContainerController.lookupData.get(obs.getPatientUuid()).put("vlIndicationDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
        }
        else{
             DateTime currVlDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("vlIndicationDate");
             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), currVlDate))//then use this one
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("vlIndicationDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setViralLoadIndication(obs.getValueText());
             }
        }
    }
    
    public static void validateHIVDiagnosisDate(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        //JSONObject errorData = encounterValidator.get(Constant);
        //System.out.println("validating diagnosis date "+obs.getValueDatetime());
        if(obs.getValueDatetime() != null)
        {
           //at least it is not empty
            ContainerController.allPatients.get(obs.getPatientUuid()).setDateConfirmedPositive(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
            encounterValidator.remove(Constant.DIAGNOSIS_DATE_MISSING);  
            if(!Misc.isInFuture(obs.getValueDatetime().toGregorianCalendar()))
            {//remove the error
                encounterValidator.remove(Constant.DIAGNOSIS_DATE_IN_FUTURE);
            }
            else{//get the error and add the obs uuid
                JSONObject errorData = encounterValidator.get(Constant.DIAGNOSIS_DATE_IN_FUTURE);
                errorData.put("obs", obs);
                encounterValidator.put(Constant.DIAGNOSIS_DATE_IN_FUTURE, errorData);
            }
            
            //get the dob from lookup data 
            JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
            XMLGregorianCalendar dob = (patientLookupData.get(Constant.DOB) != null) ? (XMLGregorianCalendar)patientLookupData.get(Constant.DOB) : null;
            if(dob != null)
            {  
               if(!Misc.isBeforeDate(obs.getValueDatetime().toGregorianCalendar(), dob.toGregorianCalendar()))
               {
                   encounterValidator.remove(Constant.DIAGNOSIS_DATE_EARLIER_THAN_DOB);
               }
               else{
                   JSONObject errorData = encounterValidator.get(Constant.DIAGNOSIS_DATE_EARLIER_THAN_DOB);
                    errorData.put("obs", obs);
                    encounterValidator.put(Constant.DIAGNOSIS_DATE_EARLIER_THAN_DOB, errorData);
               }
            }
            
        }
        else{
            encounterValidator.remove(Constant.DIAGNOSIS_DATE_IN_FUTURE);
            encounterValidator.remove(Constant.DIAGNOSIS_DATE_EARLIER_THAN_DOB);
        }
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
        
    }
    
    public static void validateEnrollmentMethod(ObsType obs)
    {
        
        //we can set the care entry point here
        ContainerController.allRadet.get(obs.getPatientUuid()).setCareEntryPoint(obs.getVariableValue());
        ContainerController.allRadet.get(obs.getPatientUuid()).setTransferInStatus(obs.getVariableValue());
        
        //for us to get here, we can now check if the value has something
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        
        if(obs.getValueCoded() != 0)
        {
            encounterValidator.remove(Constant.MISSING_ENROLLMENT_METHOD);
        }
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
    }
    
    public static void validatePriorART(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        if(obs.getValueCoded() != 0)
        {
            encounterValidator.remove(Constant.PRIOR_ART_MISSING);
            //now check if prior art is transferred in with records
            if(obs.getValueCoded() == Constant.TRANSFER_IN_WITH_RECORDS)
            {
                Validator.setupPriorARTValidation(obs.getPatientUuid(), obs.getEncounterUuid());
                 //set transferred in to yes
                 ContainerController.allPatients.get(obs.getPatientUuid()).setTransferredIn("yes");
                
            }
        }
        
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
        
    }
    
    public static void validateDateTransferred(ObsType obs)
    {
        
        //here, we can set date transferred in
        ContainerController.allRadet.get(obs.getPatientUuid()).setTransferInDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        if(obs.getValueDatetime() != null)
        {
            encounterValidator.remove(Constant.TRANSFER_DATE_MISSING);
            if(!Misc.isInFuture(obs.getValueDatetime().toGregorianCalendar()))
            {
                encounterValidator.remove(Constant.TRANSFER_DATE_IN_FUTURE);
            }
            else{//add the obs with errorous data
                JSONObject obj = encounterValidator.get(Constant.TRANSFER_DATE_IN_FUTURE);
                obj.put("obs", obs);
                encounterValidator.put(Constant.TRANSFER_DATE_IN_FUTURE, obj);
            }
        }
        else{
            encounterValidator.remove(Constant.TRANSFER_DATE_IN_FUTURE);
        }
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
        
        
    }
    
    public static void validateFacTransferredFrom(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        encounterValidator.remove(Constant.FAC_TRANSFERRED_FROM_MISSING);
        
        
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
    }
    public static void validateARTStartDate(ObsType obs){
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        //System.out.println(obs.getValueDatetime());
        if(obs.getValueDatetime() != null)
        {//art start date is not missing
            
            
            encounterValidator.remove(Constant.ART_START_DATE_MISSING);
            
            //get the dob from lookup data 
            JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
            XMLGregorianCalendar dob = (patientLookupData.get(Constant.DOB) != null) ? (XMLGregorianCalendar)patientLookupData.get(Constant.DOB) : null;
            
            ContainerController.allPatients.get(obs.getPatientUuid()).setArtStartDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
            //also lets set the date at art start date
            int ageYearAtARTStart = Misc.getAge(dob.toGregorianCalendar(), obs.getValueDatetime().toGregorianCalendar());
            int ageMonthAtARTStart = Misc.getAgeMonths(dob.toGregorianCalendar(), obs.getValueDatetime().toGregorianCalendar());
            
            ContainerController.allPatients.get(obs.getPatientUuid()).setAgeAtArtStartYears(ageYearAtARTStart);
            ContainerController.allPatients.get(obs.getPatientUuid()).setAgeAtArtStartMonths(ageMonthAtARTStart);
            
            if(Misc.isAfterDate(obs.getValueDatetime().toGregorianCalendar(), dob.toGregorianCalendar()))
            {
                encounterValidator.remove(Constant.ART_START_DATE_EARLIER_THAN_DOB);
            }
            else{
                JSONObject obj = encounterValidator.get(Constant.ART_START_DATE_EARLIER_THAN_DOB);
                obj.put("obs", obs);
                encounterValidator.put(Constant.ART_START_DATE_EARLIER_THAN_DOB, obj);
            }
            
            //check and get enrollment date from lookup date
            XMLGregorianCalendar enrollmentDate = (patientLookupData.get(Constant.ENROLLMENT_DATE) != null) ? (XMLGregorianCalendar)patientLookupData.get(Constant.DOB) : null;
            if(enrollmentDate == null)//enrollment date has not been taken yet, lets store the start start date so we can do the check later
            {
                patientLookupData.put(Constant.ART_START_DATE, obs);
            }
            else{//the if is unlikely anyways. So we are most likely going to end up here
                if(Misc.isAfterDate(obs.getValueDatetime().toGregorianCalendar(), enrollmentDate.toGregorianCalendar()))
                {
                    
                    encounterValidator.remove(Constant.ART_START_DATE_EARLIER_THAN_ENROLLMENT_DATE);
                }
                else{
                    //we want to get the obs uuid and add it.
                    JSONObject obj = encounterValidator.get(Constant.ART_START_DATE_EARLIER_THAN_ENROLLMENT_DATE);
                    obj.put("obs", obs);
                    
                    encounterValidator.put(Constant.ART_START_DATE_EARLIER_THAN_ENROLLMENT_DATE, obj);
                }
            }
        }
        else{
            encounterValidator.remove(Constant.ART_START_DATE_EARLIER_THAN_DOB);
            encounterValidator.remove(Constant.ART_START_DATE_EARLIER_THAN_ENROLLMENT_DATE);
            encounterValidator.remove(Constant.ART_START_DATE_IN_FUTURE);
        }
        
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
    }
    
    public static void validateCurrentRegimenLine(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        if(obs.getValueCoded() != 0)
        {
            //that means we have something
           // Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
            Integer [] validRegimenConcepts = new Integer[]{164506,164513,165702,164507,164514,165703};
            List<Integer> list = Arrays.asList(validRegimenConcepts);
            
            if(list.indexOf(obs.getValueCoded()) > -1)
            {
                encounterValidator.remove(Constant.ADULT_PED_REGIMEN_LINE_MISSING);
               // ContainerController.encounterValidationData.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator); 
                
               //we can collect the intial and current regimen line regimen
                if(ContainerController.lookupData.get(obs.getPatientUuid()).get("initialRegimen") == null)//this is the first time
                {
                    //we will use this as the initial and current regimen line until another comes through
                   ContainerController.allRadet.get(obs.getPatientUuid()).setInitialRegimenLine(obs.getVariableValue());
                   ContainerController.lookupData.get(obs.getPatientUuid()).put("initialRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                   
                   ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentRegimenLine(obs.getVariableValue());
                   ContainerController.lookupData.get(obs.getPatientUuid()).put("currentRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                    
                }
                else{
                     DateTime initialRegimenDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("initialRegimen");
                     DateTime currentRegimenDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("currentRegimen");
                     if(Misc.isBeforeDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), initialRegimenDate))
                     {
                         ContainerController.lookupData.get(obs.getPatientUuid()).put("initialRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                         ContainerController.allRadet.get(obs.getPatientUuid()).setInitialRegimenLine(obs.getVariableValue());
                         //ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
                     }

                     if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), currentRegimenDate))
                     {
                         ContainerController.lookupData.get(obs.getPatientUuid()).put("currentRegimen", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                         ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentRegimenLine(obs.getVariableValue());
                         //ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
                     }
                }
            }
        }
        
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
        
    }
    
    public static void validateRegimenForDuration(ObsType obs)
    {
        //add this to lookup data and try to validate duration
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        if(!patientLookupData.containsKey("duration"))//create the key if it does not exist
        {
            patientLookupData.put("duration", new JSONObject());
        }
        JSONObject obj = (JSONObject)patientLookupData.get("duration");

        if(!obj.containsKey(obs.getEncounterUuid()+"__"+obs.getObsGroupId()))//create the group key if it does not exist
        {
            obj.put(obs.getEncounterUuid()+"__"+obs.getObsGroupId(), new JSONObject());
        }

        JSONObject groupObj = (JSONObject)obj.get(obs.getEncounterUuid()+"__"+obs.getObsGroupId());
        groupObj.put("regimen", obs.getValueCoded());
        obj.put(obs.getEncounterUuid()+"__"+obs.getObsGroupId(), groupObj);
        patientLookupData.put("duration", obj);
        ContainerController.lookupData.put(obs.getPatientUuid(), patientLookupData);

        //we can also try to validate the arv medication duration here if the duration has been saved before. if not, then save this one in look up data
        validateMedicationDuration(obs);
    }
    
    public static void validateDuration(ObsType obs)
    {
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        if(!patientLookupData.containsKey("duration"))//create the key if it does not exist
        {
            patientLookupData.put("duration", new JSONObject());
        }
        JSONObject obj = (JSONObject)patientLookupData.get("duration");

        if(!obj.containsKey(obs.getEncounterUuid()+"__"+obs.getObsGroupId()))//create the group key if it does not exist
        {
            obj.put(obs.getEncounterUuid()+"__"+obs.getObsGroupId(), new JSONObject());
        }
       
        JSONObject groupObj = (JSONObject)obj.get(obs.getEncounterUuid()+"__"+obs.getObsGroupId());
        groupObj.put("drugDuration", obs.getValueNumeric());
        obj.put(obs.getEncounterUuid()+"__"+obs.getObsGroupId(), groupObj);
        patientLookupData.put("duration", obj);
        ContainerController.lookupData.put(obs.getPatientUuid(), patientLookupData);
        Validator.validateMedicationDuration(obs);
        
    }
    
    public static void validateMedicationDuration(ObsType obs)
    {
        
       
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        
        
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        JSONObject obj = (JSONObject) patientLookupData.get("duration");
        JSONObject groupObj = (JSONObject)obj.get(obs.getEncounterUuid()+"__"+obs.getObsGroupId());
        System.out.println("drug regi and duration "+groupObj.get("regimen")+"__"+groupObj.get("drugDuration"));
        if(groupObj.containsKey("regimen") && groupObj.containsKey("drugDuration"))
        {
            //get the validation and validate
            //Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
            Float duration = Float.parseFloat(groupObj.get("drugDuration").toString());
            
            if(duration > 0)
            {
                encounterValidator.remove(Constant.MEDICATION_DURATION_EMPTY);
                
                if(duration <= 180)
                {
                    encounterValidator.remove(Constant.MEDICATION_DURATION_MORE_THAN_180_DAYS);
                    //if we got here, then we are good. we can compare the date of the encounter with the last saved encounter. if this is later, then use this
                   //get the encounter for this obs
                   JSONObject encounters = (JSONObject)ContainerController.lookupData.get(obs.getPatientUuid()).get("encounters");
                    EncounterType obsEncounter = (EncounterType)encounters.get(obs.getEncounterUuid());
                    DateTime pickupDate = new DateTime(obsEncounter.getEncounterDatetime().toGregorianCalendar());
                    int d = Math.round(duration);
                    if(ContainerController.lookupData.get(obs.getPatientUuid()).get("lastPickupDate") == null)
                    {
                       ContainerController.allRadet.get(obs.getPatientUuid()).setLastPickupDate(pickupDate);
                       ContainerController.lookupData.get(obs.getPatientUuid()).put("lastPickupDate", pickupDate);
                       ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
                    }
                    else{
                         DateTime lastPickupDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("lastPickupDate");
                         if(Misc.isAfterDate(pickupDate, lastPickupDate))
                         {
                             ContainerController.lookupData.get(obs.getPatientUuid()).put("lastPickupDate", pickupDate);
                             ContainerController.allRadet.get(obs.getPatientUuid()).setLastPickupDate(pickupDate);
                             ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
                         }
                    }
                    
                }
            }
            else{//it means the medication is inded empty
                encounterValidator.remove(Constant.MEDICATION_DURATION_MORE_THAN_180_DAYS);
            }
            
            patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
            ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
            //ContainerController.encounterValidationData.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
            
        }
    }
    
    public static void validateReturnVisitDate(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        
        //get the encounter for this obs and if the encounter is of type pharmacy, then validate it
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        JSONObject patientEncounters = (JSONObject)patientLookupData.get("encounters");
        EncounterType encounter = (EncounterType)patientEncounters.get(obs.getEncounterUuid());
        if(encounter.getEncounterTypeId() == Constant.PHARMACY_FORM)
        {
            if(obs.getValueDatetime() != null)
            {
                encounterValidator.remove(Constant.RETURN_VISIT_DATE_MISSING);
            }
        }
        
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
    }
    
    public static void validateDateReported(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        JSONObject patientEncounters = (JSONObject)patientLookupData.get("encounters");
        EncounterType encounter = (EncounterType)patientEncounters.get(obs.getEncounterUuid());
        if(encounter.getEncounterTypeId() == Constant.LAB_ORDER_AND_RESULT_FORM)
        {
            if(obs.getValueDatetime() != null)
            {
                encounterValidator.remove(Constant.REPORTED_DATE_MISSING);
                if(!Misc.isInFuture(obs.getValueDatetime().toGregorianCalendar()))
                {
                    encounterValidator.remove(Constant.REPORTED_DATE_IN_FUTURE);
                }
                
                
                
                //lets put the date reported in lookup date so that we can check whether it is before order date
                if(!patientLookupData.containsKey("ordered_reported"))//create the key if it does not exist
                {
                    patientLookupData.put("ordered_reported", new JSONObject());
                }
                JSONObject obj = (JSONObject)patientLookupData.get("ordered_reported");

                if(!obj.containsKey(obs.getEncounterUuid()))//create the group key if it does not exist
                {
                    obj.put(obs.getEncounterUuid(), new JSONObject());
                }
                JSONObject groupObj = (JSONObject)obj.get(obs.getEncounterUuid());
                groupObj.put("date_reported", obs.getValueDatetime());
                obj.put(obs.getEncounterUuid(), groupObj);
                patientLookupData.put("ordered_reported", obj);
                ContainerController.lookupData.put(obs.getPatientUuid(), patientLookupData);
                
                Validator.validateDateReportedAgainstDateOrdered(obs);
                
            }
            else
            {//if date reported is missing, then we cannot have any of these errors
                encounterValidator.remove(Constant.REPORTED_DATE_IN_FUTURE);
                encounterValidator.remove(Constant.REPORTED_DATE_BEFORE_ORDER_DATE);
            }
        }
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
    }
    
    public static void validateDateOrdered(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        JSONObject patientEncounters = (JSONObject)patientLookupData.get("encounters");
        EncounterType encounter = (EncounterType)patientEncounters.get(obs.getEncounterUuid());
        if(encounter.getEncounterTypeId() == Constant.LAB_ORDER_AND_RESULT_FORM)
        {
            if(obs.getValueDatetime() != null)
            {
                patientEncounterValidator.remove(Constant.ORDER_DATE_MISSING);
                if(!Misc.isInFuture(obs.getValueDatetime().toGregorianCalendar()))
                {
                    encounterValidator.remove(Constant.ORDER_DATE_IN_FUTURE);
                }
                
                
                //lets put the date reported in lookup date so that we can check whether it is before order date
                if(!patientLookupData.containsKey("ordered_reported"))//create the key if it does not exist
                {
                    patientLookupData.put("ordered_reported", new JSONObject());
                }
                JSONObject obj = (JSONObject)patientLookupData.get("ordered_reported");

                if(!obj.containsKey(obs.getEncounterUuid()))//create the group key if it does not exist
                {
                    obj.put(obs.getEncounterUuid(), new JSONObject());
                }
                JSONObject groupObj = (JSONObject)obj.get(obs.getEncounterUuid());
                groupObj.put("date_ordered", obs.getValueDatetime());
                obj.put(obs.getEncounterUuid(), groupObj);
                patientLookupData.put("ordered_reported", obj);
                ContainerController.lookupData.put(obs.getPatientUuid(), patientLookupData);
                
                Validator.validateDateReportedAgainstDateOrdered(obs);
                
            }
            else
            {//if date reported is missing, then we cannot have any of these errors
                encounterValidator.remove(Constant.REPORTED_DATE_IN_FUTURE);
                encounterValidator.remove(Constant.REPORTED_DATE_BEFORE_ORDER_DATE);
            }
        }
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
    }
    
    public static void validateDateReportedAgainstDateOrdered(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
        Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        JSONObject obj = (JSONObject) patientLookupData.get("ordered_reported");
        JSONObject groupObj = (JSONObject)obj.get(obs.getEncounterUuid());
        if(groupObj.containsKey("date_reported") && groupObj.containsKey("date_ordered"))
        {
            //get the validation and validate
            //Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
            XMLGregorianCalendar dateOrdered = (XMLGregorianCalendar)groupObj.get("date_ordered");
            XMLGregorianCalendar dateReported = (XMLGregorianCalendar)groupObj.get("date_reported");
            if(Misc.isAfterDate(dateReported.toGregorianCalendar(), dateOrdered.toGregorianCalendar()))
            {
                encounterValidator.remove(Constant.REPORTED_DATE_BEFORE_ORDER_DATE);   
            }
            //ContainerController.encounterValidationData.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
            
        }
        patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
    }
    
    public static void validateWeight(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
       // Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        XMLGregorianCalendar dob = (patientLookupData.get(Constant.DOB) != null) ? (XMLGregorianCalendar)patientLookupData.get(Constant.DOB) : null;
            
        if(obs.getValueNumeric().intValue() < 250)
        {
           patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.WEIGHT_OUT_OF_RANGE);
           
           //we can save the weight
           Validator.saveWeight(obs);
        }
        if(obs.getValueNumeric().intValue() < 10 && dob != null && Misc.getAge(dob.toGregorianCalendar()) > Constant.CHILD_AGE)//we are checking for 7 years
        {
            
        }
        else{
            patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.WEIGHT_TOO_SMALL_ADULT);
        }
        ///patientEncounterValidator.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
        ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
    }
    
    public static void saveWeight(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("currWeightDate") == null)//this is the first time
        {
            //we will use this as the initial and current regimen line until another comes through
           ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentWeightDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
           ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentWeight(obs.getValueNumeric().floatValue());
           ContainerController.lookupData.get(obs.getPatientUuid()).put("currWeightDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
        }
        else{
             DateTime currVlDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("currWeightDate");
           

             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), currVlDate))//then use this one
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("currWeightDate", new DateTime(obs.getValueDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentWeightDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setCurrentWeight(obs.getValueNumeric().floatValue());
             }
        }
    }
    
    public static void validateHeight(ObsType obs)
    {
        Map<String, Map<String, JSONObject>> patientEncounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid());
       // Map<String, JSONObject> encounterValidator = patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        XMLGregorianCalendar dob = (patientLookupData.get(Constant.DOB) != null) ? (XMLGregorianCalendar)patientLookupData.get(Constant.DOB) : null;
            
        if(obs.getValueNumeric().intValue() < 400)
        {
            patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.HEIGHT_OUT_OF_RANGE);
        }
        if(obs.getValueNumeric().intValue() < 10 && dob != null && Misc.getAge(dob.toGregorianCalendar()) > Constant.CHILD_AGE)//we are checking for 7 years
        {
            
        }
        else{
             patientEncounterValidator.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.HEIGHT_TOO_SMALL_ADULT);
        }
         ContainerController.encounterValidationData.put(obs.getPatientUuid(), patientEncounterValidator);
    }
    
    
    public static void validateWHOStaging(ObsType obs)
    {
        if(obs.getValueCoded() != 0)
        {
            ContainerController.encounterValidationData.get(obs.getPatientUuid()).get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.WHO_STAGE_MISSING);
        }
    }
    public static void validateTBStatus(ObsType obs)
    {
        if(obs.getValueCoded() != 0)
        {
            ContainerController.encounterValidationData.get(obs.getPatientUuid()).get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.TB_STATUS_MISSING);
            Validator.saveTBStatus(obs);
        }
    }
    
    public static void saveTBStatus(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("tbStatusDate") == null)//this is the first time
        {
            //we will use this as the initial and current regimen line until another comes through
           ContainerController.allRadet.get(obs.getPatientUuid()).setTbStatusDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
           ContainerController.allRadet.get(obs.getPatientUuid()).setTbStatus(obs.getVariableValue());
           ContainerController.lookupData.get(obs.getPatientUuid()).put("tbStatusDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
        }
        else{
             DateTime currVlDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("tbStatusDate");
           

             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), currVlDate))//then use this one
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("tbStatusDate", new DateTime(obs.getValueDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setTbStatusDate(new DateTime(obs.getValueDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setTbStatus(obs.getVariableValue());
             }
        }
    }
    
    public static void saveInhDates(ObsType obs)
    {
        if(ContainerController.lookupData.get(obs.getPatientUuid()).get("inhStartDate") == null)//this is the first time
        {
            
           ContainerController.allRadet.get(obs.getPatientUuid()).setInhStartDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
           ContainerController.lookupData.get(obs.getPatientUuid()).put("inhStartDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));

          
           ContainerController.allRadet.get(obs.getPatientUuid()).setInhStopDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
           ContainerController.lookupData.get(obs.getPatientUuid()).put("inhStopDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));

           
        }
        else{
             DateTime inhStartDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("inhStartDate");
             DateTime inhStopDate = (DateTime)ContainerController.lookupData.get(obs.getPatientUuid()).get("inhStopDate");
             if(Misc.isBeforeDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), inhStartDate))
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("inhStartDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setInhStartDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 //ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
             }

             if(Misc.isAfterDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()), inhStopDate))
             {
                 ContainerController.lookupData.get(obs.getPatientUuid()).put("inhStopDate", new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setInhStopDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
                 ContainerController.allRadet.get(obs.getPatientUuid()).setLastInhDispensedDate(new DateTime(obs.getObsDatetime().toGregorianCalendar()));
           
                 //ContainerController.allRadet.get(obs.getPatientUuid()).setDaysOfARVRefill(d);
             }
        }
    }
    
   
}
