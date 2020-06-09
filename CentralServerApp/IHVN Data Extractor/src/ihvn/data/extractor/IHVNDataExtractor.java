/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor;

import ihvn.data.extractor.controller.MainController;
import ihvn.data.extractor.model.dao.PatientDAO;
import ihvn.data.extractor.model.dao.ZipUtil;
import javax.swing.JFrame;
import ihvn.data.extractor.view.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author rsuth
 */
public class IHVNDataExtractor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*MainController mainController = new MainController("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/openmrs","root", "inside12114");
        mainController.generateXMLs();*/
        
        
        System.out.println("App Ran fine");
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                   MainFrame frame = new MainFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);

                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true); 
                    
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                
            }
        });
       
    }
    
}
