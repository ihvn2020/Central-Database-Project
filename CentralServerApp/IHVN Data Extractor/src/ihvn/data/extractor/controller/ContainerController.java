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
        return messageHeader;
    }

    //build message data
    private MessageDataType buildMessageData() {
        MessageDataType messageData = new MessageDataType();
        messageData.setDemographics(this.buildDemographics());
        messageData.getVisits().addAll(this.buildVisits());

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
                demo.setDateCreated(Misc.getXMLdate(new SimpleDateFormat("yyyy-MM-dd").parse(patientDetails.get("dateCreated"))));
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
            

            demo.getPatientBiometric().addAll(this.buildPatientBiometrics());
            demo.getPatientIdentifiers().addAll(this.buildPatienIdentifiers());
            demo.getPatientProgram().addAll(this.buildPatientPrograms());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return demo;
    }

    //build visits 
    private List<VisitType> buildVisits() {
        VisitDAO visitObj = new VisitDAO();
        List<VisitType> allVisits = visitObj.getAllVisits(Integer.parseInt(patientDetails.get("patientId")));

        return allVisits;
    }

    //build patientBiometrics
    private List<PatientBiometricType> buildPatientBiometrics() {
        PatientBiometricDAO biometricObj = new PatientBiometricDAO();
        List<PatientBiometricType> allBiometrics = biometricObj.getPatientBiometric(Integer.parseInt(patientDetails.get("patientId")));

        return allBiometrics;
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
        List<EncounterType> allEncounters =dao.getAllEncountersByPatient(Integer.parseInt(patientDetails.get("patientId")));
        
        return allEncounters;
    }

    //build obs 
    private List<ObsType> buildObs() {
        VisitDAO dao=new VisitDAO();
        List<ObsType> allObs = new ArrayList<>();

        return allObs;
    }

}
