/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer;

import ihvn.data.consumer.controller.MainController;
import ihvn.data.consumer.view.MainFrame;
import javax.swing.JFrame;
import javax.swing.UIManager;



/**
 *
 * @author rsuth
 */
public class IHVNDataConsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
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
       //check if there are files to read
       
           
       
        
    }
    
}
