/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.xml;

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
@XmlType(name = "VisitType", propOrder = { "visitId", "patientId", "patientUUID", "visitTypeId", "dateStarted",
    "dateStopped", "creator", "dateCreated", "voided", "voidedBy", "voidedByName", "dateVoided", "changedBy", "dateChanged", "visit_uuid", "Encounter"})
public class VisitType {
    @XmlElement(name = "visit_id")
    private int visitId;
    
    @XmlElement(name = "patient_id")
    private int patientId;
    
    @XmlElement(name = "patient_uuid")
    private String patientUUID;
    
    
    @XmlElement(name = "visit_type_id")
    private int visitTypeId;
    
    
    @XmlElement(name = "date_started")
    private XMLGregorianCalendar dateStarted;
    
    @XmlElement(name = "date_stopped")
    private XMLGregorianCalendar dateStopped;
    
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
    
    @XmlElement(name = "visit_uuid")
    private String visit_uuid;
    
    @XmlElement(name = "Encounter")
    private List<EncounterType> Encounter;

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
     * @return the patientId
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the patientUUID
     */
    public String getPatientUUID() {
        return patientUUID;
    }

    /**
     * @param patientUUID the patientUUID to set
     */
    public void setPatientUUID(String patientUUID) {
        this.patientUUID = patientUUID;
    }

    /**
     * @return the visitTypeId
     */
    public int getVisitTypeId() {
        return visitTypeId;
    }

    /**
     * @param visitTypeId the visitTypeId to set
     */
    public void setVisitTypeId(int visitTypeId) {
        this.visitTypeId = visitTypeId;
    }

    /**
     * @return the dateStarted
     */
    public XMLGregorianCalendar getDateStarted() {
        return dateStarted;
    }

    /**
     * @param dateStarted the dateStarted to set
     */
    public void setDateStarted(XMLGregorianCalendar dateStarted) {
        this.dateStarted = dateStarted;
    }

    /**
     * @return the dateStopped
     */
    public XMLGregorianCalendar getDateStopped() {
        return dateStopped;
    }

    /**
     * @param dateStopped the dateStopped to set
     */
    public void setDateStopped(XMLGregorianCalendar dateStopped) {
        this.dateStopped = dateStopped;
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
     * @return the visit_uuid
     */
    public String getVisit_uuid() {
        return visit_uuid;
    }

    /**
     * @param visit_uuid the visit_uuid to set
     */
    public void setVisit_uuid(String visit_uuid) {
        this.visit_uuid = visit_uuid;
    }

    /**
     * @return the Encounter
     */
    public List<EncounterType> getEncounter() {
        return Encounter;
    }

    /**
     * @param Encounter the Encounter to set
     */
    public void setEncounter(List<EncounterType> Encounter) {
        this.Encounter = Encounter;
    }
    
    
    
    
}
