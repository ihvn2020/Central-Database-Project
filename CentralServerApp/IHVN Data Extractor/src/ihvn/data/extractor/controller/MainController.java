/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.controller;

import ihvn.data.extractor.model.dao.Database;
import ihvn.data.extractor.model.dao.FacilityDAO;
import ihvn.data.extractor.model.dao.PatientDAO;
import ihvn.data.extractor.model.xml.Container;
import ihvn.data.extractor.view.MainFrame;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 *
 * @author rsuth
 */
public class MainController {
    
    private String className = "com.mysql.jdbc.Driver";
    private String connString;
    private String username;
    private String password;
    private String outputLocation;
    public static int counter = 0;
    public static AtomicInteger atomicCounter = new AtomicInteger(0);
    public static String patientFacilityName = "";
    public static String datimId = "";
    
    public static JAXBContext jaxbContext ;
    MainFrame mainFrame;
    
    static{
        
        try {
            jaxbContext = JAXBContext.newInstance(Container.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    
    PatientDAO patientObj = new PatientDAO();
    FacilityDAO facilityObj = new FacilityDAO();
    public MainController(MainFrame mainF, String className, String connString, String username, String password)
    {
        this.mainFrame = mainF;
        this.className = className;
        this.connString = connString;
        this.username = username;
        this.password = password;
    }
    
    public void setOutputLocation(String outputLocation)
    {
        this.outputLocation = outputLocation;
    }
    
    public boolean testConnection()
    {
        try{
            if(Database.testConnection(className, connString, username, password))
            {
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    public int generateXMLs()
    {
        //initialize database connectino
        Database.initConnection(className, connString, username, password);
        
        XMLGeneratorController xmlGeneratorController = new XMLGeneratorController(this.mainFrame);
        int totalPatients = patientObj.getTotalPatients();
        int limit = 200;
        int totalPages = (int)Math.ceil(totalPatients/limit);
        //some global stuff. Lets get them only once
        patientFacilityName = facilityObj.getGlobalProperty("Facility_Name");
	datimId = facilityObj.getGlobalProperty("facility_datim_code");
        mainFrame.setTotalPatients(totalPatients);
        for(int i=0; i <= totalPages ; i++)
        //for(int i=0; i< 2; i++)
        {
            //System.out.println("page count: "+i);
            xmlGeneratorController.startGenerating( this.outputLocation, i * limit, limit);
            try {
                //if(i%)
                //Thread.sleep(8000);
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }
       
      
        
       /*SwingUtilities.invokeLater(new Runnable(){
       
           @Override
            public void run(){
                //for(int i=0; i< totalPages; i++)
                 for(int i=0; i< 2; i++)
                 {
                     xmlGeneratorController.startGenerating( MainController.this.outputLocation, i * limit, limit);
                 }

            }
       });
        
        System.out.println("hi and hello");
        */
        return 1;
    }
    
}
