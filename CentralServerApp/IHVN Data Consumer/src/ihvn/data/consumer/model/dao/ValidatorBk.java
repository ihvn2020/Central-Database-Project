/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.DemographicsType;
import ihvn.data.consumer.model.xml.EncounterType;
import ihvn.data.consumer.model.xml.VisitType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author rsuth
 */
public class ValidatorBk {
    
    //lets put the encounter map here. basically this map will be multi dimensional with the first key as the patient uuid and second key as encounter uuid
    //next keys will be all the other validations. 
    
    public static Map<String, Map<String, Map<String, Boolean>>> patientObsValidationData = new HashMap<>();
    
    public static void initPatientObsValidationData(String patientUUID)
    {
        Map<String, Map<String, Boolean>> tempMap = new HashMap<>();
        patientObsValidationData.put(patientUUID, tempMap);
    }
    
    public static void initEncounterObsValidation(String patientUUID, String encounterUUID)
    {
        Map<String, Boolean> encounterObsValidation = new HashMap<>(); 
        encounterObsValidation.put("hivDiagnosisDateMissing", true);
        encounterObsValidation.put("hivDiagnosisDateBeforeDob", true);
        encounterObsValidation.put("hivDiagnosisDateInFuture", true);
        encounterObsValidation.put("missingEnrollmentMethod", true);
        encounterObsValidation.put("priorARTEmpty", true);
        encounterObsValidation.put("diagnosisDateMissing", true);
        encounterObsValidation.put("diagnosisDateMissing", true);
        encounterObsValidation.put("diagnosisDateMissing", true);
        encounterObsValidation.put("diagnosisDateMissing", true);
        encounterObsValidation.put("diagnosisDateMissing", true);
        encounterObsValidation.put("diagnosisDateMissing", true);
        encounterObsValidation.put("diagnosisDateMissing", true);
        encounterObsValidation.put("diagnosisDateMissing", true);
        patientObsValidationData.get(patientUUID).put(encounterUUID, encounterObsValidation);
    }
    
    
    
    
    
    public static Map<Integer, Object>  validatePatient(String messageUUID, DemographicsType demo)//this will return a map of three items, 1  whether to igore the patient, 2, the query and 3 a lfist of params
    {
        String ignorePatient = "false";//whether to ignore the patient or not
        
        Map<Integer, Object> errorData = new HashMap<>();
        StringBuilder errorQuery = new StringBuilder("");
        List<ValidatorData> errorParams = new ArrayList<>();
        if(demo.getGender() == null || (!demo.getGender().equalsIgnoreCase("M") && !demo.getGender().equalsIgnoreCase("F")))
        {
            ignorePatient = "true";
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "CRITICAL", 
                    "Gender cannot be missing", "", "", "", null, 1, 0, 0,  messageUUID);
           
            errorParams.add(data);
        }
        
