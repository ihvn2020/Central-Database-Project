/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.models;

import ihvn.data.consumer.model.dao.Misc;
import ihvn.data.consumer.model.xml.DemographicsType;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author lordmaul
 */
public class Radet {
   private int radetId;
   private int finYear;
   private int finQuarter;
   private int calendarYear;
   private int calendarQuarter;
   private int year;
   private int quarter;
   private int month;
   private String datimId;
   private String patientUUID;
   private String transferInStatus;
   private DateTime transferInDate;
   private String careEntryPoint;
   private DateTime lastPickupDate;
   private int daysOfARVRefill;
   private String initialFirstLineRegimen;
   private DateTime initialFirstLineRegimenDate;
   private String initialSecondLineRegimen;
   private DateTime initialSecondLineRegimenDate;
   private String initialRegimenLine;
   private String currentRegimenLine;
   private String currentFirstLineRegimen;
   private DateTime currentFirstLineRegimenDate;
   private String currentSecondLineRegimen;
   private DateTime currentSecondLineRegimenDate;
   private String pregnancyStatus;
   private double currentViralLoad;
   private DateTime viralLoadSampleCollectionDate;
   private DateTime viralLoadReportedDate;
   private String viralLoadIndication;
   private String currentArtStatus;
   private DateTime transferOutDate;
   private DateTime deathDate;
   private double currentWeight;
   private DateTime currentWeightDate;
   private String tbStatus;
   private DateTime tbStatusDate;
   private DateTime inhStartDate;
   private DateTime inhStopDate;
   private DateTime lastInhDispensedDate;
   private DateTime tbTreatmentStartDate;
   private DateTime tbTreatmentStopDate;
   private DateTime lastvLSampleCollectionFormDate;
   private DateTime otzStartDate;
   private DateTime otzStopDate;

    /**
     * @return the radetId
     */
    public int getRadetId() {
        return radetId;
    }

    /**
     * @param radetId the radetId to set
     */
    public void setRadetId(int radetId) {
        this.radetId = radetId;
    }

    /**
     * @return the finYear
     */
    public int getFinYear() {
        return finYear;
    }

    /**
     * @param finYear the finYear to set
     */
    public void setFinYear(int finYear) {
        this.finYear = finYear;
    }

    /**
     * @return the finQuarter
     */
    public int getFinQuarter() {
        return finQuarter;
    }

