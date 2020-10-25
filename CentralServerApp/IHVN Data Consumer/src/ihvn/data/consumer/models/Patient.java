/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.models;

import ihvn.data.consumer.model.dao.Misc;
import ihvn.data.consumer.model.xml.DemographicsType;
import java.util.Calendar;
import org.joda.time.DateTime;

/**
 *
 * @author lordmaul
 */
public class Patient {
    private String patientUUID;
    private String patientOMRSID;
    private int personId;
    private String firstName;
    private String lastName;
    private String sex;
    private DateTime dob;
    private int currAgeYears;
    private int currAgeMonths;
    private String uniqueId;
    private String patientUniqueID;
    private String hospitalID;
    private String transferredIn;
    private String addressCountry;
    private String addressState;
    private String addressLGA;
    private String addressWard;
    private String addressTown;
    private String addressOther;
    private String createdBy;
    private DateTime dateCreated;
    private DateTime dateUpdated;
    private String updatedBy;
    private DateTime enrollmentDate;
    private DateTime dateConfirmedPositive;
    private String registrationPhone;
    private String contactPhoneNo;
    private int markedAsDeceased;
    private DateTime dateDeceased;
    private DateTime artStartDate;
    private int ageAtArtStartYears;
    private int ageAtArtStartMonths;
    private DateTime firstVisitDate;
    private DateTime lastVisitDate;
    private String biometricCaptured;
    private DateTime biometricCaptureDate;
    private int calendarYear;
    private int calendarQuarter;
    private int financialYear;
    private int financialQuarter;
    private int month;
    private String datimId;
    private String currentARTStatus;
    

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
     * @return the patientOMRSID
     */
    public String getPatientOMRSID() {
        return patientOMRSID;
    }

    /**
     * @param patientOMRSID the patientOMRSID to set
     */
    public void setPatientOMRSID(String patientOMRSID) {
        this.patientOMRSID = patientOMRSID;
    }

    /**
     * @return the personId
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(int personId) {
        this.personId = personId;
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
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the dob
     */
    public DateTime getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(DateTime dob) {
        this.dob = dob;
    }

    /**
     * @return the currAgeYears
     */
    public int getCurrAgeYears() {
        return currAgeYears;
    }

    /**
     * @param currAgeYears the currAgeYears to set
     */
    public void setCurrAgeYears(int currAgeYears) {
        this.currAgeYears = currAgeYears;
    }

    /**
     * @return the currAgeMonths
     */
    public int getCurrAgeMonths() {
        return currAgeMonths;
    }

    /**
     * @param currAgeMonths the currAgeMonths to set
     */
    public void setCurrAgeMonths(int currAgeMonths) {
        this.currAgeMonths = currAgeMonths;
    }

    /**
     * @return the uniqueId
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId the uniqueId to set
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return the patientUniqueID
     */
    public String getPatientUniqueID() {
        return patientUniqueID;
    }

    /**
     * @param patientUniqueID the patientUniqueID to set
     */
    public void setPatientUniqueID(String patientUniqueID) {
        this.patientUniqueID = patientUniqueID;
    }

    /**
     * @return the hospitalID
     */
    public String getHospitalID() {
        return hospitalID;
    }

    /**
     * @param hospitalID the hospitalID to set
     */
    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    /**
     * @return the transferredIn
     */
    public String getTransferredIn() {
        return transferredIn;
    }

    /**
     * @param transferredIn the transferredIn to set
     */
    public void setTransferredIn(String transferredIn) {
        this.transferredIn = transferredIn;
    }

    /**
     * @return the addressCountry
     */
    public String getAddressCountry() {
        return addressCountry;
    }

    /**
     * @param addressCountry the addressCountry to set
     */
    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    /**
     * @return the addressState
     */
    public String getAddressState() {
        return addressState;
    }

    /**
     * @param addressState the addressState to set
     */
    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    /**
     * @return the addressLGA
     */
    public String getAddressLGA() {
        return addressLGA;
    }

    /**
     * @param addressLGA the addressLGA to set
     */
    public void setAddressLGA(String addressLGA) {
        this.addressLGA = addressLGA;
    }

    /**
     * @return the addressWard
     */
    public String getAddressWard() {
        return addressWard;
    }

    /**
     * @param addressWard the addressWard to set
     */
    public void setAddressWard(String addressWard) {
        this.addressWard = addressWard;
    }

