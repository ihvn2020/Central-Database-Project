/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

import ihvn.data.extractor.model.xml.DemographicsType;
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
public class PatientDAO extends MasterDAO {

    public int getTotalPatients() {
        String query = "SELECT COUNT(patient_id) AS count  FROM patient where patient.voided=0";
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
        } catch (SQLException ex) {
            handleException(ex);
            return 0;
        } finally {
            cleanUp(rs, stmt, con);
        }
    }

    public List<Map<String, String>> getAllPatients(int offset, int limit) {//we are supposed to get only patients who have had something done after the ndr last updated
        String query = "SELECT person.person_id, person.birthdate, person.uuid, person.birthdate_estimated, person.gender, person.dead, person.death_date, ";
        query += " person.cause_of_death, person_name.given_name, person_name.middle_name, person_name.family_name,person_address.address1,person_address.city_village,";
        query += " person.creator, person.date_created, person.changed_by, person.date_changed,person.voided,person.voided_by,person.date_voided,person.void_reason,person.deathdate_estimated,";
        query += " person_address.state_province,person_address.address2,person_address.country, ";
        query += " person_attribute.value AS phone FROM patient ";
        query += " JOIN  person ON (person.person_id=patient.patient_id) ";
        query += " LEFT JOIN person_name ON (person_name.person_id=person.person_id) ";
        query += " LEFT JOIN person_address ON (person_address.person_id=person.person_id and person_address.voided=0) ";
        query += " LEFT JOIN person_attribute ON  person_attribute.person_id=person.person_id AND person_attribute.person_attribute_type_id=8 ";
        //query += " WHERE person.voided=0  AND person.person_id IN (SELECT person_id FROM obs WHERE obs_datetime > '"+lastDate+"')LIMIT "+offset+", "+limit;
        //query += " WHERE person.voided=0 AND person.person_id=14121 LIMIT "+offset+", "+limit;  
        query += " WHERE person.voided=0 LIMIT " + offset + ", " + limit;

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
                tempMap.put("patientId", rs.getString("person_id"));
                tempMap.put("firstName", rs.getString("given_name"));
                tempMap.put("lastName", rs.getString("family_name"));
                tempMap.put("middleName", rs.getString("middle_name"));
                tempMap.put("gender", rs.getString("gender"));
                tempMap.put("birthDate", rs.getString("birthdate"));
                tempMap.put("birthDateEstimated", rs.getString("birthdate_estimated"));
                tempMap.put("dead", rs.getString("dead"));
                tempMap.put("deadDate", rs.getString("death_date"));
                tempMap.put("causeOfDeath", rs.getString("cause_of_death"));
                tempMap.put("creator", rs.getString("creator"));
                tempMap.put("dateCreated", rs.getString("date_created"));
                tempMap.put("changedBy", rs.getString("changed_by"));
                tempMap.put("dateChanged", rs.getString("date_changed"));
                tempMap.put("voided", rs.getString("voided"));
                tempMap.put("voidedBy", rs.getString("voided_by"));
                tempMap.put("dateVoided", rs.getString("date_voided"));
                tempMap.put("voidReason", rs.getString("void_reason"));
                tempMap.put("deathdateEstimated", rs.getString("deathdate_estimated"));
                tempMap.put("phone", rs.getString("phone"));
                tempMap.put("middleName", rs.getString("middle_name"));
                tempMap.put("patientUUID", rs.getString("uuid"));
                tempMap.put("address1", rs.getString("address1"));
                tempMap.put("address2", rs.getString("address2"));
                tempMap.put("cityVillage", rs.getString("city_village"));
                tempMap.put("stateProvince", rs.getString("state_province"));
                allPatients.add(tempMap);

            }
            cleanUp(rs, stmt, con);
            return allPatients;
        } catch (SQLException ex) {
            handleException(ex);
            return null;

        } finally {
            cleanUp(rs, stmt, con);
        }
    }

    
    public Date getPersonNameTimestamp(int patientID){
        Date lastModifiedDate = null;
        String sql_text = "select MAX(GREATEST(person_name.date_created,COALESCE(person_name.date_changed,0),COALESCE(person_name.date_voided,0))) as most_recent from person_name where person_id=?";
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.connectionPool.getConnection();
            ps = con.prepareStatement(sql_text);
            ps.setInt(1, patientID);
            rs = ps.executeQuery();
            while (rs.next()) {
                lastModifiedDate = rs.getDate("most_recent");
            }
            cleanUp(rs, ps, con);
        } catch (SQLException ex) {
            handleException(ex);
        } finally {
            cleanUp(rs, ps, con);
        }
        return lastModifiedDate;
    }
    public Date getPersonAddressTimestamp(int patientID){
        Date lastModifiedDate = null;
        String sql_text = "select MAX(GREATEST(person_address.date_created,COALESCE(person_address.date_changed,0),COALESCE(person_address.date_voided,0))) as most_recent from person_address where person_id=?";
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.connectionPool.getConnection();
            ps = con.prepareStatement(sql_text);
            ps.setInt(1, patientID);
            rs = ps.executeQuery();
            while (rs.next()) {
                lastModifiedDate = rs.getDate("most_recent");
            }
            cleanUp(rs, ps, con);
        } catch (SQLException ex) {
            handleException(ex);
        } finally {
            cleanUp(rs, ps, con);
        }
        return lastModifiedDate;
    }
   
    public Date getPatientTimestamp(int patientID) {
        Date lastModifiedDate = null;
        String sql_text = "select MAX(GREATEST(patient.date_created,COALESCE(patient.date_changed,0),COALESCE(patient.date_voided,0))) as most_recent from patient where patient_id=?";
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = Database.connectionPool.getConnection();
            ps = con.prepareStatement(sql_text);
            ps.setInt(1, patientID);
            rs = ps.executeQuery();
            while (rs.next()) {
                lastModifiedDate = rs.getDate("most_recent");
            }
            cleanUp(rs, ps, con);
        } catch (SQLException ex) {
            handleException(ex);
        } finally {
            cleanUp(rs, ps, con);
        }
        return lastModifiedDate;
    }

    

    public Date getPersonTimestamp(int patientID) {
        Date lastModifiedDate = null;
        String sql_text = "select MAX(GREATEST(person.date_created,COALESCE(person.date_changed,0),COALESCE(person.date_voided,0))) as most_recent from person where person_id=?";
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement ps=null;
        try {
            con = Database.connectionPool.getConnection();
            //stmt = Database.conn.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
            //stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
            //stmt.setFetchSize(Integer.MIN_VALUE);
            ps=con.prepareStatement(sql_text);
            ps.setInt(1, patientID);
            rs = ps.executeQuery(sql_text);
            while (rs.next()) {
                lastModifiedDate = rs.getDate("most_recent");
            }
            cleanUp(rs, ps, con);
        } catch (SQLException ex) {
            handleException(ex);
        } finally {
            cleanUp(rs, ps, con);
        }
        return lastModifiedDate;
    }

    
}
