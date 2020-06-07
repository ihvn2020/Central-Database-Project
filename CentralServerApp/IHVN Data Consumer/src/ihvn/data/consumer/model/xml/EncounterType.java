/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.xml;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author lordmaul
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncounterType", propOrder = { "encounterUUID", "encounterId", "encounterTypeId", "visitId", "patient_id", "formId", "encounterDateTime", 
                    "creator", "dateCreated", "voided", "voidedBy", "voidedByName", "dateVoided", "changedBy", "dateChanged", "obsList"
                })
public class EncounterType {
    
    @XmlElement(name = "encounter_uuid")
    private String encounterUUID;
    
    @XmlElement(name = "encounter_id")
    private long encounterId;
    
     @XmlElement(name = "visit_id")
    private int visitId;
    
    @XmlElement(name = "encounter_type_id")
    private int encounterTypeId;
    
    @XmlElement(name = "patient_id")
    private int patient_id;
    
    @XmlElement(name = "form_id")
    private int formId;
    
    @XmlElement(name = "encounter_datetime")
    private XMLGregorianCalendar encounterDateTime;
    
    @XmlElement(name = "creator")
    private int creator;
     
    @XmlElement(name = "date_created")
    private XMLGregorianCalendar dateCreated;
    
    @XmlElement(name = "voided")
    private int voided;
    
    @XmlElement(name = "voided_by")
    private int voidedBy;
    
    @XmlElement(name = "voided_by_name")
    private String voidedByName;
    
    @XmlElement(name = "date_voided")
    private XMLGregorianCalendar dateVoided;
    
    
     @XmlElement(name = "changed_by")
    private int changedBy;
    
    
    @XmlElement(name = "date_changed")
    private XMLGregorianCalendar dateChanged;
    
    @XmlElement(name = "Obs")
    private List<ObsType> obsList;

    /**
     * @return the encounterUUID
     */
    public String getEncounterUUID() {
        return encounterUUID;
    }

    /**
     * @param encounterUUID the encounterUUID to set
     */
    public void setEncounterUUID(String encounterUUID) {
        this.encounterUUID = encounterUUID;
    }

    /**
     * @return the encounterId
     */
    public long getEncounterId() {
        return encounterId;
    }

    /**
     * @param encounterId the encounterId to set
     */
    public void setEncounterId(long encounterId) {
        this.encounterId = encounterId;
    }

    /**
     * @return the visitId
     */
    public int getVisitId() {
        return visitId;
    }

    /**
     * @param visitId the visitId to set
     */
    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    /**
     * @return the encounterTypeId
     */
    public int getEncounterTypeId() {
        return encounterTypeId;
    }

    /**
     * @param encounterTypeId the encounterTypeId to set
     */
    public void setEncounterTypeId(int encounterTypeId) {
        this.encounterTypeId = encounterTypeId;
    }

    /**
     * @return the patient_id
     */
    public int getPatient_id() {
        return patient_id;
    }

    /**
     * @param patient_id the patient_id to set
     */
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    /**
     * @return the formId
     */
    public int getFormId() {
        return formId;
    }

    /**
     * @param formId the formId to set
     */
    public void setFormId(int formId) {
        this.formId = formId;
    }

    /**
     * @return the encounterDateTime
     */
    public XMLGregorianCalendar getEncounterDateTime() {
        return encounterDateTime;
    }

    /**
     * @param encounterDateTime the encounterDateTime to set
     */
    public void setEncounterDateTime(XMLGregorianCalendar encounterDateTime) {
        this.encounterDateTime = encounterDateTime;
    }

    /**
     * @return the creator
     */
    public int getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(int creator) {
        this.creator = creator;
    }

    /**
     * @return the dateCreated
     */
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(XMLGregorianCalendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the voided
     */
    public int getVoided() {
        return voided;
    }

    /**
     * @param voided the voided to set
     */
    public void setVoided(int voided) {
        this.voided = voided;
    }

    /**
     * @return the voidedBy
     */
    public int getVoidedBy() {
        return voidedBy;
    }

    /**
     * @param voidedBy the voidedBy to set
     */
    public void setVoidedBy(int voidedBy) {
        this.voidedBy = voidedBy;
    }

    /**
     * @return the voidedByName
     */
    public String getVoidedByName() {
        return voidedByName;
    }

    /**
     * @param voidedByName the voidedByName to set
     */
    public void setVoidedByName(String voidedByName) {
        this.voidedByName = voidedByName;
    }

    /**
     * @return the dateVoided
     */
    public XMLGregorianCalendar getDateVoided() {
        return dateVoided;
    }

    /**
     * @param dateVoided the dateVoided to set
     */
    public void setDateVoided(XMLGregorianCalendar dateVoided) {
        this.dateVoided = dateVoided;
    }

    /**
     * @return the changedBy
     */
    public int getChangedBy() {
        return changedBy;
    }

    /**
     * @param changedBy the changedBy to set
     */
    public void setChangedBy(int changedBy) {
        this.changedBy = changedBy;
    }

    /**
     * @return the dateChanged
     */
    public XMLGregorianCalendar getDateChanged() {
        return dateChanged;
    }

    /**
     * @param dateChanged the dateChanged to set
     */
    public void setDateChanged(XMLGregorianCalendar dateChanged) {
        this.dateChanged = dateChanged;
    }

    /**
     * @return the obsList
     */
    public List<ObsType> getObsList() {
        return obsList;
    }

    /**
     * @param obsList the obsList to set
     */
    public void setObsList(List<ObsType> obsList) {
        this.obsList = obsList;
    }
    
    
    
    
    
    
    
}
