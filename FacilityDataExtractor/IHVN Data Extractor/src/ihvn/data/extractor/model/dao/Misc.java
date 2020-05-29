/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

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
}
