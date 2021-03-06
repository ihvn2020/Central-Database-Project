/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

import ihvn.data.extractor.model.xml.DemographicsType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rsuth
 */
public class PatientDAO {
    
    
    public int getTotalPatients() {
        String query = "SELECT COUNT(patient_id) AS count  FROM patient ";
        //String query = "SELECT COUNT(obs_id) AS count  FROM obs ";
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
                con = Database.connectionPool.getConnection();
                //stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
                stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);

                //stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
                stmt.setFetchSize(Integer.MIN_VALUE);
                rs = stmt.executeQuery(query);

                rs.next();
                int totalCount = rs.getInt("count");

                return totalCount;
        }
        catch (SQLException ex) {
                //screen.updateStatus(ex.getMessage());
                ex.printStackTrace();
                return 0;
        }
        finally {
                try {
                        rs.close();
                        stmt.close();
                        Database.connectionPool.free(con);
                }
                catch (SQLException ex) {
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    public List<Map<String, String>> getAllPatients(int offset, int limit) {//we are supposed to get only patients who have had something done after the ndr last updated
            String query = "SELECT person.person_id, person.birthdate, person.uuid, person.birthdate_estimated, person.gender, person.dead, person.death_date, ";
            query += " person.cause_of_death, person_name.given_name, person_name.middle_name, person_name.family_name,  ";
            query += " person_attribute.value AS phone FROM patient ";
            query += " JOIN  person ON person.person_id=patient.patient_id ";
            query += " LEFT JOIN person_name ON person_name.person_id=person.person_id ";
            query += " LEFT JOIN person_attribute ON  person_attribute.person_id=person.person_id AND person_attribute.person_attribute_type_id=8 ";
            //query += " WHERE person.voided=0  AND person.person_id IN (SELECT person_id FROM obs WHERE obs_datetime > '"+lastDate+"')LIMIT "+offset+", "+limit;
            //query += " WHERE person.voided=0 AND person.person_id=14121 LIMIT "+offset+", "+limit;  
            query += " WHERE person.voided=0 LIMIT "+offset+", "+limit;  
              
            Statement stmt = null;
            ResultSet rs = null;
            Connection con = null;
            List<Map<String, String>> allPatients = new ArrayList<>();
            try {
                
                con = Database.connectionPool.getConnection();
                //stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
                stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
                stmt.setFetchSize(Integer.MIN_VALUE);
                rs = stmt.executeQuery(query);
                
                while (rs.next()) {
                    Map<String, String> tempMap = new HashMap<>();
                    tempMap.put("patientId",  rs.getString("person_id"));
                    tempMap.put("birthDate",  rs.getString("birthdate"));
                    tempMap.put("birthDateEstimated",  rs.getString("birthdate_estimated"));
                    tempMap.put("gender",  rs.getString("gender"));
                    tempMap.put("dead",  rs.getString("dead"));
                    tempMap.put("deadDate",  rs.getString("death_date"));
                    tempMap.put("firstName",  rs.getString("given_name"));
                    tempMap.put("lastName",  rs.getString("family_name"));
                    tempMap.put("middleName",  rs.getString("middle_name"));
                    tempMap.put("phone",  rs.getString("phone"));
                    tempMap.put("middleName",  rs.getString("middle_name"));
                    tempMap.put("patientUUID",  rs.getString("uuid"));
                   
                   
                     
                    
                    allPatients.add(tempMap);
                   
                    
                }
                rs.close();
                stmt.close();
                Database.connectionPool.free(con);
                return allPatients;
            } catch (SQLException ex) {
                //screen.updateStatus(ex.getMessage());
                ex.printStackTrace();
                return null;
                
            } finally {
                try {
                        rs.close();
                        stmt.close();
                        Database.connectionPool.free(con);
                    } catch (SQLException ex) {
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
	}
}
