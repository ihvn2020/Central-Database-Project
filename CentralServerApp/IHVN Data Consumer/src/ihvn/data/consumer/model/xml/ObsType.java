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
@XmlType(name = "ObsType", propOrder = { "obsUUID", "obsId", "personId", "conceptId", "encounterId", "formId", "encounterType",
                    "obsDateTime", "obsGroupId", "valueCoded", "valueDateTime", "valueNumeric", "valueText", "creator", "dateCreated", 
                    "voided", "voidedBy", "voidedByName", "dateVoided", "variableValue", "variableName"
                })
public class ObsType {
     
    @XmlElement(name = "obs_uuid")
    private String obsUUID;
    
    @XmlElement(name = "obs_id")
    private long obsId;
    
    @XmlElement(name = "person_id")
    private long personId;
    
    @XmlElement(name = "concept_id")
    private int conceptId;
    
    @XmlElement(name = "encounter_id")
    private long encounterId;
    
    @XmlElement(name = "form_id")
    private int formId;
    
    @XmlElement(name = "encounter_type")
    private int encounterType;
    
    @XmlElement(name = "obs_datetime")
    private XMLGregorianCalendar obsDateTime;
    
    @XmlElement(name = "obs_group_id")
    private int obsGroupId;
    
    @XmlElement(name = "value_coded")
    private int valueCoded;
    
    
    @XmlElement(name = "value_datetime")
    private XMLGregorianCalendar valueDateTime;
    
    @XmlElement(name = "value_numeric")
    private double valueNumeric;
    
    @XmlElement(name = "value_text")
    private String valueText;
    
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
    
    @XmlElement(name = "variable_value")
    private String variableValue;
    
    @XmlElement(name = "variable_name")
    private String variableName;

    /**
     * @return the obsUUID
     */
    public String getObsUUID() {
        return obsUUID;
    }

    /**
     * @param obsUUID the obsUUID to set
     */
    public void setObsUUID(String obsUUID) {
        this.obsUUID = obsUUID;
    }

    /**
     * @return the obsId
     */
    public long getObsId() {
        return obsId;
    }

    /**
     * @param obsId the obsId to set
     */
    public void setObsId(long obsId) {
        this.obsId = obsId;
    }

    /**
     * @return the personId
     */
    public long getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(long personId) {
        this.personId = personId;
    }

    /**
     * @return the conceptId
     */
    public int getConceptId() {
        return conceptId;
    }

    /**
     * @param conceptId the conceptId to set
     */
    public void setConceptId(int conceptId) {
        this.conceptId = conceptId;
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
     * @return the encounterType
     */
    public int getEncounterType() {
        return encounterType;
    }

    /**
     * @param encounterType the encounterType to set
     */
    public void setEncounterType(int encounterType) {
        this.encounterType = encounterType;
    }

    /**
     * @return the obsDateTime
     */
    public XMLGregorianCalendar getObsDateTime() {
        return obsDateTime;
    }

    /**
     * @param obsDateTime the obsDateTime to set
     */
    public void setObsDateTime(XMLGregorianCalendar obsDateTime) {
        this.obsDateTime = obsDateTime;
    }

    /**
     * @return the obsGroupId
     */
    public int getObsGroupId() {
        return obsGroupId;
    }

    /**
     * @param obsGroupId the obsGroupId to set
     */
    public void setObsGroupId(int obsGroupId) {
        this.obsGroupId = obsGroupId;
    }

    /**
     * @return the valueCoded
     */
    public int getValueCoded() {
        return valueCoded;
    }

    /**
     * @param valueCoded the valueCoded to set
     */
    public void setValueCoded(int valueCoded) {
        this.valueCoded = valueCoded;
    }

    /**
     * @return the valueDateTime
     */
    public XMLGregorianCalendar getValueDateTime() {
        return valueDateTime;
    }

    /**
     * @param valueDateTime the valueDateTime to set
     */
    public void setValueDateTime(XMLGregorianCalendar valueDateTime) {
        this.valueDateTime = valueDateTime;
    }

    /**
     * @return the valueNumeric
     */
    public double getValueNumeric() {
        return valueNumeric;
    }

    /**
     * @param valueNumeric the valueNumeric to set
     */
    public void setValueNumeric(double valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    /**
     * @return the valueText
     */
    public String getValueText() {
        return valueText;
    }

    /**
     * @param valueText the valueText to set
     */
    public void setValueText(String valueText) {
        this.valueText = valueText;
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
     * @return the variableValue
     */
    public String getVariableValue() {
        return variableValue;
    }

    /**
     * @param variableValue the variableValue to set
     */
    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }

    /**
     * @return the variableName
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * @param variableName the variableName to set
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }
    
    
    
}
