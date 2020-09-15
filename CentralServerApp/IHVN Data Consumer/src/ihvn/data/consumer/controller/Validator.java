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
import ihvn.data.consumer.model.xml.VisitType;
import ihvn.data.consumer.models.Constant;
import ihvn.data.consumer.models.Constant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;
import org.json.simple.JSONObject;

/**
 *
 * @author lordmaul
 */
public class Validator {
    
    public static void setupPatientValidationData(String patientUUID)
    {
        Map<String, String> patientValidationData = new HashMap<>();
        patientValidationData.put(Constant.GENDER_MISSING, "Gender cannot be missing");
        patientValidationData.put(Constant.INVALID_GENDER, "Gender can only be 'M' and 'F'");
        patientValidationData.put(Constant.BIRTHDATE_EMPTY, "Birthdate cannot be empty");
        patientValidationData.put(Constant.BIRTHDATE_IN_FUTURE, "Birthdate cannot be in the future");
        patientValidationData.put(Constant.AGE_ABOVE_200, "Current Age cannot be more than 200");
        patientValidationData.put(Constant.NO_PROGRAM, "All patient must be enrolled into at least one program");
        patientValidationData.put(Constant.NO_PEPFAR_ID, "All patients should have PepfarID");
        ContainerController.validationData.put(patientUUID, patientValidationData);
        
        //add whatever we need to the lookup data
        //ContainerController
        
        //setup a few other things here
        JSONObject obj = new JSONObject();
        obj.put("enounters", new JSONObject());
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
        ContainerController.visitValidationData.put(patientUUID+"__"+visitUUID, visitValidationData);
    }
    
    public static void setEncounterValidation(String patientUUID, EncounterType encounter)
    {
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = new HashMap<>();
        obj = new JSONObject();
        obj.put("error", "Date enrolled cannot be in the future");
        encounterValidationData.put(Constant.ENCOUNTER_DATE_IN_FUTURE, obj );
        obj = new JSONObject();
        obj.put("error", "Date enrolled cannot be missing");
        encounterValidationData.put(Constant.ENCOUNTER_DATE_EMPTY, obj);
        obj = new JSONObject();
        obj.put("error", "date_enrolled cannot be earlier than Birthdate");
        encounterValidationData.put(Constant.ENCOUNTER_DATE_EARLIER_THAN_DOB, obj);
        ContainerController.encounterValidationData.put(patientUUID+"__"+encounter.getEncounterUuid(), encounterValidationData);
        
        JSONObject patientLookupData = ContainerController.lookupData.get(patientUUID);
        JSONObject encounterObjs = (JSONObject)patientLookupData.get("encounters");//get the encounters and add a new entry to it
        encounterObjs.put(encounter.getEncounterUuid(), encounter);
        
        
    }
    
