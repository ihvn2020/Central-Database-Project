/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.controller;

import ihvn.data.consumer.model.dao.FacilityDAO;
import ihvn.data.consumer.model.dao.PatientBiometricDAO;
import ihvn.data.consumer.model.dao.PatientDAO;
import ihvn.data.consumer.model.dao.PatientIdentifierDAO;
import ihvn.data.consumer.model.dao.VisitDAO;
import ihvn.data.consumer.model.xml.Container;
import ihvn.data.consumer.model.xml.DemographicsType;
import ihvn.data.consumer.model.xml.MessageDataType;
import ihvn.data.consumer.model.xml.MessageHeaderType;
import ihvn.data.consumer.model.xml.PatientBiometricType;
import ihvn.data.consumer.model.xml.PatientIdentifierType;
import ihvn.data.consumer.model.xml.VisitType;
import java.util.List;

/**
 *
 * @author rsuth
 */
public class ContainerController {
    
    private int facilityId = 0;
    private Container container;
    private long patientId = 0;
    
    //visit 
    public void saveContainer(Container cnt)
    {
        this.container = cnt; 
        MessageHeaderType messageHeader = this.container.getMessageHeader(); 
        MessageDataType messageData = this.container.getMessageData();
        //get the facility
        
        facilityId = FacilityDAO.getFacilityWithDatimCode(messageHeader.getFacilityDatimCode());
        
        //save patient demographics details
        DemographicsType demo = messageData.getDemographics();
        
        patientId = PatientDAO.getPatientIdWithUUID(demo.getPatientUUID());
    
        patientId = PatientDAO.insertOrUpdatePatient(patientId, facilityId, demo);
        
        //save patient biometrics
        if(this.container.getMessageData().getDemographics().getPatientBiometric() != null){
             this.savePatientBiometrics();
        }
       
        //save the patient identifiers
        this.savePatientIdentifiers();
        
        //save visits
        this.saveVisits();
        
    }
    
    
    private void savePatientBiometrics()
    {
        List<PatientBiometricType> biometrics = this.container.getMessageData().getDemographics().getPatientBiometric();
        //first delete any existing biometrics record for ths patient 
        PatientBiometricDAO.deleteExisitingBiometrics(patientId);
        //now insert the biometrics afresh
        PatientBiometricDAO.savePatientBiometrics(patientId, biometrics);
    }
    
    
    
    private void savePatientIdentifiers()
    {
        List<PatientIdentifierType> identifiers = this.container.getMessageData().getDemographics().getPatientIdentifiers();
        PatientIdentifierDAO.deleteExisitingIdentifiers(patientId);
        PatientIdentifierDAO.savePatientIdentifiers(patientId, identifiers);
    }
    
    private void saveVisits()
    {
        List<VisitType> allVisits = this.container.getMessageData().getVisits();
        VisitDAO.deleteVisits(patientId);
      
        long firstVisitId = VisitDAO.saveVisits(patientId, facilityId, allVisits);

        // save the encounters

        long firstEncounterId = VisitDAO.saveEncounters(patientId, firstVisitId, allVisits);

        //save obs
        VisitDAO.saveObs(patientId, firstEncounterId, allVisits);
        
    }
    
   
    
    
    
    
    
    
}
