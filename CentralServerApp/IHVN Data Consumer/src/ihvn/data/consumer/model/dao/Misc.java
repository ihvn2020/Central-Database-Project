/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.ObsType;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    
   
}