    /**
     * @return the addressTown
     */
    public String getAddressTown() {
        return addressTown;
    }

    /**
     * @param addressTown the addressTown to set
     */
    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    /**
     * @return the addressOther
     */
    public String getAddressOther() {
        return addressOther;
    }

    /**
     * @param addressOther the addressOther to set
     */
    public void setAddressOther(String addressOther) {
        this.addressOther = addressOther;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the dateCreated
     */
    public DateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the dateUpdated
     */
    public DateTime getDateUpdated() {
        return dateUpdated;
    }

    /**
     * @param dateUpdated the dateUpdated to set
     */
    public void setDateUpdated(DateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    /**
     * @return the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy the updatedBy to set
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return the enrollmentDate
     */
    public DateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    /**
     * @param enrollmentDate the enrollmentDate to set
     */
    public void setEnrollmentDate(DateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    /**
     * @return the dateConfirmedPositive
     */
    public DateTime getDateConfirmedPositive() {
        return dateConfirmedPositive;
    }

    /**
     * @param dateConfirmedPositive the dateConfirmedPositive to set
     */
    public void setDateConfirmedPositive(DateTime dateConfirmedPositive) {
        this.dateConfirmedPositive = dateConfirmedPositive;
    }

    /**
     * @return the registrationPhone
     */
    public String getRegistrationPhone() {
        return registrationPhone;
    }

    /**
     * @param registrationPhone the registrationPhone to set
     */
    public void setRegistrationPhone(String registrationPhone) {
        this.registrationPhone = registrationPhone;
    }

    /**
     * @return the contactPhoneNo
     */
    public String getContactPhoneNo() {
        return contactPhoneNo;
    }

    /**
     * @param contactPhoneNo the contactPhoneNo to set
     */
    public void setContactPhoneNo(String contactPhoneNo) {
        this.contactPhoneNo = contactPhoneNo;
    }

    /**
     * @return the markedAsDeceased
     */
    public int getMarkedAsDeceased() {
        return markedAsDeceased;
    }

    /**
     * @param markedAsDeceased the markedAsDeceased to set
     */
    public void setMarkedAsDeceased(int markedAsDeceased) {
        this.markedAsDeceased = markedAsDeceased;
    }

    /**
     * @return the dateDeceased
     */
    public DateTime getDateDeceased() {
        return dateDeceased;
    }

    /**
     * @param dateDeceased the dateDeceased to set
     */
    public void setDateDeceased(DateTime dateDeceased) {
        this.dateDeceased = dateDeceased;
    }

    /**
     * @return the artStartDate
     */
    public DateTime getArtStartDate() {
        return artStartDate;
    }

    /**
     * @param artStartDate the artStartDate to set
     */
    public void setArtStartDate(DateTime artStartDate) {
        this.artStartDate = artStartDate;
    }

    /**
     * @return the ageAtArtStartYears
     */
    public int getAgeAtArtStartYears() {
        return ageAtArtStartYears;
    }

    /**
     * @param ageAtArtStartYears the ageAtArtStartYears to set
     */
    public void setAgeAtArtStartYears(int ageAtArtStartYears) {
        this.ageAtArtStartYears = ageAtArtStartYears;
    }

    /**
     * @return the ageAtArtStartMonths
     */
    public int getAgeAtArtStartMonths() {
        return ageAtArtStartMonths;
    }

    /**
     * @param ageAtArtStartMonths the ageAtArtStartMonths to set
     */
    public void setAgeAtArtStartMonths(int ageAtArtStartMonths) {
        this.ageAtArtStartMonths = ageAtArtStartMonths;
    }

    /**
     * @return the firstVisitDate
     */
    public DateTime getFirstVisitDate() {
        return firstVisitDate;
    }

    /**
     * @param firstVisitDate the firstVisitDate to set
     */
    public void setFirstVisitDate(DateTime firstVisitDate) {
        this.firstVisitDate = firstVisitDate;
    }

    /**
     * @return the lastVisitDate
     */
    public DateTime getLastVisitDate() {
        return lastVisitDate;
    }

    /**
     * @param lastVisitDate the lastVisitDate to set
     */
    public void setLastVisitDate(DateTime lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    /**
     * @return the biometricCaptured
     */
    public String getBiometricCaptured() {
        return biometricCaptured;
    }

    /**
     * @param biometricCaptured the biometricCaptured to set
     */
    public void setBiometricCaptured(String biometricCaptured) {
        this.biometricCaptured = biometricCaptured;
    }

    /**
     * @return the biometricCaptureDate
     */
    public DateTime getBiometricCaptureDate() {
        return biometricCaptureDate;
    }

    /**
     * @param biometricCaptureDate the biometricCaptureDate to set
     */
    public void setBiometricCaptureDate(DateTime biometricCaptureDate) {
        this.biometricCaptureDate = biometricCaptureDate;
    }

    /**
     * @return the calendarYear
     */
    public int getCalendarYear() {
        return calendarYear;
    }

    /**
     * @param calendarYear the calendarYear to set
     */
    public void setCalendarYear(int calendarYear) {
        this.calendarYear = calendarYear;
    }

    /**
     * @return the calendarQuarter
     */
    public int getCalendarQuarter() {
        return calendarQuarter;
    }

    /**
     * @param calendarQuarter the calendarQuarter to set
     */
    public void setCalendarQuarter(int calendarQuarter) {
        this.calendarQuarter = calendarQuarter;
    }

    /**
     * @return the financialYear
     */
    public int getFinancialYear() {
        return financialYear;
    }

    /**
     * @param financialYear the financialYear to set
     */
    public void setFinancialYear(int financialYear) {
        this.financialYear = financialYear;
    }

    /**
     * @return the financialQuarter
     */
    public int getFinancialQuarter() {
        return financialQuarter;
    }

    /**
     * @param financialQuarter the financialQuarter to set
     */
    public void setFinancialQuarter(int financialQuarter) {
        this.financialQuarter = financialQuarter;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    public String getDatimId() {
        return datimId;
    }

    public void setDatimId(String datimId) {
        this.datimId = datimId;
    }

    public String getCurrentARTStatus() {
        return currentARTStatus;
    }

    public void setCurrentARTStatus(String currentARTStatus) {
        this.currentARTStatus = currentARTStatus;
    }
    
    
    
    
    public static Patient newInstance(DemographicsType demo)
    {
        Patient p = new Patient();
        p.setAddressCountry(demo.getCountry());
        p.setAddressLGA(demo.getCityVillage());
        p.setAddressOther(demo.getAddress2());
        p.setAddressState(demo.getStateProvince());
        p.setAddressTown(demo.getAddress1());
        p.setAddressWard(demo.getAddress2());
        p.setContactPhoneNo(demo.getPhoneNumber());
        p.setCreatedBy(demo.getCreator()+"");
        p.setCurrAgeMonths(Misc.getAgeMonths(demo.getBirthdate().toGregorianCalendar()));
        p.setCurrAgeYears(Misc.getAge(demo.getBirthdate().toGregorianCalendar()));
        p.setDateCreated(new DateTime(demo.getDateCreated().toGregorianCalendar()));
        if(demo.getDeathDate() != null)
             p.setDateDeceased(new DateTime(demo.getDeathDate().toGregorianCalendar()));
        
        if(demo.getDateChanged() != null)
            p.setDateUpdated(new DateTime(demo.getDateChanged().toGregorianCalendar()));
        p.setDob(new DateTime(demo.getBirthdate().toGregorianCalendar()));
        p.setFinancialQuarter(Misc.getFinancialQuarter(Calendar.getInstance()));
        p.setFinancialYear(Misc.getFinancialYear(Calendar.getInstance()));
        p.setCalendarYear(Misc.getCalendarYear(Calendar.getInstance()));
        p.setCalendarQuarter(Misc.getCalendarQuarter(Calendar.getInstance()));
        p.setFirstName(demo.getFirstName());
        p.setLastName(demo.getLastName());
        p.setMarkedAsDeceased(demo.getDead());
        Calendar today = Calendar.getInstance();
        int month = today.get(Calendar.MONTH);
        p.setMonth(month);
        //p.setPatientOMRSID(demo.getPatientId());
        p.setPatientUUID(demo.getPatientUuid());
        p.setPersonId(demo.getPatientId());
        p.setRegistrationPhone(demo.getPhoneNumber());
        p.setSex(demo.getGender());
        p.setUpdatedBy(demo.getChangedBy()+"");
     
        p.setDatimId(demo.getDatimId());
        
        return p;
    }
    
    
    
    
}
