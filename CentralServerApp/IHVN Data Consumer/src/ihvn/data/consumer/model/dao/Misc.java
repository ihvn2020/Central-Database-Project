/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.controller.ContainerController;
import ihvn.data.consumer.model.xml.ObsType;
import ihvn.data.consumer.models.Patient;
import ihvn.data.consumer.models.Radet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.Years;

/**
 *
 * @author rsuth
 */
public class Misc {
    
    
    public static XMLGregorianCalendar getXMLdate(Date date)
    {
        XMLGregorianCalendar cal = null;
        try{
            if(date != null)
            {
                cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd").format(date));
            }
        }
        catch(Exception e)
        {
            
        }
        return cal;
    }
    
    public static XMLGregorianCalendar getXMLdateTime(Date date)
    {
        XMLGregorianCalendar cal = null;
        try{
            if(date != null)
            {
                cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd'T'mm:ss").format(date));
            }
        }
        catch(Exception e)
        {
            
        }
        return cal;
    }
    
    public static int getStateId(String stateName)
    {
        return 0;
    }

    public static int getLGAId(String lgaName)
    {
        return 0;
    }
    
    public static String[] getObsValue(ObsType obs)
    {
        String obsValue[] = new String[2];
        /*if(obs.getValueDateTime()!= null)
        {
            obsValue[0] = obs.getValueDateTime().toString();
            obsValue[1] = "datetime";
        }
        else if(obs.getValueCoded() != 0)
        {
            obsValue[0] = obs.getValueCoded()+"";
            obsValue[1] = "valuecoded";
        }
        else if(obs.getValueNumeric() != 0 )
        {
            obsValue[0] = obs.getValueNumeric()+"";
            obsValue[1] = "numeric";
        }
        else if(obs.getValueText() != null)
        {
            obsValue[0] = obs.getValueText();
            obsValue[1] = "text";
        }*/
            
        return obsValue;
    }
    
    //unzip file
    public static String unzipp(String zippedFilePath)
    {
        ZipEntry zipEntry;
        ZipInputStream zis = null;
        String unzippedFilePath = "";
        try{
            unzippedFilePath = zippedFilePath.replace(".zip", "");
            File destDir = new File(unzippedFilePath);
            destDir.mkdir();
            byte[] buffer = new byte[1024];
            zis = new ZipInputStream(new FileInputStream(zippedFilePath));
            zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newFile(destDir, zipEntry);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zis.getNextEntry();
            }
            
            
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
                zis.closeEntry();
                zis.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return unzippedFilePath ;
        
    }
    
    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
         
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
         
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
         
