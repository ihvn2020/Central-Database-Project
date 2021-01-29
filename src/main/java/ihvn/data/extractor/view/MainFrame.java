/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.view;

import ihvn.data.extractor.controller.MainController;
import ihvn.data.extractor.model.dao.ZipUtil;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author rsuth
 */
public class MainFrame extends javax.swing.JFrame {

    MainController mainController;
    JFileChooser jfc ;
    static int totalPatient = 1;
    String outPutFolder = "";
    static boolean zipping = false;
    ProgessSetter progressSetter = new ProgessSetter();
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a directory to save your file: ");
	jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        myProgressBar.setStringPainted(true);
        myProgressBar.setValue(0);
        txtDbPassword.setVisible(false);
        //jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hvnlogo.png"))); // NOI18N
        //D:\'NMRS DEVELOPER\IHVNDataExtractor\src\main\resources\ihvnlogo.png'
        //jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("src/main/resources/images/ihvnlogo.png"))); 
        
    }
    
    private void startSettingProgress()
    {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(progressSetter, 100, 10);
    
        
    }
    
    private void zipXMlFolder()
    {
        System.out.println("started zipping");
       
        File f = new File(this.outPutFolder);
        String zipFileName = "IHVN_"+MainController.datimId+"_STATE DATABASE_"+new Date().getTime()+".zip";
        ZipUtil.zipFolder(f.getParent()+File.separator+zipFileName, this.outPutFolder);
        /*File toZIP = new File(this.outPutFolder);
        if (!toZIP.exists() || toZIP.listFiles() == null || Objects.requireNonNull(toZIP.listFiles()).length == 0) {
             JOptionPane.showMessageDialog(this, "An error occcured", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
             //Zip today's folder and name it with today's date
            //String zipFileName = new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".zip";
            ZipUtil appZip = new ZipUtil(this.outPutFolder);
            appZip.generateFileList(toZIP);
            // old implementation               appZip.zipIt(toZIP.getParent() + "/" + zipFileName);
            appZip.zipIt(Paths.get(toZIP.getParent(), zipFileName).toString());
            //zipping is complete. Open the folder
            
        }*/
        //this.openFolder(); this is throwing an error for now
        MainFrame.this.txtPatientCount.setText("Zipping complete. The zipped file is at "+f.getParent()+File.separator+zipFileName);
        
        
        
        
       
    }
    
    private void openFolder()
    {
        Desktop desktop = null;
    // on Windows, retrieve the path of the "Program Files" folder
        File file = new File(this.outPutFolder);

        try {
          if (Desktop.isDesktopSupported()) {
             desktop = Desktop.getDesktop();
             desktop.open(file.getParentFile());
          }
          else {
             System.out.println("desktop is not supported");
          }
        }
        catch (IOException e){  
            e.printStackTrace();
        }
    }
    
    public void setProgress(int patientCount)
    {
       
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                //MainFrame
               // MainFrame.this.totalPatient = patientCount;
                //MainFrame.this.txtPatientCount.setText("Processed Patient "+patientCount+" of "+MainFrame.this.totalPatient);
                
                System.out.println(MainController.atomicCounter.get());
                System.out.println("total count"+MainFrame.totalPatient);
                if(patientCount + 50 >= MainFrame.totalPatient && MainFrame.totalPatient != 1)//some times, the total processed files does not get to the total number of patients in the system due to errorneous data
                {
                    MainFrame.this.txtPatientCount.setText("Processed Patient "+MainFrame.this.totalPatient+" of "+MainFrame.totalPatient);
                
                    int progress = 100;
                    myProgressBar.setString( "Processed Patient "+MainFrame.this.totalPatient+" of "+MainFrame.this.totalPatient + ":  "+progress +" % complete");
                    myProgressBar.setValue(progress);
                   
                    //set a timer to zip the output folder
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                                   @Override
                                   public void run() {
                                     if(MainFrame.zipping == false)
                                     {
                                         MainFrame.this.txtPatientCount.setText("Extraction Complete. Currently zipping");
                                         MainFrame.this.zipXMlFolder();
                                         MainFrame.zipping = true; 
                                        
                                         
                                     }
                                    
                                   }
                                }, 3000);
                    System.out.println("zipping");
                     progressSetter.cancel();
                    
                }
                else{
                     MainFrame.this.txtPatientCount.setText("Processed Patient "+patientCount+" of "+MainFrame.totalPatient);
                
                    int progress = (patientCount *100) / MainFrame.totalPatient;
                    myProgressBar.setString( "Processed Patient "+patientCount+" of "+MainFrame.this.totalPatient + ":  "+progress +" % complete");
                    myProgressBar.setValue(progress);
                }
               
                //System.out.println("progress:"+progress);
                //System.out.println("Patinet count:"+patientCount);
                //System.out.println("total count:"+MainFrame.totalPatient);
                jpExtractionPanel.repaint();
               
            }
            
        
        });
        
        
       // revalidate();
       
       // jpExtractionPanel.repaint();
        
    }
    
    public void setTotalPatients(int totalPatient)
    {
        MainFrame.totalPatient = totalPatient;
        System.out.println(MainFrame.totalPatient);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDbHost = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDbPort = new javax.swing.JTextField();
        txtDbUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDbPassword = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtDbName = new javax.swing.JTextField();
        jpDbPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jpExtractionPanel = new javax.swing.JPanel();
        btnExtract = new javax.swing.JButton();
        txtOutputFolder = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        myProgressBar = new javax.swing.JProgressBar();
        btnShowFolderChooser = new javax.swing.JButton();
        txtPatientCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel1.setText("Database host");

        txtDbHost.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        txtDbHost.setText("localhost");
        txtDbHost.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDbHost.setMinimumSize(new java.awt.Dimension(100, 34));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel2.setText("Database port");

        txtDbPort.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        txtDbPort.setText("3316");
        txtDbPort.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDbPort.setMinimumSize(new java.awt.Dimension(100, 34));

        txtDbUsername.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        txtDbUsername.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDbUsername.setMinimumSize(new java.awt.Dimension(100, 34));
        txtDbUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDbUsernameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel3.setText("Database username");

        txtDbPassword.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        txtDbPassword.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDbPassword.setMinimumSize(new java.awt.Dimension(100, 34));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel4.setText("Database password");

        btnConnect.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel7.setText("Database name");

        txtDbName.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        txtDbName.setText("openmrs");
        txtDbName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtDbName.setMinimumSize(new java.awt.Dimension(100, 34));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDbHost, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addComponent(txtDbPort, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDbName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDbUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpDbPassword, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConnect)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDbHost, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDbPort, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDbName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDbUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jpDbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConnect)
                    .addComponent(txtDbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(57, 57, 139));
        jLabel5.setText("IHVN Data Extractor");

        try{
            jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ihvnlogo.png"))); // NOI18N
        }catch(Exception e){}

        jpExtractionPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnExtract.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        btnExtract.setText("Start Extraction");
        btnExtract.setEnabled(false);
        btnExtract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtractActionPerformed(evt);
            }
        });

        txtOutputFolder.setEditable(false);
        txtOutputFolder.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel8.setText("Output Location");

        btnShowFolderChooser.setText("...");
        btnShowFolderChooser.setEnabled(false);
        btnShowFolderChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowFolderChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpExtractionPanelLayout = new javax.swing.GroupLayout(jpExtractionPanel);
        jpExtractionPanel.setLayout(jpExtractionPanelLayout);
        jpExtractionPanelLayout.setHorizontalGroup(
            jpExtractionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpExtractionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpExtractionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpExtractionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtOutputFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnShowFolderChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpExtractionPanelLayout.createSequentialGroup()
                        .addGroup(jpExtractionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPatientCount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExtract)
                            .addComponent(myProgressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jpExtractionPanelLayout.setVerticalGroup(
            jpExtractionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpExtractionPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jpExtractionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpExtractionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtOutputFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addComponent(btnShowFolderChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(myProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPatientCount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExtract)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpExtractionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpExtractionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        // TODO add your handling code here:
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               MainFrame.this.txtPatientCount.setText("Processed Patient");
               String host = txtDbHost.getText().trim();
               String username = txtDbUsername.getText().trim();
               String password = new String(jpDbPassword.getPassword());//txtDbPassword.getText().trim();
               String port = txtDbPort.getText().trim();
               String dbName = txtDbName.getText().trim();
               mainController = new MainController(MainFrame.this, "com.mysql.jdbc.Driver", "jdbc:mysql://"+host+":"+port+"/"+dbName, username, password);
               if(mainController.testConnection())
               {
                   JOptionPane.showMessageDialog(MainFrame.this, "Connection Established", "Success", JOptionPane.INFORMATION_MESSAGE);
                   btnShowFolderChooser.setEnabled(true);
                   btnExtract.setEnabled(true);
               }
               else{
                    JOptionPane.showMessageDialog(MainFrame.this, "Connection Failed!",  "Error",  JOptionPane.ERROR_MESSAGE);
                    btnShowFolderChooser.setEnabled(false);
                   btnExtract.setEnabled(false);
               }
            }
        }); 
        
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnExtractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtractActionPerformed
        // TODO add your handling code here:
        btnExtract.setEnabled(false);
        Thread xmlThread = new Thread() {
                public void run() {
                   if(outPutFolder.equalsIgnoreCase(""))
                   {
                       JOptionPane.showMessageDialog(MainFrame.this, "You have not selected an output folder", "Output folder not set", JOptionPane.ERROR_MESSAGE);
                       btnExtract.setEnabled(true);
                   }
                   else{
                       MainFrame.this.startSettingProgress();
                       mainController.generateXMLs();
                   }
                   
                   //btnExtract.setEnabled(true);

                }  
            };

            xmlThread.start();
        
       
       System.out.println("startinged");
       
       
        
       
    }//GEN-LAST:event_btnExtractActionPerformed
    
    private void btnShowFolderChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowFolderChooserActionPerformed
        // TODO add your handling code here:
        jfc.setApproveButtonText("Select");
        int returnValue = jfc.showSaveDialog(null);
        try{
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (jfc.getSelectedFile().isDirectory()) {
                    Calendar now = Calendar.getInstance();
                    now.setTimeInMillis(new Date().getTime());
                    
                    outPutFolder= jfc.getSelectedFile().getAbsolutePath()+File.separator+""+now.get(Calendar.YEAR)+"_"+now.get(Calendar.MONTH)+"_"+now.get(Calendar.DATE)+"_"+now.get(Calendar.HOUR)+"_"+now.get(Calendar.MINUTE)+"_"+now.get(Calendar.SECOND);
                    File f = new File(outPutFolder);
                    f.mkdir();
                    System.out.println("You selected the directory: " + outPutFolder);
                    txtOutputFolder.setText( outPutFolder);
                    //System.out.println("You selected the directory: " + jfc.getSelectedFile().getCanonicalPath());
                    //System.out.println("You selected the directory: " + jfc.getSelectedFile().getPath());
                    //System.out.println("You selected the directory: " + jfc.getSelectedFile().getName());
                    mainController.setOutputLocation(outPutFolder);
                    
                    
                    
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnShowFolderChooserActionPerformed

    private void txtDbUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDbUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDbUsernameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    class ProgessSetter extends TimerTask
    {
        
        @Override
        public void run() {
          // Your database code here
          //MainFrame.this.setProgress(MainController.counter);
          MainFrame.this.setProgress(MainController.atomicCounter.get());

          if(MainController.atomicCounter.get()+10 > MainFrame.totalPatient)
          {
              System.out.println(MainController.counter);
              MainFrame.this.setProgress(MainFrame.totalPatient);
              btnExtract.setEnabled(true);
          }


        }
          
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnExtract;
    private javax.swing.JButton btnShowFolderChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jpDbPassword;
    private javax.swing.JPanel jpExtractionPanel;
    private javax.swing.JProgressBar myProgressBar;
    private javax.swing.JTextField txtDbHost;
    private javax.swing.JTextField txtDbName;
    private javax.swing.JTextField txtDbPassword;
    private javax.swing.JTextField txtDbPort;
    private javax.swing.JTextField txtDbUsername;
    private javax.swing.JTextField txtOutputFolder;
    private javax.swing.JLabel txtPatientCount;
    // End of variables declaration//GEN-END:variables
}
