/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.ObsType;
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
    
    public static int getAge(Calendar dob)
    {
        Calendar today = Calendar.getInstance();
 
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
    
   
}
