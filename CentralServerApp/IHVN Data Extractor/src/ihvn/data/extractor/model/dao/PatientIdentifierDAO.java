/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

import ihvn.data.extractor.model.xml.PatientIdentifierType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lordmaul
 */
public class PatientIdentifierDAO extends MasterDAO {
 
    public List<PatientIdentifierType> getAllPatientIdentifiers(int patientId)
    {
        String query = "SELECT patient_identifier.* FROM patient_identifier WHERE patient_id="+patientId;  
              
            Statement stmt = null;
            ResultSet rs = null;
            Connection con = null;
            List<PatientIdentifierType> allIdentifiers = new ArrayList<>();
            //System.out.println("Connection list "+Database.connectionPool.totalConnections());
            try {
                con = Database.connectionPool.getConnection();
                stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
                
                stmt.setFetchSize(Integer.MIN_VALUE);
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatientIdentifierType idType = buildIdentifier(rs);
                   
                    allIdentifiers.add(idType);
                    
                }
                //rs.close();
                //stmt.close();
                return allIdentifiers;
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
    
    private PatientIdentifierType buildIdentifier(ResultSet rs) throws SQLException
    {
        PatientIdentifierType idType = new PatientIdentifierType();
        
        idType.setPatientIdentifierId(rs.getInt("patient_identifier_id"));
        idType.setPatientId(rs.getInt("patient_id"));
        idType.setIdentifier(rs.getString("identifier"));
        idType.setIdentifierType(rs.getInt("identifier_type"));
        idType.setPreferred(rs.getInt("preferred"));
        idType.setCreator(rs.getInt("creator"));
        idType.setPatientIdentifierUuid(rs.getString("uuid"));
        idType.setDateCreated(Misc.getXMLdateTime(rs.getDate("date_created")));
        idType.setChangedBy(rs.getInt("changed_by"));
        if(rs.getString("date_changed") != null)
        {
            idType.setDateChanged(Misc.getXMLdateTime(rs.getDate("date_changed")));
        }
        idType.setVoided(rs.getInt("voided"));
        idType.setVoidedBy(rs.getInt("voided_by"));
        //idType.setVoidedByName("");
        if(rs.getString("date_voided") != null)
        {
             idType.setDateVoided(Misc.getXMLdateTime(rs.getDate("date_voided")));
        }
       
        
        return idType;
        
    }
    public Date getPatientIdentifierTimestamp(int patientID) {
        Date lastModifiedDate = null;
        String sql_text = "select MAX(GREATEST(patient_identifier.date_created,COALESCE(patient_identifier.date_changed,0),COALESCE(patient_identifier.date_voided,0))) as most_recent from patient_identifier where patient_id=?";
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
}

