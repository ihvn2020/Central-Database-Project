/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.PatientBiometricType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author rsuth
 */
public class PatientBiometricDAO {

    
   public static void deleteExisitingBiometrics(long patientId)
    {
        String query = "DELETE FROM patient_biometrics  WHERE patient_id= ?" ; 

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query);  
                stmt.setLong(1,patientId);//1 specifies the first parameter in the query  
               
                stmt.executeUpdate();
                int affectedRows = stmt.executeUpdate();
                

                //rs.close();
                //stmt.close();
               
        }
        catch (SQLException ex) {
                //screen.updateStatus(ex.getMessage());
                ex.printStackTrace();
                
        }
        finally {
                try {
                        if (rs != null && stmt != null) {
                                rs.close();
                                stmt.close();
                                 Database.connectionPool.free(con);
                              //con.close();
                        }

                }
                catch (SQLException ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    public static void  savePatientBiometrics(String patientUUID, String datimId, String messageUUID, List<PatientBiometricType> patientBiometrics) {
        
        //Connection connection;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {

            con = Database.connectionPool.getConnection();
            StringBuilder query = new StringBuilder("INSERT INTO  patient_biometric(biometric_info_id, datim_id, patient_id, template, image_height, image_width, image_dpi, ");
            query.append("image_quality, finger_position, serial_number, model, manufacturer, creator, date_created, patient_uuid, message_uuid)VALUES");
            
            
            for(int i=0; i<patientBiometrics.size(); i++)
            {
                query.append("(?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?),");
                
            }
            
            query.setLength(query.length() - 1);//remove the last comma
            query.append(" ON DUPLICATE KEY UPDATE template=VALUES(template), image_height=VALUES(image_height), image_width=VALUES(image_width), image_dpi=VALUES(image_dpi), ");
            query.append("image_quality=VALUES(image_quality), finger_position=VALUES(finger_position), serial_number=VALUES(serial_number), model=VALUES(model), manufacturer=VALUES(manufacturer)");
            
            if(patientBiometrics.size() > 0)//this is to ensure that there is actually biometrics before we try to prepare the statement
            {
                 stmt = con.prepareStatement(query.toString());
                 int index=1;
                 for(int i=0; i<patientBiometrics.size(); i++)
                 {
                    
                    stmt.setLong(index++, patientBiometrics.get(i).getBiometricInfoId());
                    stmt.setString(index++, datimId);
                    stmt.setInt(index++, patientBiometrics.get(i).getPatientId());
                    stmt.setString(index++, patientBiometrics.get(i).getTemplate());
                    stmt.setInt(index++, patientBiometrics.get(i).getImageHeight());
                    stmt.setInt(index++, patientBiometrics.get(i).getImageWidth());
                    stmt.setInt(index++, patientBiometrics.get(i).getImageDpi());
                    stmt.setInt(index++, patientBiometrics.get(i).getImageQuality());
                    stmt.setString(index++, patientBiometrics.get(i).getFingerPosition());
                    stmt.setString(index++, patientBiometrics.get(i).getSerialNumber());
                    stmt.setString(index++, patientBiometrics.get(i).getModel());
                    stmt.setString(index++, patientBiometrics.get(i).getManufacturer());
                    stmt.setLong(index++, patientBiometrics.get(i).getCreator());
                    stmt.setDate(index++, (patientBiometrics.get(i).getDateCreated() != null) ?  new java.sql.Date(patientBiometrics.get(i).getDateCreated().toGregorianCalendar().getTime().getTime()) : null);
                    stmt.setString(index++, patientBiometrics.get(i).getPatientUuid());
                    stmt.setString(index++, messageUUID);
                    
                 }
            }
           
            if(patientBiometrics.size() > 0)
                stmt.executeUpdate();//only execute if biometrics is present, otherwise this will through an error since the query will be incomplete
        }
        catch (SQLException e) {
                e.printStackTrace();
                //LoggerUtils.write(NDRMainDictionary.class.getName(), e.getMessage(), LogFormat.FATAL, LoggerUtils.LogLevel.live.live);
        }
        catch (Exception e) {
                e.printStackTrace();
        }
        finally {
                //cleanUp(resultSet, pStatement);

            try {
                    //result.close();
                    if(stmt != null)
                         stmt.close();
                    Database.connectionPool.free(con);
                    //con.close();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
                    //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
