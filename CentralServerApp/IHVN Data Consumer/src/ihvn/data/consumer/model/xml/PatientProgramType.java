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
@XmlType(name = "PatientProgramType", propOrder = { "patientProgramId", "programId", "programName", "dateEnrolled", "dateCompleted",
    "outcomeConceptId", "creator", "dateCreated", "voided", "voidedBy", "voidedByName", "dateVoided", "changedBy", "dateChanged", "patientProgramUUID"
                })
public class PatientProgramType {
    
    
    
    @XmlElement(name = "patient_program_id")
    private int patientProgramId;
    
    @XmlElement(name = "program_id")
    private int programId;
    
    @XmlElement(name = "program_name")
    private String programName;
    
    @XmlElement(name = "date_enrolled")
    private XMLGregorianCalendar dateEnrolled;
    
    @XmlElement(name = "date_completed")
    private XMLGregorianCalendar dateCompleted;


    @XmlElement(name = "outcome_concept_id")
    private int outcomeConceptId;
    
    @XmlElement(name = "creator")
    private int creator;
    
    @XmlElement(name = "date_created")
    private XMLGregorianCalendar dateCreated;
    
    @XmlElement(name = "date_changed")
    private XMLGregorianCalendar dateChanged;
    
    @XmlElement(name = "changed_by")
    private int changedBy;
    
    @XmlElement(name = "voided")
    private int voided;
    
    @XmlElement(name = "voided_by")
    private int voidedBy;
    
    @XmlElement(name = "voided_by_name")
    private String voidedByName;
    
    @XmlElement(name = "date_voided")
    private XMLGregorianCalendar dateVoided;
    
    
    
    @XmlElement(name = "patient_program_uuid")
    private String patientProgramUUID;

    /**
     * @return the patientProgramId
     */
    public int getPatientProgramId() {
        return patientProgramId;
    }

    /**
     * @param patientProgramId the patientProgramId to set
     */
    public void setPatientProgramId(int patientProgramId) {
        this.patientProgramId = patientProgramId;
    }

    /**
     * @return the programId
     */
    public int getProgramId() {
        return programId;
    }

    /**
     * @param programId the programId to set
     */
    public void setProgramId(int programId) {
        this.programId = programId;
    }

    /**
     * @return the programName
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * @param programName the programName to set
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * @return the dateEnrolled
     */
    public XMLGregorianCalendar getDateEnrolled() {
        return dateEnrolled;
    }

    /**
     * @param dateEnrolled the dateEnrolled to set
     */
    public void setDateEnrolled(XMLGregorianCalendar dateEnrolled) {
        this.dateEnrolled = dateEnrolled;
    }

    /**
     * @return the dateCompleted
     */
    public XMLGregorianCalendar getDateCompleted() {
        return dateCompleted;
    }

    /**
     * @param dateCompleted the dateCompleted to set
     */
    public void setDateCompleted(XMLGregorianCalendar dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    /**
     * @return the outcomeConceptId
     */
    public int getOutcomeConceptId() {
        return outcomeConceptId;
    }

    /**
     * @param outcomeConceptId the outcomeConceptId to set
     */
    public void setOutcomeConceptId(int outcomeConceptId) {
        this.outcomeConceptId = outcomeConceptId;
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
     * @return the patientProgramUUID
     */
    public String getPatientProgramUUID() {
        return patientProgramUUID;
    }

    /**
     * @param patientProgramUUID the patientProgramUUID to set
     */
    public void setPatientProgramUUID(String patientProgramUUID) {
        this.patientProgramUUID = patientProgramUUID;
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
