/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.controller;

import ihvn.data.consumer.model.dao.Database;
import ihvn.data.consumer.model.dao.Misc;
import ihvn.data.consumer.model.xml.Container;
import ihvn.data.consumer.view.MainFrame;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBContext;

/**
 *
 * @author rsuth
 */
public class MainController {
    
    
    private String folderPath = "";
    private String currFolder = "";//this is the extracted folder that is being processed
    public static JAXBContext jaxbContext ;
    public MainFrame main;
    
    
    
    static{
        
        try {
            jaxbContext = JAXBContext.newInstance(Container.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public MainController(MainFrame main)
    {
        this.main = main;
    }
    public boolean initDb()
    {
        //initialize database connection
        FileReader reader=null;
        try {
            // TODO code application logic here
            reader = new FileReader("connection.properties");
            Properties p=new Properties();
            p.load(reader);
            String connString = p.getProperty("connection.url");
            String className = p.getProperty("connection.driver_class");
            String username = p.getProperty("connection.username");
            String password = p.getProperty("connection.password");
            folderPath = p.getProperty("xml_folder_path");
          
            
            Database.initConnection(className, connString, username, password);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
       
       
    }
    
    public boolean newFilesExists()
    {
        final File folder = new File(this.folderPath);
        
        //get only zipped files in that folder
        File[] zippedFileList = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".zip");
            }
        });
        
        if(zippedFileList.length > 0)
        {

            //if there are more than one file, sort the zipped files to get the oldest ones first
            if(zippedFileList.length > 1){
                Arrays.sort(zippedFileList, new Comparator<File>(){
                public int compare(File f1, File f2)
                {
                    return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
                } });
            }
            
           //unzip the very first file
           
           this.currFolder = Misc.unzipp(zippedFileList[0].getAbsolutePath());
           
           //next is to delete the zipped file
           zippedFileList[0].delete();
           
           
           final File currFolder = new File(this.currFolder);
           MainFrame.fileCount = currFolder.listFiles().length;
            return true;
        }
        else{
            MainFrame.fileCount = 0;
            return false;
        }
          
    }
    
    
    
    public int readXMLs()
    {
        
        
        SwingWorker sw1 = new SwingWorker()  
        { 
  
            @Override
            protected String doInBackground() throws Exception  
            { 
               
              try {
                final File folder = new File(MainController.this.currFolder);
                int patientCounter = 0;
                File [] fileList = folder.listFiles();
                for (final File fileEntry : fileList) {
                    if(fileEntry.isFile()){ 
                        String fileExtension = fileEntry.getAbsolutePath().substring(fileEntry.getAbsolutePath().lastIndexOf(".") + 1);
                        if(fileExtension.equalsIgnoreCase("xml"))
                        {
                            System.out.println("processing patient "+patientCounter++);
                            XmlController xmlController = new XmlController(fileEntry);
                            xmlController.parseContainer();
                            MainFrame.currCount.getAndIncrement();
                            //this.marshallXML(fileEntry);

                        }
                    }


                 }

                try {
                    //once we are done with the loop,  waiting for about 10 seconds and start checking again
                    Thread.sleep(10000);
                    main.startCheckingForFiles();//start checking again
                    // break;
                    //System.out.println(fileEntry.getAbsolutePath());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }


             } catch (Exception ex) {
                 ex.printStackTrace();
            }  
                String res = "Finished Execution"; 
                return res; 
            } 
  
            @Override
            protected void process(List chunks) 
            { 
               
               
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
        
        
        return 1;
    }
   
}