    public static void setupPharmacyEncounterValidation(String patientUUID, String encounterUUID)
    { 
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = new HashMap<>();
        obj = new JSONObject();
        obj.put("error", "Current Regimen Line is missing in Pharmacy forms");
        encounterValidationData.put(Constant.CURRENT_REGIMEN_LINE_MISSING, obj );
        obj = new JSONObject();
        obj.put("error", "Adult or Pediatric Regimen Line is missing");
        encounterValidationData.put(Constant.ADULT_PED_REGIMEN_LINE_MISSING, obj);
        obj = new JSONObject();
        obj.put("error", "Medication duration for an ARV Medication   cannot be empty");
        encounterValidationData.put(Constant.MEDICATION_DURATION_EMPTY, obj);
        obj = new JSONObject();
        obj.put("error", "Medication duration cannot be more than 6 months");
        encounterValidationData.put(Constant.MEDICATION_DURATION_MORE_THAN_180_DAYS, obj);
        obj = new JSONObject();
        obj.put("error", "Return Visit Date is missing in Pharmacy Form");
        encounterValidationData.put(Constant.RETURN_VISIT_DATE_MISSING, obj );
        ContainerController.encounterValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData); 
    }
    public static void setupLabEncounterValidation(String patientUUID, String encounterUUID){
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = new HashMap<>();
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
        ContainerController.encounterValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData); 
    }
    public static void setupClinicalEncounterValidation(String patientUUID, String encounterUUID)
    {
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = new HashMap<>();
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
        ContainerController.encounterValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData); 
    }
    
    public static void setupAdultPedInitialEvaluationForm(String patientUUID, String encounterUUID)
    {
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = new HashMap<>();
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
        ContainerController.encounterValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData); 
    }
    public static void setupEnrollmentFormEncounterValidation(String patientUUID, EncounterType encounter)
    {
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = new HashMap<>();
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
        obj.put("error", "Prior ART cannot be empty");
        encounterValidationData.put(Constant.PRIOR_ART_MISSING, obj);
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
        obj.put("error", "Missing Method of enrollment");
        encounterValidationData.put(Constant.MISSING_ENROLLMENT_METHOD, obj);
        
        obj = new JSONObject();
        obj.put("error", "ART Start Date earlier than date_enrolled");
        encounterValidationData.put(Constant.ART_START_DATE_EARLIER_THAN_ENROLLMENT_DATE, obj);
        ContainerController.encounterValidationData.put(patientUUID+"__"+encounter.getEncounterUuid(), encounterValidationData); 
        
        //we also want to add the hiv enrollment date to lookup data
        JSONObject patientLookupData = ContainerController.lookupData.get(patientUUID);
        patientLookupData.put(Constant.HIV_ENROLLMENT_DATE, encounter.getEncounterDatetime());
        ContainerController.lookupData.put(patientUUID, patientLookupData);
    }
    
    public static void setupArtCommencementForm(String patientUUID, String encounterUUID)
    {
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = new HashMap<>();
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
    }
    
    public static void setupPriorARTValidation(String patientUUID, String encounterUUID)//if prior art is transferred in with records
    {
        JSONObject obj ;
        Map<String, JSONObject> encounterValidationData = ContainerController.encounterValidationData.get(patientUUID+"__"+encounterUUID);
        obj = new JSONObject();
        obj.put("error", "Date Transferred in invalid");
        encounterValidationData.put(Constant.TRANSFER_DATE_MISSING, obj);
         obj = new JSONObject();
        obj.put("error", "Date Transferred in cannot be in the future");
        encounterValidationData.put(Constant.TRANSFER_DATE_IN_FUTURE, obj);
         obj = new JSONObject();
        obj.put("error", "Facility Transferred from is missing");
        encounterValidationData.put(Constant.FAC_TRANSFERRED_FROM_MISSING, obj);
        ContainerController.encounterValidationData.put(patientUUID+"__"+encounterUUID, encounterValidationData);
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
    
    
    public static void validateProgram(DemographicsType patientDemo, int programCount)
    {
        Map<String, String> patientValidator = ContainerController.validationData.get(patientDemo.getPatientUuid());
            
        if(programCount > 0)
        {
            patientValidator.remove(Constant.NO_PROGRAM);
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
            }
        }
        ContainerController.validationData.put(patientDemo.getPatientUuid(), patientValidator);
    }
    
    
    
    public static void validateVisit(DemographicsType patientDemo, VisitType visit)
    {
         Map<String, String> visitValidator = ContainerController.visitValidationData.get(patientDemo.getPatientUuid()+"__"+visit.getVisitUuid());
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
        
        ContainerController.visitValidationData.put(patientDemo.getPatientUuid()+"__"+visit.getVisitUuid(), visitValidator);    
    }
    
    public static void validateEncounter(DemographicsType patientDemo, EncounterType encounter)
    {
         Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(patientDemo.getPatientUuid()+"__"+encounter.getEncounterUuid());
        //validate visit start date
        if(encounter.getEncounterDatetime() != null)
        {
            encounterValidator.remove(Constant.ENCOUNTER_DATE_EMPTY);
            if(!Misc.isBeforeDate(encounter.getEncounterDatetime().toGregorianCalendar(), patientDemo.getBirthdate().toGregorianCalendar()))
            {
                encounterValidator.remove(Constant.ENCOUNTER_DATE_EARLIER_THAN_DOB);
            }
           
        }
        else{//visit date started is missing then
           encounterValidator.remove(Constant.ENCOUNTER_DATE_EARLIER_THAN_DOB);
           
        }
        ContainerController.encounterValidationData.put(patientDemo.getPatientUuid()+"__"+encounter.getEncounterUuid(), encounterValidator);    
    }
    
    public static void ValidateObs(ObsType obs)
    {
        if(obs.getConceptId() == Constant.HIV_DIAGNOSIS_DATE_CONCEPT)
        {
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
        else if(obs.getConceptId() == Constant.RETURN_VISIT_DATE_CONCEPT)
        {
            Validator.validateReturnVisitDate(obs);
        }
    }
    
    public static void validateHIVDiagnosisDate(ObsType obs)
    {
        Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        //JSONObject errorData = encounterValidator.get(Constant);
        if(obs.getValueDatetime() != null)
        {
            encounterValidator.get(Constant.DIAGNOSIS_DATE_MISSING);  
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
    }
    
    public static void validateEnrollmentMethod(ObsType obs)
    {
        //for us to get here, we can now check if the value has something
        Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        
        if(obs.getValueCoded() != 0)
        {
            encounterValidator.remove(Constant.MISSING_ENROLLMENT_METHOD);
        }
    }
    
    public static void validatePriorART(ObsType obs)
    {
        Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        if(obs.getValueCoded() != 0)
        {
            encounterValidator.remove(Constant.PRIOR_ART_MISSING);
            //now check if prior art is transferred in with records
            if(obs.getValueCoded() == Constant.TRANSFER_IN_WITH_RECORDS)
            {
                Validator.setupPriorARTValidation(obs.getPatientUuid(), obs.getEncounterUuid());
                
            }
        }
        
    }
    
    public static void validateDateTransferred(ObsType obs)
    {
        Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
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
        
        
    }
    
    public static void validateFacTransferredFrom(ObsType obs)
    {
        Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        encounterValidator.remove(Constant.FAC_TRANSFERRED_FROM_MISSING);
    }
    public static void validateARTStartDate(ObsType obs){
        Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
        if(obs.getValueDatetime() != null)
        {//art start date is not missing
            encounterValidator.remove(Constant.ART_START_DATE_MISSING);
            
            //get the dob from lookup data 
            JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
            XMLGregorianCalendar dob = (patientLookupData.get(Constant.DOB) != null) ? (XMLGregorianCalendar)patientLookupData.get(Constant.DOB) : null;
            
            if(!Misc.isBeforeDate(obs.getValueDatetime().toGregorianCalendar(), dob.toGregorianCalendar()))
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
    }
    
    public static void validateCurrentRegimenLine(ObsType obs)
    {
        if(obs.getValueCoded() != 0)
        {
            //that means we have something
            Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
            Integer [] validRegimenConcepts = new Integer[]{164506,164513,165702,164507,164514,165703};
            List<Integer> list = Arrays.asList(validRegimenConcepts);
            
            if(list.indexOf(obs.getValueCoded()) > -1)
            {
                encounterValidator.remove(Constant.ADULT_PED_REGIMEN_LINE_MISSING);
                ContainerController.encounterValidationData.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator); 
                
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
        }
        
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
        groupObj.put("drugDuration", obs.getValueCoded());
        Validator.validateMedicationDuration(obs);
        
    }
    
    public static void validateMedicationDuration(ObsType obs)
    {
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        JSONObject obj = (JSONObject) patientLookupData.get(obs.getPatientUuid());
        JSONObject groupObj = (JSONObject)obj.get("duration");
        if(groupObj.containsKey("regimen") && groupObj.containsKey("drugDuration"))
        {
            //get the validation and validate
            Map<String, JSONObject> encounterValidator = ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid());
            int duration = (int)groupObj.get("drugDuration");
            if(duration > 0)
            {
                encounterValidator.remove(Constant.MEDICATION_DURATION_EMPTY);
                
                if(duration <= 180)
                {
                    encounterValidator.remove(Constant.MEDICATION_DURATION_MORE_THAN_180_DAYS);
                }
            }
            else{//it means the medication is inded empty
                encounterValidator.remove(Constant.MEDICATION_DURATION_MORE_THAN_180_DAYS);
            }
            
            ContainerController.encounterValidationData.put(obs.getPatientUuid()+"__"+obs.getEncounterUuid(), encounterValidator);
            
        }
    }
    
    public static void validateReturnVisitDate(ObsType obs)
    {
        //get the encounter for this obs and if the encounter is of type pharmacy, then validate it
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        JSONObject patientEncounters = (JSONObject)patientLookupData.get("encounters");
        EncounterType encounter = (EncounterType)patientEncounters.get(obs.getEncounterUuid());
        if(encounter.getEncounterTypeId() == Constant.PHARMACY_FORM)
        {
            if(obs.getValueDatetime() != null)
            {
                ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.RETURN_VISIT_DATE_MISSING);
            }
        }
    }
    
    public static void validateDateReported(ObsType obs)
    {
        JSONObject patientLookupData = ContainerController.lookupData.get(obs.getPatientUuid());
        JSONObject patientEncounters = (JSONObject)patientLookupData.get("encounters");
        EncounterType encounter = (EncounterType)patientEncounters.get(obs.getEncounterUuid());
        if(encounter.getEncounterTypeId() == Constant.LAB_ORDER_AND_RESULT_FORM)
        {
            if(obs.getValueDatetime() != null)
            {
                ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.RETURN_VISIT_DATE_MISSING);
                if(!Misc.isInFuture(obs.getValueDatetime().toGregorianCalendar()))
                {
                    ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.REPORTED_DATE_IN_FUTURE);
                }
                
                
                
                //lets put the date reported in lookup date so that we can check whether it is before order date
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
        groupObj.put("drugDuration", obs.getValueCoded());
        Validator.validateMedicationDuration(obs);
                
            }
            else
            {//if date reported is missing, then we cannot have any of these errors
                ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.REPORTED_DATE_IN_FUTURE);
                ContainerController.encounterValidationData.get(obs.getPatientUuid()+"__"+obs.getEncounterUuid()).remove(Constant.REPORTED_DATE_BEFORE_ORDER_DATE);
            }
        }
    }
}
