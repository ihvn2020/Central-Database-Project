/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.DemographicsType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rsuth
 */
public class PatientDAO {
    
    
    
    
    
    public static long getPatientIdWithUUID(String patientUUID)
    {
        String query = "SELECT patient_demographics.patient_id FROM patient_demographics where patient_uuid= ?" ; 

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        long patientId = 0;
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query);  
                stmt.setString(1,patientUUID);//1 specifies the first parameter in the query  
               
                rs = stmt.executeQuery();
                
                if (rs.next()) {
                        patientId = rs.getLong("patient_id");
                }

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
                catch (Exception ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        return patientId;
    }
    
    public static long insertOrUpdatePatient(long patientId, int facilityId, DemographicsType patientDemo)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        
        StringBuilder query = new StringBuilder("INSERT INTO  patient_demographics(patient_id, first_name, last_name, gender, birthdate, birthdate_estimated, dead, creator, date_created, patient_uuid, phone_number, state_id, lga_id, address, city_village, country_id,");
        query.append("voided, date_voided, voided_by, changed_by, date_changed, facility_id)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        query.append(" ON DUPLICATE KEY UPDATE first_name=VALUES(first_name), last_name=VALUES(last_name), gender=VALUES(gender), birthdate=VALUES(birthdate), birthdate_estimated=VALUES(birthdate_estimated),dead=VALUES(dead), phone_number=VALUES(phone_number), ");
        query.append("state_id=VALUES(state_id), lga_id=VALUES(lga_id), address=VALUES(address), city_village=VALUES(city_village), changed_by=VALUES(changed_by), voided=VALUES(voided), date_voided=VALUES(date_voided), voided_by=VALUES(voided_by)");
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                stmt.setLong(1,patientId);
                stmt.setString(2,patientDemo.getFirstName());
                stmt.setString(3,patientDemo.getLastName());
                stmt.setString(4,patientDemo.getGender());
                stmt.setDate(5, new java.sql.Date(patientDemo.getBirthDate().toGregorianCalendar().getTime().getTime()));
                stmt.setInt(6,patientDemo.getBirthDateEstimated());
                stmt.setInt(7,patientDemo.getDead());
                stmt.setInt(8,patientDemo.getCreator());
                stmt.setLong(9, (patientDemo.getDateCreated() != null) ? patientDemo.getDateCreated().toGregorianCalendar().getTime().getTime() : 0);
                stmt.setString(10,patientDemo.getPatientUUID());
                stmt.setString(11,patientDemo.getPhoneNumber());
                stmt.setInt(12, Misc.getStateId(patientDemo.getStateProvince()));
                stmt.setInt(13, 0);
                stmt.setString(14,patientDemo.getAddress1()+" "+patientDemo.getAddress2() );
                stmt.setString(15,patientDemo.getCityVillage());
                stmt.setInt(16,160);
                stmt.setInt(17,patientDemo.getVoided());
                stmt.setLong(18, (patientDemo.getDateVoided() != null) ? patientDemo.getDateVoided().toGregorianCalendar().getTime().getTime() : 0);
                stmt.setInt(19,patientDemo.getVoidedBy());
                stmt.setInt(20,patientDemo.getChangedBy());
                stmt.setLong(21, (patientDemo.getDateChanged() != null) ? patientDemo.getDateChanged().toGregorianCalendar().getTime().getTime() : 0);
               stmt.setInt(22,facilityId);
                int affectedRows = stmt.executeUpdate();
              
               try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        if(patientId == 0)
                            patientId = (int)generatedKeys.getLong(1);
                       
                    }
                    
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
               
               return patientId;
               
        }
        catch (SQLException ex) {
                //screen.updateStatus(ex.getMessage());
                ex.printStackTrace();
                return patientId;
        }
        finally {
                try {
                        if (rs != null && stmt != null) {
                                //rs.close();
                                stmt.close();
                                Database.connectionPool.free(con);
                               // con.close();
                        }

                }
                catch (Exception ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }
}
