/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.controller;

import static ihvn.data.consumer.controller.MainController.jaxbContext;
import ihvn.data.consumer.model.xml.Container;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author rsuth
 */
public class XmlController {
  
    private Container container;
    private File file;
    private ContainerController cntController = new ContainerController();
    
    
    public XmlController(File f)
    {
        this.file = f;
    }
    
    
    
    
    public void parseContainer()
    {
        
         try {
            
           
            Unmarshaller unmarshaller = MainController.jaxbContext.createUnmarshaller();
            Container container  = (Container) unmarshaller.unmarshal(XmlController.this.file);
            cntController.saveContainer(container);
            System.out.println(XmlController.this.file.delete());


        } catch (Exception ex) {
           ex.printStackTrace();
        }
       
       
        
    }
   
    
}