        //next validate dob
        if(demo.getBirthdate().toGregorianCalendar().getTime() == null)
        {
            ignorePatient = "true";
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "CRITICAL", 
                    "Date of birth cannot be empty", "", "", "", null, 1, 0, 0,  messageUUID);
            
           
            errorParams.add(data);
            
        }
        if(demo.getBirthdate().toGregorianCalendar().getTime().compareTo(new Date()) > 0)
        {
            ignorePatient = "true";
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
           
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "CRITICAL", 
                    "Date of birth cannot be in the future", "", "", "", null, 1, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        
        //check if age is above 200
        Calendar calendar = Calendar.getInstance();
	calendar.setTime(demo.getBirthdate().toGregorianCalendar().getTime());
        
        int age = Misc.getAge(calendar);
        if(age >= 200)
        {
            
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
           
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Current age cannot be more than 200", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        //if(demo.getBirthdate())
        
        
        
        
        errorData.put(1, ignorePatient);
        errorData.put(2, errorQuery);
        errorData.put(3, errorParams);
            
            
        return errorData;
    }
    
    public static Map<Integer, Object>  validateProgram(String messageUUID, int programCount, DemographicsType demo)//this will return a map of three items, 1  whether to igore the patient, 2, the query and 3 a lfist of params
    {
        String ignorePatient = "false";//whether to ignore the patient or not
        
        Map<Integer, Object> errorData = new HashMap<>();
        StringBuilder errorQuery = new StringBuilder("");
        List<ValidatorData> errorParams = new ArrayList<>();
        if(programCount == 0)
        {
            
        
           
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Patient must belong to at least one program", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        
        errorData.put(1, ignorePatient);
        errorData.put(2, errorQuery);
        errorData.put(3, errorParams);
        
        return errorData;
 
    }
    
    public static Map<Integer, Object> validateVisit(DemographicsType demo, String messageUUID, VisitType visit)
    {
        
        String ignorePatient = "false";//whether to ignore the patient or not
        
        Map<Integer, Object> errorData = new HashMap<>();
        StringBuilder errorQuery = new StringBuilder("");
        List<ValidatorData> errorParams = new ArrayList<>();
        
        if(ValidatorBk.isVisitDateStartedInFuture(visit))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Date Started cannot be in the future", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        if(ValidatorBk.isVisitDateStartedBefore1900(visit))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Date Started cannot be before 1900", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        
        if(ValidatorBk.isVisitDateStartedBeforeDob(demo, visit))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Date Started cannot be before date of birth", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        if(ValidatorBk.isVisitDateStartedEmpty( visit))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Date Started cannot be empty", "", "", "", null, 0, 0, 0,  messageUUID);
            errorParams.add(data);
        }



        if(ValidatorBk.isVisitDateEndedInFuture(visit))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Date Stopped cannot be in the future", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        if(ValidatorBk.isVisitDateStoppedBefore1900(visit))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Date Ended cannot be before 1900", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        
        if(ValidatorBk.isVisitDateEndedBeforeDob(demo, visit))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Date Started cannot be before date of birth", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        if(ValidatorBk.isVisitDateEndedEmpty( visit))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Date Started cannot be empty", "", "", "", null, 0, 0, 0,  messageUUID);
            errorParams.add(data);
        }

        
       
        errorData.put(1, ignorePatient);
        errorData.put(2, errorQuery);
        errorData.put(3, errorParams);
        
        return errorData;
    }
    
    
    
     public static Map<Integer, Object> validateEncounter(DemographicsType demo, String messageUUID, EncounterType encounter)
    {
        
        String ignorePatient = "false";//whether to ignore the patient or not
        
        Map<Integer, Object> errorData = new HashMap<>();
        StringBuilder errorQuery = new StringBuilder("");
        List<ValidatorData> errorParams = new ArrayList<>();
        
        if(ValidatorBk.isEncounterDateTimeInFuture(encounter))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Encounter date time cannot be in the future", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        if(ValidatorBk.isEncounterDateBeforeDob(demo, encounter))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Encounter date time cannot be before date of birth", "", "", "", null, 0, 0, 0,  messageUUID);
            
            errorParams.add(data);
        }
        
       
        if(ValidatorBk.isEncounterDateTimeEmpty(encounter))
        {
            errorQuery.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
            ValidatorData data = new ValidatorData(0, demo.getPatientUuid(), demo.getDatimId(), "", "", "", "WARNING", 
                    "Encounter datetime cannot be empty", "", "", "", null, 0, 0, 0,  messageUUID);
            errorParams.add(data);
        }


       
        errorData.put(1, ignorePatient);
        errorData.put(2, errorQuery);
        errorData.put(3, errorParams);
        
        return errorData;
    }
    
    
    
    
    
    
    public static boolean isVisitDateStartedInFuture(VisitType visit)
    {
        if(visit.getDateStarted().toGregorianCalendar().getTime().compareTo(new Date()) > 0)
        {
            return true;
        }
        return false;
    }
    
    public static boolean isVisitDateStartedEmpty(VisitType visit)
    {
        if(visit.getDateStarted() == null )
        {
            return true;
        }
        return false;
    }
    
    public static boolean isVisitDateStartedBeforeDob(DemographicsType demo, VisitType visit)
    {
        if(visit.getDateStarted().toGregorianCalendar().getTime().compareTo(demo.getBirthdate().toGregorianCalendar().getTime()) < 0)
        {
            return true;
        }
        return false;
    }
    
    public static boolean isVisitDateStartedBefore1900(VisitType visit)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            //String dateString = format.format( new Date());
            Date  date  = format.parse("1900-01-01");
            if(visit.getDateStarted().toGregorianCalendar().getTime().compareTo(date) < 0)
            {
                return true;
            }
            
        } catch (ParseException ex) {
           ex.printStackTrace();
        }
        return false;
    }
    
    
    public static boolean isVisitDateEndedInFuture(VisitType visit)
    {
        if(visit.getDateStopped().toGregorianCalendar().getTime().compareTo(new Date()) > 0)
        {
            return true;
        }
        return false;
    }
    
    public static boolean isVisitDateEndedEmpty(VisitType visit)
    {
        if(visit.getDateStopped()== null )
        {
            return true;
        }
        return false;
    }
    
    public static boolean isVisitDateEndedBeforeDob(DemographicsType demo, VisitType visit)
    {
        if(visit.getDateStopped().toGregorianCalendar().getTime().compareTo(demo.getBirthdate().toGregorianCalendar().getTime()) < 0)
        {
            return true;
        }
        return false;
    }
    
    public static boolean isVisitDateStoppedBefore1900(VisitType visit)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            
            //String dateString = format.format( new Date());
            Date  date  = format.parse("1900-01-01");
            if(visit.getDateStopped().toGregorianCalendar().getTime().compareTo(date) < 0)
            {
                return true;
            }
            
        } catch (ParseException ex) {
           ex.printStackTrace();
        }
        return false;
    }
    
    
    public static boolean isEncounterDateTimeInFuture(EncounterType encounter)
    {
        if(encounter.getEncounterDatetime().toGregorianCalendar().getTime().compareTo(new Date()) > 0)
        {
            return true;
        }
        return false;
    }
    
    public static boolean isEncounterDateTimeEmpty(EncounterType encounter)
    {
        if(encounter.getEncounterDatetime() == null )
        {
            return true;
        }
        return false;
    }
     
    public static boolean isEncounterDateBeforeDob(DemographicsType demo, EncounterType encounter)
    {
        if(encounter.getEncounterDatetime().toGregorianCalendar().getTime().compareTo(demo.getBirthdate().toGregorianCalendar().getTime()) < 0)
        {
            return true;
        }
        return false;
    }
    
}