    /**
     * @param finQuarter the finQuarter to set
     */
    public void setFinQuarter(int finQuarter) {
        this.finQuarter = finQuarter;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the quarter
     */
    public int getQuarter() {
        return quarter;
    }

    /**
     * @param quarter the quarter to set
     */
    public void setQuarter(int quarter) {
        this.quarter = quarter;
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
     * @return the transferInStatus
     */
    public String getTransferInStatus() {
        return transferInStatus;
    }

    /**
     * @param transferInStatus the transferInStatus to set
     */
    public void setTransferInStatus(String transferInStatus) {
        this.transferInStatus = transferInStatus;
    }

    /**
     * @return the transferInDate
     */
    public DateTime getTransferInDate() {
        return transferInDate;
    }

    /**
     * @param transferInDate the transferInDate to set
     */
    public void setTransferInDate(DateTime transferInDate) {
        this.transferInDate = transferInDate;
    }

    /**
     * @return the careEntryPoint
     */
    public String getCareEntryPoint() {
        return careEntryPoint;
    }

    /**
     * @param careEntryPoint the careEntryPoint to set
     */
    public void setCareEntryPoint(String careEntryPoint) {
        this.careEntryPoint = careEntryPoint;
    }

    /**
     * @return the lastPickupDate
     */
    public DateTime getLastPickupDate() {
        return lastPickupDate;
    }

    /**
     * @param lastPickupDate the lastPickupDate to set
     */
    public void setLastPickupDate(DateTime lastPickupDate) {
        this.lastPickupDate = lastPickupDate;
    }

    /**
     * @return the daysOfARVRefill
     */
    public int getDaysOfARVRefill() {
        return daysOfARVRefill;
    }

    /**
     * @param daysOfARVRefill the daysOfARVRefill to set
     */
    public void setDaysOfARVRefill(int daysOfARVRefill) {
        this.daysOfARVRefill = daysOfARVRefill;
    }

    /**
     * @return the initialFirstLineRegimen
     */
    public String getInitialFirstLineRegimen() {
        return initialFirstLineRegimen;
    }

    /**
     * @param initialFirstLineRegimen the initialFirstLineRegimen to set
     */
    public void setInitialFirstLineRegimen(String initialFirstLineRegimen) {
        this.initialFirstLineRegimen = initialFirstLineRegimen;
    }

    /**
     * @return the initialFirstLineRegimenDate
     */
    public DateTime getInitialFirstLineRegimenDate() {
        return initialFirstLineRegimenDate;
    }

    /**
     * @param initialFirstLineRegimenDate the initialFirstLineRegimenDate to set
     */
    public void setInitialFirstLineRegimenDate(DateTime initialFirstLineRegimenDate) {
        this.initialFirstLineRegimenDate = initialFirstLineRegimenDate;
    }

    /**
     * @return the initialSecondLineRegimen
     */
    public String getInitialSecondLineRegimen() {
        return initialSecondLineRegimen;
    }

    /**
     * @param initialSecondLineRegimen the initialSecondLineRegimen to set
     */
    public void setInitialSecondLineRegimen(String initialSecondLineRegimen) {
        this.initialSecondLineRegimen = initialSecondLineRegimen;
    }

    /**
     * @return the initialSecondLineRegimenDate
     */
    public DateTime getInitialSecondLineRegimenDate() {
        return initialSecondLineRegimenDate;
    }

    /**
     * @param initialSecondLineRegimenDate the initialSecondLineRegimenDate to set
     */
    public void setInitialSecondLineRegimenDate(DateTime initialSecondLineRegimenDate) {
        this.initialSecondLineRegimenDate = initialSecondLineRegimenDate;
    }

    /**
     * @return the currentRegimenLine
     */
    public String getCurrentRegimenLine() {
        return currentRegimenLine;
    }

    /**
     * @param currentRegimenLine the currentRegimenLine to set
     */
    public void setCurrentRegimenLine(String currentRegimenLine) {
        this.currentRegimenLine = currentRegimenLine;
    }

    /**
     * @return the currentFirstLineRegimen
     */
    public String getCurrentFirstLineRegimen() {
        return currentFirstLineRegimen;
    }

    /**
     * @param currentFirstLineRegimen the currentFirstLineRegimen to set
     */
    public void setCurrentFirstLineRegimen(String currentFirstLineRegimen) {
        this.currentFirstLineRegimen = currentFirstLineRegimen;
    }

    /**
     * @return the currentFirstLineRegimenDate
     */
    public DateTime getCurrentFirstLineRegimenDate() {
        return currentFirstLineRegimenDate;
    }

    /**
     * @param currentFirstLineRegimenDate the currentFirstLineRegimenDate to set
     */
    public void setCurrentFirstLineRegimenDate(DateTime currentFirstLineRegimenDate) {
        this.currentFirstLineRegimenDate = currentFirstLineRegimenDate;
    }

    /**
     * @return the currentSecondLineRegimen
     */
    public String getCurrentSecondLineRegimen() {
        return currentSecondLineRegimen;
    }

    /**
     * @param currentSecondLineRegimen the currentSecondLineRegimen to set
     */
    public void setCurrentSecondLineRegimen(String currentSecondLineRegimen) {
        this.currentSecondLineRegimen = currentSecondLineRegimen;
    }

    /**
     * @return the currentSecondLineRegimenDate
     */
    public DateTime getCurrentSecondLineRegimenDate() {
        return currentSecondLineRegimenDate;
    }

    /**
     * @param currentSecondLineRegimenDate the currentSecondLineRegimenDate to set
     */
    public void setCurrentSecondLineRegimenDate(DateTime currentSecondLineRegimenDate) {
        this.currentSecondLineRegimenDate = currentSecondLineRegimenDate;
    }

    /**
     * @return the pregnancyStatus
     */
    public String getPregnancyStatus() {
        return pregnancyStatus;
    }

    /**
     * @param pregnancyStatus the pregnancyStatus to set
     */
    public void setPregnancyStatus(String pregnancyStatus) {
        this.pregnancyStatus = pregnancyStatus;
    }

    /**
     * @return the currentViralLoad
     */
    public double getCurrentViralLoad() {
        return currentViralLoad;
    }

    /**
     * @param currentViralLoad the currentViralLoad to set
     */
    public void setCurrentViralLoad(double currentViralLoad) {
        this.currentViralLoad = currentViralLoad;
    }

    /**
     * @return the viralLoadSampleCollectionDate
     */
    public DateTime getViralLoadSampleCollectionDate() {
        return viralLoadSampleCollectionDate;
    }

    /**
     * @param viralLoadSampleCollectionDate the viralLoadSampleCollectionDate to set
     */
    public void setViralLoadSampleCollectionDate(DateTime viralLoadSampleCollectionDate) {
        this.viralLoadSampleCollectionDate = viralLoadSampleCollectionDate;
    }

    /**
     * @return the viralLoadReportedDate
     */
    public DateTime getViralLoadReportedDate() {
        return viralLoadReportedDate;
    }

    /**
     * @param viralLoadReportedDate the viralLoadReportedDate to set
     */
    public void setViralLoadReportedDate(DateTime viralLoadReportedDate) {
        this.viralLoadReportedDate = viralLoadReportedDate;
    }

    /**
     * @return the viralLoadIndication
     */
    public String getViralLoadIndication() {
        return viralLoadIndication;
    }

    /**
     * @param viralLoadIndication the viralLoadIndication to set
     */
    public void setViralLoadIndication(String viralLoadIndication) {
        this.viralLoadIndication = viralLoadIndication;
    }

    /**
     * @return the currentArtStatus
     */
    public String getCurrentArtStatus() {
        return currentArtStatus;
    }

    /**
     * @param currentArtStatus the currentArtStatus to set
     */
    public void setCurrentArtStatus(String currentArtStatus) {
        this.currentArtStatus = currentArtStatus;
    }

    /**
     * @return the transferOutDate
     */
    public DateTime getTransferOutDate() {
        return transferOutDate;
    }

    /**
     * @param transferOutDate the transferOutDate to set
     */
    public void setTransferOutDate(DateTime transferOutDate) {
        this.transferOutDate = transferOutDate;
    }

    /**
     * @return the deathDate
     */
    public DateTime getDeathDate() {
        return deathDate;
    }

    /**
     * @param deathDate the deathDate to set
     */
    public void setDeathDate(DateTime deathDate) {
        this.deathDate = deathDate;
    }

    /**
     * @return the currentWeight
     */
    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * @param currentWeight the currentWeight to set
     */
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * @return the currentWeightDate
     */
    public DateTime getCurrentWeightDate() {
        return currentWeightDate;
    }

    /**
     * @param currentWeightDate the currentWeightDate to set
     */
    public void setCurrentWeightDate(DateTime currentWeightDate) {
        this.currentWeightDate = currentWeightDate;
    }

    /**
     * @return the tbStatus
     */
    public String getTbStatus() {
        return tbStatus;
    }

    /**
     * @param tbStatus the tbStatus to set
     */
    public void setTbStatus(String tbStatus) {
        this.tbStatus = tbStatus;
    }

    /**
     * @return the tbStatusDate
     */
    public DateTime getTbStatusDate() {
        return tbStatusDate;
    }

    /**
     * @param tbStatusDate the tbStatusDate to set
     */
    public void setTbStatusDate(DateTime tbStatusDate) {
        this.tbStatusDate = tbStatusDate;
    }

    /**
     * @return the inhStartDate
     */
    public DateTime getInhStartDate() {
        return inhStartDate;
    }

    /**
     * @param inhStartDate the inhStartDate to set
     */
    public void setInhStartDate(DateTime inhStartDate) {
        this.inhStartDate = inhStartDate;
    }

    /**
     * @return the inhStopDate
     */
    public DateTime getInhStopDate() {
        return inhStopDate;
    }

    /**
     * @param inhStopDate the inhStopDate to set
     */
    public void setInhStopDate(DateTime inhStopDate) {
        this.inhStopDate = inhStopDate;
    }

    /**
     * @return the lastInhDispensedDate
     */
    public DateTime getLastInhDispensedDate() {
        return lastInhDispensedDate;
    }

    /**
     * @param lastInhDispensedDate the lastInhDispensedDate to set
     */
    public void setLastInhDispensedDate(DateTime lastInhDispensedDate) {
        this.lastInhDispensedDate = lastInhDispensedDate;
    }

    /**
     * @return the tbTreatmentStartDate
     */
    public DateTime getTbTreatmentStartDate() {
        return tbTreatmentStartDate;
    }

    /**
     * @param tbTreatmentStartDate the tbTreatmentStartDate to set
     */
    public void setTbTreatmentStartDate(DateTime tbTreatmentStartDate) {
        this.tbTreatmentStartDate = tbTreatmentStartDate;
    }

    /**
     * @return the tbTreatmentStopDate
     */
    public DateTime getTbTreatmentStopDate() {
        return tbTreatmentStopDate;
    }

    /**
     * @param tbTreatmentStopDate the tbTreatmentStopDate to set
     */
    public void setTbTreatmentStopDate(DateTime tbTreatmentStopDate) {
        this.tbTreatmentStopDate = tbTreatmentStopDate;
    }

    /**
     * @return the lastvLSampleCollectionFormDate
     */
    public DateTime getLastvLSampleCollectionFormDate() {
        return lastvLSampleCollectionFormDate;
    }

    /**
     * @param lastvLSampleCollectionFormDate the lastvLSampleCollectionFormDate to set
     */
    public void setLastvLSampleCollectionFormDate(DateTime lastvLSampleCollectionFormDate) {
        this.lastvLSampleCollectionFormDate = lastvLSampleCollectionFormDate;
    }

    /**
     * @return the otzStartDate
     */
    public DateTime getOtzStartDate() {
        return otzStartDate;
    }

    /**
     * @param otzStartDate the otzStartDate to set
     */
    public void setOtzStartDate(DateTime otzStartDate) {
        this.otzStartDate = otzStartDate;
    }

    /**
     * @return the otzStopDate
     */
    public DateTime getOtzStopDate() {
        return otzStopDate;
    }

    /**
     * @param otzStopDate the otzStopDate to set
     */
    public void setOtzStopDate(DateTime otzStopDate) {
        this.otzStopDate = otzStopDate;
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
    
    
    public static Radet newInstance(DemographicsType demo)
    {
        Radet r = new Radet();
        r.setDatimId(demo.getDatimId());
        r.setPatientUUID(demo.getPatientUuid());
        r.setFinQuarter(Misc.getFinancialQuarter(Calendar.getInstance()));
        r.setFinYear(Misc.getFinancialYear(Calendar.getInstance()));
        r.setCalendarYear(Misc.getCalendarYear(Calendar.getInstance()));
        r.setCalendarQuarter(Misc.getCalendarQuarter(Calendar.getInstance()));
        Calendar today = Calendar.getInstance();
        int month = today.get(Calendar.MONTH);
        r.setMonth(month);
        //r.se
        return r;
    }

    /**
     * @return the initialRegimenLine
     */
    public String getInitialRegimenLine() {
        return initialRegimenLine;
    }

    /**
     * @param initialRegimenLine the initialRegimenLine to set
     */
    public void setInitialRegimenLine(String initialRegimenLine) {
        this.initialRegimenLine = initialRegimenLine;
    }
    
   
}
