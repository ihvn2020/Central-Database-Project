/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.controller;

import ihvn.data.extractor.model.dao.Misc;
import ihvn.data.extractor.model.dao.PatientBiometricDAO;
import ihvn.data.extractor.model.dao.PatientIdentifierDAO;
import ihvn.data.extractor.model.dao.PatientProgramDAO;
import ihvn.data.extractor.model.dao.VisitDAO;
import ihvn.data.extractor.model.xml.Container;
import ihvn.data.extractor.model.xml.DemographicsType;
import ihvn.data.extractor.model.xml.EncounterType;
import ihvn.data.extractor.model.xml.MessageDataType;
import ihvn.data.extractor.model.xml.MessageHeaderType;
import ihvn.data.extractor.model.xml.ObsType;
import ihvn.data.extractor.model.xml.PatientBiometricType;
import ihvn.data.extractor.model.xml.PatientIdentifierType;
import ihvn.data.extractor.model.xml.PatientProgramType;
import ihvn.data.extractor.model.xml.VisitType;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author rsuth
 */
public class ContainerController {
    
    Map<String, String> patientDetails;
    
    public ContainerController(Map<String, String> patientDetails)
    {
        this.patientDetails = patientDetails;
    }
    
    //build container
    public Container buildContainer()
    {
        Container container = new Container();
        container.setMessageHeader(this.buildMessageHeader());
        container.setMessageData(this.buildMessageData());
        
        return container;
    }
    
    
    //build message header type
    private MessageHeaderType buildMessageHeader()
    {
        
        MessageHeaderType messageHeader = new MessageHeaderType();
        messageHeader.setFacilityDatimCode(MainController.datimId);
        messageHeader.setFacilityName(MainController.patientFacilityName);
        messageHeader.setMessageCreationDateTime(Misc.getXMLdateTime(new Date()));
        messageHeader.setMessageSchemaVersion(new BigDecimal("1.0"));
        messageHeader.setMessageStatusCode("EXPORTED");
        messageHeader.setMessageUniqueID(UUID.randomUUID().toString());
        messageHeader.setMessageSource("NMRS");
        return messageHeader;
    }
    
    //build message data
    private MessageDataType buildMessageData()
    {
        MessageDataType messageData = new MessageDataType();
        messageData.setDemographics(this.buildDemographics());
        messageData.getVisits().addAll(this.buildVisits());
        
        return messageData;
        
    }
    
    //build demographics 
    private DemographicsType buildDemographics()
    {
        DemographicsType demo = new DemographicsType();
        try{
            
            demo.setAddress1(Misc.encrypt(patientDetails.get("address1")));
            demo.setAddress2(Misc.encrypt(patientDetails.get("address2")));
            
            
            
            XMLGregorianCalendar dob = null;
            XMLGregorianCalendar deathDate = null;
            if(patientDetails.get("birthDate") != null)
            {
                dob = Misc.getXMLdate(new SimpleDateFormat("yyyy-MM-dd").parse(patientDetails.get("birthDate")));
            }
           
            demo.setBirthdate(dob);
            demo.setBirthdateEstimated(Integer.parseInt(patientDetails.get("birthDateEstimated")));
            demo.setCityVillage("");
            demo.setCountry("");
            demo.setDead(Integer.parseInt(patientDetails.get("dead")));
             if(patientDetails.get("deathDate") != null)
            {
                deathDate = Misc.getXMLdate(new SimpleDateFormat("yyyy-MM-dd").parse(patientDetails.get("deathDate")));
            }
            demo.setDeathDate(deathDate);
            demo.setFirstName(patientDetails.get("firstName"));
            demo.setGender(patientDetails.get("gender"));
            demo.setLastName(patientDetails.get("lastName"));
            demo.setMiddleName(patientDetails.get("middleName"));
            
            demo.setPatientUuid(patientDetails.get("patientUUID"));
            demo.setPatientId(Integer.parseInt(patientDetails.get("patientId")));
            demo.setPhoneNumber(patientDetails.get("phone"));
            demo.setStateProvince("");
            
            demo.getPatientBiometric().addAll(this.buildPatientBiometrics());
            demo.getPatientIdentifiers().addAll(this.buildPatienIdentifiers());
            demo.getPatientProgram().addAll(this.buildPatientPrograms());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            
        return demo;
    }
    
    //build visits 
    private List<VisitType> buildVisits()
    {
        VisitDAO visitObj = new VisitDAO();
        List<VisitType> allVisits = visitObj.getAllVisits(Integer.parseInt(patientDetails.get("patientId")));
        
        return allVisits;
    }
    
    
    //build patientBiometrics
    private List<PatientBiometricType> buildPatientBiometrics()
    {
        PatientBiometricDAO biometricObj = new PatientBiometricDAO();
        List<PatientBiometricType> allBiometrics = biometricObj.getPatientBiometric(Integer.parseInt(patientDetails.get("patientId")));
         
        return allBiometrics;
    }
    
    //build patient programs
     private List<PatientProgramType> buildPatientPrograms()
    {
        PatientProgramDAO patientProgramObj = new PatientProgramDAO();
        List<PatientProgramType> allPatientPrograms = patientProgramObj.getAllPatientPrograms(Integer.parseInt(patientDetails.get("patientId")));
        
        return allPatientPrograms;
    }
     
     
    //build patient identifiers
    private List<PatientIdentifierType> buildPatienIdentifiers()
    {
        PatientIdentifierDAO patientIdentifierObj = new PatientIdentifierDAO();
        List<PatientIdentifierType> allPatientIdentifiers = patientIdentifierObj.getAllPatientIdentifiers(Integer.parseInt(patientDetails.get("patientId")));
        
        return allPatientIdentifiers;
    }
    
    //build encounters 
    private List<EncounterType> buildEncounters()
    {
        List<EncounterType> allEncounters = new ArrayList<>();
        
        return allEncounters;
    }
    
    //build obs 
    private List<ObsType> buildObs()
    {
        List<ObsType> allObs = new ArrayList<>();
        
        
        
        return allObs;
    }
    
    
    
}
