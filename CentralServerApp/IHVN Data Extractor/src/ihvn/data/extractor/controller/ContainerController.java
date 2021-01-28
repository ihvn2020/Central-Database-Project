/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.controller;

import ihvn.data.extractor.model.dao.MasterDAO;
import ihvn.data.extractor.model.dao.Misc;
import ihvn.data.extractor.model.dao.PatientBiometricDAO;
import ihvn.data.extractor.model.dao.PatientDAO;
import ihvn.data.extractor.model.dao.PatientIdentifierDAO;
import ihvn.data.extractor.model.dao.PatientProgramDAO;
import ihvn.data.extractor.model.dao.VisitDAO;
import ihvn.data.extractor.model.xml.Container;
import ihvn.data.extractor.model.xml.DemographicsType;
import ihvn.data.extractor.model.xml.EncounterProviderType;
import ihvn.data.extractor.model.xml.EncounterType;
import ihvn.data.extractor.model.xml.MessageDataType;
import ihvn.data.extractor.model.xml.MessageHeaderType;
import ihvn.data.extractor.model.xml.ObsType;
import ihvn.data.extractor.model.xml.PatientBiometricType;
import ihvn.data.extractor.model.xml.PatientIdentifierType;
import ihvn.data.extractor.model.xml.PatientProgramType;
import ihvn.data.extractor.model.xml.VisitType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

    public ContainerController(Map<String, String> patientDetails) {
        this.patientDetails = patientDetails;
    }

    //build container
    public Container buildContainer() {
         
        Container container = new Container();
        container.setMessageHeader(this.buildMessageHeader());
        container.setMessageData(this.buildMessageData());
        return container;
    }

    //build message header type
    private MessageHeaderType buildMessageHeader() {

         MessageHeaderType messageHeader = new MessageHeaderType();
        messageHeader.setFacilityDatimCode(MainController.datimId);
        messageHeader.setFacilityName(MainController.patientFacilityName);
        messageHeader.setMessageCreationDateTime(Misc.getXMLdateTime(new Date()));
        messageHeader.setMessageSchemaVersion(new BigDecimal("1.0"));
        messageHeader.setMessageStatusCode("EXPORTED");
        messageHeader.setMessageUniqueID(UUID.randomUUID().toString());
        messageHeader.setMessageSource("NMRS");
        System.out.println("got here 1");
        messageHeader.setTouchTime(Misc.getXMLdateTime(buildTimestamp()));
        System.out.println("got here 2");
        return messageHeader;
    }

    //build message data
    private MessageDataType buildMessageData() {
        MessageDataType messageData = new MessageDataType();
        messageData.setDemographics(this.buildDemographics());
         messageData.getVisits().addAll(this.buildVisits());
        messageData.getEncounters().addAll(this.buildEncounters());
        messageData.getObs().addAll(this.buildObs());
        messageData.getPatientBiometrics().addAll(this.buildPatientBiometrics());
        messageData.getEncounterProviders().addAll(this.buildEncounterProviders());
        messageData.getPatientIdentifiers().addAll(this.buildPatienIdentifiers());
        messageData.getPatientPrograms().addAll(this.buildPatientPrograms());
        return messageData;

    }

    //build demographics 
    private DemographicsType buildDemographics() {
        DemographicsType demo = new DemographicsType();
        try {
            if (patientDetails.get("address1") != null) {
                demo.setAddress1(Misc.encrypt(patientDetails.get("address1")));
            }
            if (patientDetails.get("address2") != null) {
                demo.setAddress2(Misc.encrypt(patientDetails.get("address2")));
            }

            demo.setCityVillage(patientDetails.get("cityVillage"));
            demo.setStateProvince(patientDetails.get("stateProvince"));
            demo.setCountry(patientDetails.get("country"));
            if (patientDetails.get("voided") != null) {
                demo.setVoided(Integer.parseInt(patientDetails.get("voided")));
            }
            if (patientDetails.get("voidedBy") != null) {
                demo.setVoidedBy(Integer.parseInt(patientDetails.get("voidedBy")));
            }
            if (patientDetails.get("dateVoided") != null) {
                demo.setDateVoided(Misc.getXMLdate(new SimpleDateFormat("yyyy-MM-dd").parse(patientDetails.get("dateVoided"))));
            }
            demo.setCauseOfDeath(patientDetails.get("causeOfDeath"));
            if (patientDetails.get("creator") != null) {
                demo.setCreator(Integer.parseInt(patientDetails.get("creator")));
            }

            if (patientDetails.get("dateCreated") != null) {
                demo.setDateCreated(Misc.getXMLdateTime(new SimpleDateFormat("yyyy-MM-dd").parse(patientDetails.get("dateCreated"))));
            }
            demo.setVoidedReason(patientDetails.get("voidReason"));
            demo.setDeathdateEstimated(Integer.parseInt(patientDetails.get("deathdateEstimated")));

            XMLGregorianCalendar dob = null;
            XMLGregorianCalendar deathDate = null;
            if (patientDetails.get("birthDate") != null) {
                dob = Misc.getXMLdate(new SimpleDateFormat("yyyy-MM-dd").parse(patientDetails.get("birthDate")));
            }

            demo.setBirthdate(dob);
            if (patientDetails.get("birthDateEstimated") != null) {
                demo.setBirthdateEstimated(Integer.parseInt(patientDetails.get("birthDateEstimated")));
            }
            if (patientDetails.get("dead") != null) {
                demo.setDead(Integer.parseInt(patientDetails.get("dead")));
            }

            if (patientDetails.get("deathDate") != null) {
                deathDate = Misc.getXMLdate(new SimpleDateFormat("yyyy-MM-dd").parse(patientDetails.get("deathDate")));
            }
            demo.setDeathDate(deathDate);
            if (patientDetails.get("firstName") != null) {
                demo.setFirstName(Misc.encrypt(patientDetails.get("firstName")));
            }
            if (patientDetails.get("lastName") != null) {
                demo.setLastName(Misc.encrypt(patientDetails.get("lastName")));
            }
            if (patientDetails.get("middleName") != null) {
                demo.setMiddleName(Misc.encrypt(patientDetails.get("middleName")));
            }
            
            if(patientDetails.get("phone")!=null){
                demo.setPhoneNumber(Misc.encrypt(patientDetails.get("phone")));
            }
            demo.setGender(patientDetails.get("gender"));

            demo.setPatientUuid(patientDetails.get("patientUUID"));
            demo.setPatientId(Integer.parseInt(patientDetails.get("patientId")));
            demo.setDatimId(MainController.datimId);
            
          

           // demo.getPatientBiometric().addAll(this.buildPatientBiometrics());
            //demo.getPatientIdentifiers().addAll(this.buildPatienIdentifiers());
            //demo.getPatientProgram().addAll(this.buildPatientPrograms());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return demo;
    }

    //build visits 
    private List<VisitType> buildVisits() {
        VisitDAO visitObj = new VisitDAO();
        List<VisitType> allVisits = visitObj.getAllVisits(Integer.parseInt(patientDetails.get("patientId")),patientDetails.get("patientUUID"),MainController.datimId);

        return allVisits;
    }

    //build patientBiometrics
    private List<PatientBiometricType> buildPatientBiometrics() {
        PatientBiometricDAO biometricObj = new PatientBiometricDAO();
        List<PatientBiometricType> allBiometrics = biometricObj.getPatientBiometric(Integer.parseInt(patientDetails.get("patientId")));

        return allBiometrics;
    }
    
    private Date buildTimestamp(){
         Date lastUpdateTimestamp=null;
      try{
         MasterDAO dao=new PatientDAO();
        PatientDAO pdao=new PatientDAO();
        VisitDAO vdao=new VisitDAO();
        PatientBiometricDAO bdao=new PatientBiometricDAO();
        PatientIdentifierDAO pidao=new PatientIdentifierDAO();
        PatientProgramDAO prgDao=new PatientProgramDAO();
       
        List<Date> dateList=new ArrayList<Date>();
        int patientID=Integer.parseInt(patientDetails.get("patientId"));
        /*
           This section of the code checks if there is a change in any of the tables
           patient,person,person_address,person_name
        */
        
        dateList.add(pdao.getPatientTimestamp(patientID));
        dateList.add(pdao.getPersonAddressTimestamp(patientID));
        dateList.add(pdao.getPersonNameTimestamp(patientID));
        dateList.add(pdao.getPersonTimestamp(patientID));
        
        /*
          This section of the code checks if there is change in
          obs, encounter and visit
        */
        dateList.add(vdao.getEncounterTimestamp(patientID));
        dateList.add(vdao.getObsTimestamp(patientID));
        dateList.add(vdao.getVisitTimestamp(patientID));
        
        /*
          Check if there is a change in identifier and program
        */
        dateList.add(pidao.getPatientIdentifierTimestamp(patientID));
        dateList.add(prgDao.getPatientProgramTimestamp(patientID));
        dateList.removeAll(Collections.singletonList(null));
        lastUpdateTimestamp=Collections.max(dateList);
        if(lastUpdateTimestamp==null){
            try{
            lastUpdateTimestamp=new SimpleDateFormat("yyyyMMdd").parse("19900101");
            }catch(ParseException pe)
            {
                pe.printStackTrace();
            }
        }
      }catch(Exception e)
      {
          try{
            lastUpdateTimestamp=new SimpleDateFormat("yyyyMMdd").parse("19900101");
            }catch(ParseException pe)
            {
                pe.printStackTrace();
            }
      }
        
        
        return lastUpdateTimestamp;
    }

    //build patient programs
    private List<PatientProgramType> buildPatientPrograms() {
        PatientProgramDAO patientProgramObj = new PatientProgramDAO();
        List<PatientProgramType> allPatientPrograms = patientProgramObj.getAllPatientPrograms(Integer.parseInt(patientDetails.get("patientId")));

        return allPatientPrograms;
    }

    //build patient identifiers
    private List<PatientIdentifierType> buildPatienIdentifiers() {
        PatientIdentifierDAO patientIdentifierObj = new PatientIdentifierDAO();
        List<PatientIdentifierType> allPatientIdentifiers = patientIdentifierObj.getAllPatientIdentifiers(Integer.parseInt(patientDetails.get("patientId")));

        return allPatientIdentifiers;
    }

    //build encounters 
    private List<EncounterType> buildEncounters() {
        VisitDAO dao=new VisitDAO();
        List<EncounterType> allEncounters =dao.getAllEncountersByPatient(Integer.parseInt(patientDetails.get("patientId")),patientDetails.get("patientUUID"),MainController.datimId);
        
        return allEncounters;
    }

    //build obs 
    private List<ObsType> buildObs() {
        VisitDAO dao=new VisitDAO();
        List<ObsType> allObs = dao.getAllObsByPatient(Integer.parseInt(patientDetails.get("patientId")),patientDetails.get("patientUUID"),MainController.datimId);

        return allObs;
    }
    private List<EncounterProviderType> buildEncounterProviders(){
        VisitDAO dao=new VisitDAO();
        List<EncounterProviderType> encounterProviderList=dao.getAllEncountersProvidersByPatient(Integer.parseInt(patientDetails.get("patientId")),patientDetails.get("patientUUID"),MainController.datimId);
        return encounterProviderList;
    }
    
   

}
