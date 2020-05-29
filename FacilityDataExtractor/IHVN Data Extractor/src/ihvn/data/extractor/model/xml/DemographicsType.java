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
@XmlType(name = "DemographicsType", propOrder = { "patientIdentifiers", "patientBiometric", "patientProgram", "patient_Id", "firstName",
    "lastName", "middleName", "gender",  "birthDate", "birthDateEstimated", "dead", "deathDate", "patientUUID",
    "phoneNumber", "address1", "address2", "cityVillage", "stateProvince", "country"})
public class DemographicsType {
    
    @XmlElement(name = "PatientIdentifiers")
    private List<PatientIdentifierType> patientIdentifiers;
    
    @XmlElement(name = "PatientBiometric")
    private List<PatientBiometricType> patientBiometric;
    
    @XmlElement(name = "PatientProgram")
    private List<PatientProgramType> patientProgram;
    
    @XmlElement(name = "patient_id")
    private int patient_Id;
    
    @XmlElement(name = "first_name")
    private String firstName;
    
    @XmlElement(name = "last_name")
    private String lastName;
    
    @XmlElement(name = "middle_name")
    private String middleName;
    
    @XmlElement(name = "gender")
    private String gender;
    
    @XmlElement(name = "birth_date")
    private XMLGregorianCalendar birthDate;
    
    @XmlElement(name = "birthdate_estimated")
    private int birthDateEstimated;
    
    @XmlElement(name = "dead")
    private int dead;
    
    @XmlElement(name = "death_date")
    private XMLGregorianCalendar deathDate;
    
    @XmlElement(name = "patient_uuid")
    private String patientUUID;
    
    @XmlElement(name = "phone_number")
    private String phoneNumber;
    
    @XmlElement(name = "address1")
    private String address1;
    
    @XmlElement(name = "address2")
    private String address2;
    
    @XmlElement(name = "city_village")
    private String cityVillage;
    
    @XmlElement(name = "state_province")
    private String stateProvince;
    
    @XmlElement(name = "country")
    private String country;

    /**
     * @return the patientIdentifiers
     */
    public List<PatientIdentifierType> getPatientIdentifiers() {
        return patientIdentifiers;
    }

    /**
     * @param patientIdentifiers the patientIdentifiers to set
     */
    public void setPatientIdentifiers(List<PatientIdentifierType> patientIdentifiers) {
        this.patientIdentifiers = patientIdentifiers;
    }

    /**
     * @return the patientBiometric
     */
    public List<PatientBiometricType> getPatientBiometric() {
        return patientBiometric;
    }

    /**
     * @param patientBiometric the patientBiometric to set
     */
    public void setPatientBiometric(List<PatientBiometricType> patientBiometric) {
        this.patientBiometric = patientBiometric;
    }

    /**
     * @return the patientProgram
     */
    public List<PatientProgramType> getPatientProgram() {
        return patientProgram;
    }

    /**
     * @param patientProgram the patientProgram to set
     */
    public void setPatientProgram(List<PatientProgramType> patientProgram) {
        this.patientProgram = patientProgram;
    }

    /**
     * @return the patient_Id
     */
    public int getPatient_Id() {
        return patient_Id;
    }

    /**
     * @param patient_Id the patient_Id to set
     */
    public void setPatient_Id(int patient_Id) {
        this.patient_Id = patient_Id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the birthDate
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(XMLGregorianCalendar birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the birthDateEstimated
     */
    public int getBirthDateEstimated() {
        return birthDateEstimated;
    }

    /**
     * @param birthDateEstimated the birthDateEstimated to set
     */
    public void setBirthDateEstimated(int birthDateEstimated) {
        this.birthDateEstimated = birthDateEstimated;
    }

    /**
     * @return the dead
     */
    public int getDead() {
        return dead;
    }

    /**
     * @param dead the dead to set
     */
    public void setDead(int dead) {
        this.dead = dead;
    }

    /**
     * @return the deathDate
     */
    public XMLGregorianCalendar getDeathDate() {
        return deathDate;
    }

    /**
     * @param deathDate the deathDate to set
     */
    public void setDeathDate(XMLGregorianCalendar deathDate) {
        this.deathDate = deathDate;
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
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the cityVillage
     */
    public String getCityVillage() {
        return cityVillage;
    }

    /**
     * @param cityVillage the cityVillage to set
     */
    public void setCityVillage(String cityVillage) {
        this.cityVillage = cityVillage;
    }

    /**
     * @return the stateProvince
     */
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * @param stateProvince the stateProvince to set
     */
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
    
    
    
    
}