        return destFile;
    }
    
    public static int getAge(Calendar dob, Calendar now)
    {
        Calendar today = now;
 
        int curYear = today.get(Calendar.YEAR);
        int dobYear = dob.get(Calendar.YEAR);
 
        int age = curYear - dobYear;
 
        // if dob is month or day is behind today's month or day
        // reduce age by 1
        int curMonth = today.get(Calendar.MONTH);
        int dobMonth = dob.get(Calendar.MONTH);
        if (dobMonth > curMonth) { // this year can't be counted!
            age--;
        } else if (dobMonth == curMonth) { // same month? check for day
            int curDay = today.get(Calendar.DAY_OF_MONTH);
            int dobDay = dob.get(Calendar.DAY_OF_MONTH);
            if (dobDay > curDay) { // this year can't be counted!
                age--;
            }
        }
 
        return age;
    }
    
    public static int getAge(Calendar dob)
    {
        Calendar today = Calendar.getInstance();
        return getAge(dob, today);
    }
    
    public static int getAgeMonths(Calendar dob, Calendar now)
    {
        Calendar today = now;
 
        int curYear = today.get(Calendar.YEAR);
        int dobYear = dob.get(Calendar.YEAR);
 
        int age = curYear - dobYear;
        
        int noMonths = 0;
        // if dob is month or day is behind today's month or day
        // reduce age by 1
        int curMonth = today.get(Calendar.MONTH);
        int dobMonth = dob.get(Calendar.MONTH);
        if (dobMonth > curMonth) { // this year can't be counted!
            age--;
            noMonths = dobMonth;
        } else if (dobMonth == curMonth) { // same month? check for day
            int curDay = today.get(Calendar.DAY_OF_MONTH);
            int dobDay = dob.get(Calendar.DAY_OF_MONTH);
            if (dobDay > curDay) { // this year can't be counted!
                age--;
            }
        }
        //at this point we have the years 
        //lets get the number of months assuming the current month is less than
        return (age * 12) + noMonths;
    }
    
    public static int getAgeMonths(Calendar dob)
    {
        Calendar today = Calendar.getInstance();
        return getAgeMonths(dob, today);
    }
    
    public static boolean isInFuture(Calendar cal)
    {
        DateTime date = new DateTime(cal);
        if(date.isAfterNow())
        {
            return true;
        }
        return false;
    }
    
    public static boolean isInFuture(DateTime date)
    {
        if(date.isAfterNow())
        {
            return true;
        }
        return false;
    }
    
    public static boolean isBefore1990(DateTime date)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(1990,0,1,1, 1,1);
        DateTime before1990 = new DateTime(cal);
        
        if(date.isBefore(before1990))
        {
            return true;
        }
        return false;
    }
    public static boolean isBefore1990(Calendar calDate)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(1990,0,1,1, 1,1);
        DateTime before1990 = new DateTime(cal);
        
        DateTime date = new DateTime(calDate);
        
        if(date.isBefore(before1990))
        {
            return true;
        }
        return false;
    }
    
    public static boolean isBeforeDate(DateTime date1, DateTime date2)
    {
        if(date1.isBefore(date2))
        {
            return true;
        }
        return false;
    }
    
    public static boolean isBeforeDate(Calendar cal1, Calendar cal2)
    {
        DateTime date1 = new DateTime(cal1);
        DateTime date2 = new DateTime(cal2);
        if(date1.isBefore(date2))
        {
            return true;
        }
        return false;
    }
    
    public static boolean isAfterDate(DateTime date1, DateTime date2)
    {
        if(date1.isAfter(date2))
        {
            return true;
        }
        return false;
    }
    
    public static boolean isAfterDate(Calendar cal1, Calendar cal2)
    {
        DateTime date1 = new DateTime(cal1);
        DateTime date2 = new DateTime(cal2);
        
        if(date1.isAfter(date2))
        {
            return true;
        }
        return false;
    } 
    
    public static boolean isGreaterThan200(Calendar cal)
    {
        DateTime date = new DateTime(cal);
        DateTime now = new DateTime();
        Years age = Years.yearsBetween(date, now);
        if(age.getYears() > 200)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static int getCalendarYear(Calendar date)
    {
       return date.get(Calendar.YEAR);
    }
    public static int getCalendarQuarter(Calendar date)
    {
        int month = date.get(Calendar.MONTH);

        if(month >= Calendar.JANUARY && month <= Calendar.MARCH)
        {
            return 1;
        }
        else if(month >= Calendar.APRIL && month <= Calendar.JUNE){
            return 2;
        }
        else if( month >= Calendar.JULY && month <= Calendar.SEPTEMBER){
            return 3;
        }
        else{
            return 4;
        }
    }
    
    public static int getFinancialYear(Calendar date)
    {
        int month = date.get(Calendar.MONTH);

        if(month >= Calendar.JANUARY && month <= Calendar.SEPTEMBER)
        {
            return date.get(Calendar.YEAR);
        }
        else{
            return date.get(Calendar.YEAR) + 1;
        }
        
    }
    public static int getFinancialQuarter(Calendar date)
    {
        int month = date.get(Calendar.MONTH);

        if(month >= Calendar.JANUARY && month <= Calendar.MARCH)
        {
            return 2;
        }
        else if(month >= Calendar.APRIL && month <= Calendar.JUNE){
            return 3;
        }
        else if( month >= Calendar.JULY && month <= Calendar.SEPTEMBER){
            return 4;
        }
        else{
            return 1;
        }
    }
    
    public static String getARTStatus(Patient p, Radet r)
    {
        
        if(p.getDateDeceased() != null)
        {
            return "Deceased";
        }
        else if(r.getTransferOutDate() != null)
        {
            return "Transferred out";
        }
        else {
            DateTime lastPickupDate = r.getLastPickupDate();
            if(lastPickupDate == null)
            {
                if(ContainerController.allRadet.get(p.getPatientUUID()).getCurrentArtStatus() != null)
                {
                    return ContainerController.allRadet.get(p.getPatientUUID()).getCurrentArtStatus();//the patient is most likely not on art
                }
                return "No pickup date";//the patient is probably on art but does not have any drug pickup
            }
            int drugDuration = r.getDaysOfARVRefill();
            
            DateTime maxAppointmentDate = lastPickupDate.plusDays(drugDuration+28);
            if(Misc.isBeforeDate(maxAppointmentDate, new DateTime(Calendar.getInstance())))
            {
                return "LTFU";
            }
            else{
                return "Active";
            }
            
        }
    }
}
