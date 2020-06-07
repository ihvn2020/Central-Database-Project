/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.xml;

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
@XmlType(name = "PatientIdentifierType", propOrder = { "patientIdentifierId", "patientId", "identifier", "identifierType", 
    "preferred", "creator", "dateCreated", "voided", "voidedBy", "voidedByName", "dateVoided", "changedBy", "dateChanged", "patientIdentifierUUID"
                })
public class PatientIdentifierType {
    
    @XmlElement(name = "patient_identifier_id")
    private long patientIdentifierId;
    
    @XmlElement(name = "patient_id")
    private long patientId;
    
    @XmlElement(name = "identifier")
    private String identifier;
    
    @XmlElement(name = "identifier_type")
    private int identifierType;
    
    @XmlElement(name = "preferred")
    private int preferred;
    
    @XmlElement(name = "creator")
    private long creator;
    
    @XmlElement(name = "date_created")
    private XMLGregorianCalendar dateCreated;
    
    @XmlElement(name = "date_changed")
    private XMLGregorianCalendar dateChanged;
    
    @XmlElement(name = "changed_by")
    private long changedBy;
    
    @XmlElement(name = "voided")
    private int voided;
    
    @XmlElement(name = "voided_by")
    private long voidedBy;
    
    @XmlElement(name = "voided_by_name")
    private String voidedByName;
    
    @XmlElement(name = "date_voided")
    private XMLGregorianCalendar dateVoided;
    
    @XmlElement(name = "patient_identifier_uuid")
    private String patientIdentifierUUID;

    /**
     * @return the patientIdentifierId
     */
    public long getPatientIdentifierId() {
        return patientIdentifierId;
    }

    /**
     * @param patientIdentifierId the patientIdentifierId to set
     */
    public void setPatientIdentifierId(long patientIdentifierId) {
        this.patientIdentifierId = patientIdentifierId;
    }

    /**
     * @return the patientId
     */
    public long getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the identifierType
     */
    public int getIdentifierType() {
        return identifierType;
    }

    /**
     * @param identifierType the identifierType to set
     */
    public void setIdentifierType(int identifierType) {
        this.identifierType = identifierType;
    }

    /**
     * @return the preferred
     */
    public int getPreferred() {
        return preferred;
    }

    /**
     * @param preferred the preferred to set
     */
    public void setPreferred(int preferred) {
        this.preferred = preferred;
    }

    /**
     * @return the creator
     */
    public long getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(long creator) {
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
     * @return the changedBy
     */
    public long getChangedBy() {
        return changedBy;
    }

    /**
     * @param changedBy the changedBy to set
     */
    public void setChangedBy(long changedBy) {
        this.changedBy = changedBy;
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
    public long getVoidedBy() {
        return voidedBy;
    }

    /**
     * @param voidedBy the voidedBy to set
     */
    public void setVoidedBy(long voidedBy) {
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
     * @return the patientIdentifierUUID
     */
    public String getPatientIdentifierUUID() {
        return patientIdentifierUUID;
    }

    /**
     * @param patientIdentifierUUID the patientIdentifierUUID to set
     */
    public void setPatientIdentifierUUID(String patientIdentifierUUID) {
        this.patientIdentifierUUID = patientIdentifierUUID;
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

    
    
    
}
