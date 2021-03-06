/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author rsuth
 */
public class ValidatorData {
    
    
    private int errorId;
    private String patientUUID;
    private String datimId;
    private String visitUUID;
    private String encounterUUID;
    private String obsUUID;
    private String errorCode;
    private String errorMessage;
    private String pmmForm;
    private String variableName;
    private String variableValue;
    private XMLGregorianCalendar visitDate;
    private int ignorePatient;
    private int ignoreForm;
    private int ignoreVariable;
    private String messageUUID;

    public ValidatorData()
    {
        
    }
    public ValidatorData(int errorId, String patientUUID, String datimId, String visitUUID, String encounterUUID, String obsUUID, String errorCode, String errorMessage, String pmmForm, String variableName, String variableValue, XMLGregorianCalendar visitDate, int ignorePatient, int ignoreForm, int ignoreVariable, String messageUUID) {
        this.errorId = errorId;
        this.patientUUID = patientUUID;
        this.datimId = datimId;
        this.visitUUID = visitUUID;
        this.encounterUUID = encounterUUID;
        this.obsUUID = obsUUID;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.pmmForm = pmmForm;
        this.variableName = variableName;
        this.variableValue = variableValue;
        this.visitDate = visitDate;
        this.ignorePatient = ignorePatient;
        this.ignoreForm = ignoreForm;
        this.ignoreVariable = ignoreVariable;
        this.messageUUID = messageUUID;
    }

    
    
    /**
     * @return the errorId
     */
    public int getErrorId() {
        return errorId;
    }

    /**
     * @param errorId the errorId to set
     */
    public void setErrorId(int errorId) {
        this.errorId = errorId;
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
     * @return the datimId
     */
    public String getDatimId() {
        return datimId;
    }

    /**
     * @param datimId the datimId to set
     */
    public void setDatimId(String datimId) {
        this.datimId = datimId;
    }

    /**
     * @return the visitUUID
     */
    public String getVisitUUID() {
        return visitUUID;
    }

    /**
     * @param visitUUID the visitUUID to set
     */
    public void setVisitUUID(String visitUUID) {
        this.visitUUID = visitUUID;
    }

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
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the pmmForm
     */
    public String getPmmForm() {
        return pmmForm;
    }

    /**
     * @param pmmForm the pmmForm to set
     */
    public void setPmmForm(String pmmForm) {
        this.pmmForm = pmmForm;
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
     * @return the visitDate
     */
    public XMLGregorianCalendar getVisitDate() {
        return visitDate;
    }

    /**
     * @param visitDate the visitDate to set
     */
    public void setVisitDate(XMLGregorianCalendar visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * @return the ignorePatient
     */
    public int getIgnorePatient() {
        return ignorePatient;
    }

    /**
     * @param ignorePatient the ignorePatient to set
     */
    public void setIgnorePatient(int ignorePatient) {
        this.ignorePatient = ignorePatient;
    }

    /**
     * @return the ignoreForm
     */
    public int getIgnoreForm() {
        return ignoreForm;
    }

    /**
     * @param ignoreForm the ignoreForm to set
     */
    public void setIgnoreForm(int ignoreForm) {
        this.ignoreForm = ignoreForm;
    }

    /**
     * @return the ignoreVariable
     */
    public int getIgnoreVariable() {
        return ignoreVariable;
    }

    /**
     * @param ignoreVariable the ignoreVariable to set
     */
    public void setIgnoreVariable(int ignoreVariable) {
        this.ignoreVariable = ignoreVariable;
    }

    /**
     * @return the messageUUID
     */
    public String getMessageUUID() {
        return messageUUID;
    }

    /**
     * @param messageUUID the messageUUID to set
     */
    public void setMessageUUID(String messageUUID) {
        this.messageUUID = messageUUID;
    }
    
    
}
