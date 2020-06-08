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
    
    public static String insertOrUpdatePatient(DemographicsType patientDemo, String datimId, String messageUUID)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        
        StringBuilder query = new StringBuilder("INSERT INTO  demographics(patient_uuid, patient_id, first_name, last_name, middle_name, gender, birthdate, birthdate_estimated, dead, death_date, cause_of_death, creator, date_created, phone_number, address1, address2, city_village, state_province, country,");
        query.append(" changed_by, date_changed, voided_by, voided, date_voided,  voided_reason, deathdate_estimated, datim_id, message_uuid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?)");
        query.append(" ON DUPLICATE KEY UPDATE patient_id=VALUES(patient_id), first_name=VALUES(first_name), last_name=VALUES(last_name), middle_name=VALUES(middle_name),  gender=VALUES(gender), birthdate=VALUES(birthdate), birthdate_estimated=VALUES(birthdate_estimated), dead=VALUES(dead), death_date=VALUES(death_date), ");
        query.append(" cause_of_death=VALUES(cause_of_death), creator=VALUES(creator), date_created=VALUES(date_created), phone_number=VALUES(phone_number), address1=VALUES(address1), address2=VALUES(address2), city_village=VALUES(city_village), ");
        query.append(" state_province=VALUES(state_province), country=VALUES(country), changed_by=VALUES(changed_by), date_changed=VALUES(date_changed), voided=VALUES(voided), voided_by=VALUES(voided_by), date_voided=VALUES(date_voided), voided_reason=VALUES(voided_reason), deathdate_estimated=VALUES(deathdate_estimated), message_uuid=VALUES(message_uuid)");
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                
                int i = 1;
                stmt.setString(i++, patientDemo.getPatientUuid());
                stmt.setLong(i++, patientDemo.getPatientId());
                stmt.setString(i++,patientDemo.getFirstName());
                stmt.setString(i++,patientDemo.getLastName());
                stmt.setString(i++,patientDemo.getMiddleName());
                stmt.setString(i++,patientDemo.getGender());
                stmt.setDate(i++, new java.sql.Date(patientDemo.getBirthdate().toGregorianCalendar().getTime().getTime()));
                stmt.setInt(i++,patientDemo.getBirthdateEstimated());
                stmt.setInt(i++,patientDemo.getDead());
                stmt.setDate(i++, (patientDemo.getDateChanged() != null) ? new java.sql.Date(patientDemo.getDeathDate().toGregorianCalendar().getTime().getTime()) : null);
                stmt.setString(i++, patientDemo.getCauseOfDeath());//cause of death
                stmt.setInt(i++,patientDemo.getCreator());
                stmt.setDate(i++, (patientDemo.getDateCreated() != null) ? new java.sql.Date(patientDemo.getDateCreated().toGregorianCalendar().getTime().getTime()) : null);
                stmt.setString(i++,patientDemo.getPhoneNumber());
                stmt.setString(i++,patientDemo.getAddress1());
                stmt.setString(i++,patientDemo.getAddress2());
                stmt.setString(i++,patientDemo.getCityVillage());
                stmt.setString(i++,patientDemo.getStateProvince());
                stmt.setString(i++,patientDemo.getCountry());
                stmt.setInt(i++,patientDemo.getChangedBy());
                stmt.setDate(i++, (patientDemo.getDateChanged() != null) ? new java.sql.Date(patientDemo.getDateChanged().toGregorianCalendar().getTime().getTime()) : null);
                stmt.setInt(i++, patientDemo.getVoidedBy());
                stmt.setInt(i++,patientDemo.getVoided());
                stmt.setDate(i++, (patientDemo.getDateVoided() != null) ? new java.sql.Date(patientDemo.getDateVoided().toGregorianCalendar().getTime().getTime()) : null);
                stmt.setString(i++, patientDemo.getVoidedReason());//voided reason
                stmt.setInt(i++, patientDemo.getDeathdateEstimated());
                stmt.setString(i++, datimId);
                stmt.setString(i++, messageUUID);
               
                stmt.executeUpdate();
                //stmt.setInt(22,facilityId);
                /*int affectedRows = stmt.executeUpdate();
              
               try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        if(patientId == 0)
                            patientId = (int)generatedKeys.getLong(1);
                       
                    }
                    
                }catch(Exception e)
                {
                    e.printStackTrace();
                }*/
               
               return patientDemo.getPatientUuid();
               
        }
        catch (SQLException ex) {
                //screen.updateStatus(ex.getMessage());
                ex.printStackTrace();
                return patientDemo.getPatientUuid();
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
