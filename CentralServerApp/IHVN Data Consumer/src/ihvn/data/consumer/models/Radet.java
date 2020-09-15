/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.models;

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
   
   
}
