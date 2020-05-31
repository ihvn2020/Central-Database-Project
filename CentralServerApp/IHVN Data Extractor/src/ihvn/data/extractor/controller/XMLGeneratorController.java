/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.controller;

import ihvn.data.extractor.model.dao.PatientDAO;
import ihvn.data.extractor.model.xml.Container;
import ihvn.data.extractor.view.MainFrame;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author rsuth
 */
public class XMLGeneratorController {
   
    private String outputLocation = "";
    public MainFrame mainFrame;
    ContainerController containerController;
    public XMLGeneratorController(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
    }
    
    public void startGenerating(String outputLocation, int offset, int limit)
    {
        SwingWorker sw1 = new SwingWorker()  
        { 
  
            @Override
            protected String doInBackground() throws Exception  
            { 
                // define what thread will do here 
                XMLGeneratorController.this.outputLocation = outputLocation;
                PatientDAO patientObj = new PatientDAO();
                

                List<Map<String, String>> allPatients = patientObj.getAllPatients(offset, limit);

                //set the total patients to the main frame

                // ExecutorService executor = Executors.newFixedThreadPool(5);//creating a pool of 5 threads  
                //MainControllercounter = offset;
               /* for(Map<String,String> patient : allPatients)
                {
                    //System.out.println("processing patient "+(MainController.counter++));
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                           MainController.counter++;
                           containerController = new ContainerController(patient);
                           Container container = containerController.buildContainer();

                           XMLGeneratorController.this.saveContainerToXMLFile(container);
                           publish(MainController.counter); 

                        }
                    });
                    
                   
                 

                }*/
                // executor.shutdown();  

                
                for(Map<String,String> patient : allPatients)
                {
                    //System.out.println("processing patient "+(MainController.counter++));
                    
                    MainController.counter++;
                    MainController.atomicCounter.incrementAndGet();
                    containerController = new ContainerController(patient);
                   
                    Container container = containerController.buildContainer();
                    patient.clear();
                    XMLGeneratorController.this.saveContainerToXMLFile(container);
                    
                    
                    //publish(MainController.counter);
                 

                }
                Thread.sleep(1000);
                System.gc();
                
                allPatients.clear();
                allPatients = null;

                
                String res = "Finished Execution"; 
                return res; 
            } 
  
            @Override
            protected void process(List chunks) 
            { 
                // define what the event dispatch thread  
                // will do with the intermediate results received 
                // while the thread is executing 
               int val = (int)chunks.get(chunks.size()-1); 
               
               mainFrame.setProgress(MainController.counter);
               
            } 
  
            @Override
            protected void done()  
            { 
                // this method is called when the background
                // thread finishes execution
                System.out.println("Inside done function");
                //mainFrame.setProgress(100); 
            } 
        }; 
          
        // executes the swingworker on worker thread 
        sw1.execute();
       
        /*this.outputLocation = outputLocation;
        PatientDAO patientObj = new PatientDAO();
        ContainerController containerController;
       
        List<Map<String, String>> allPatients = patientObj.getAllPatients(offset, limit);
        
        //set the total patients to the main frame
        
        
        counter = offset;
        for(Map<String,String> patient : allPatients)
        {
            System.out.println("processing patient "+(counter++));
            containerController = new ContainerController(patient);
            Container container = containerController.buildContainer();
            this.saveContainerToXMLFile(container);
            new Runnable(){
                @Override
                public void run() {
                    XMLGeneratorController.this.mainFrame.setProgress(counter);
                
                }
                
            };
            
            
        }*/
        
    }
    
    private void saveContainerToXMLFile(Container container)
    {
        
       
        Marshaller marshaller = null;
        try {
            marshaller = MainController.jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.marshal(container, new File("/home/rsuth/Documents/xmls/"+container.getMessageData().getDemographics().getPatientUUID()+"_patient.xml"));
            marshaller.marshal(container, new File(this.outputLocation+"/"+container.getMessageData().getDemographics().getPatientUuid()+"_patient.xml"));
            marshaller = null;
            container = null;
            
            //marshaller.marshal(container, System.out);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
           
        }
    
    }
}
