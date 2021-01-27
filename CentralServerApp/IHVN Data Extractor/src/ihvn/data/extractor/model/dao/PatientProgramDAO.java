/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

import ihvn.data.extractor.model.xml.PatientIdentifierType;
import ihvn.data.extractor.model.xml.PatientProgramType;
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
 * @author rsuth
 */
public class PatientProgramDAO extends MasterDAO{
    public List<PatientProgramType> getAllPatientPrograms(int patientId)
    {
        String query = "SELECT patient_program.*, program.name AS program_name FROM patient_program "
                + " JOIN program ON program.program_id=patient_program.program_id "
                + "WHERE patient_id="+patientId;  
              
            Statement stmt = null;
            ResultSet rs = null;
            Connection con = null;
            List<PatientProgramType> allIdentifiers = new ArrayList<>();
            //System.out.println("Connection list "+Database.connectionPool.totalConnections());
            try {
                con = Database.connectionPool.getConnection();
                stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
                
                stmt.setFetchSize(Integer.MIN_VALUE);
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    PatientProgramType patientProgram = buildPatientProgram(rs);
                   
                    allIdentifiers.add(patientProgram);
                    
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
                    ex.printStackTrace();
                    //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
    }
    
    private PatientProgramType buildPatientProgram(ResultSet rs) throws SQLException
    {
        PatientProgramType patientProgram = new PatientProgramType();
        
        patientProgram.setPatientProgramId(rs.getInt("patient_program_id"));
        patientProgram.setProgramId(rs.getInt("program_id"));
        patientProgram.setProgramName("program_name");
        patientProgram.setDateEnrolled(Misc.getXMLdateTime(rs.getDate("date_enrolled")));
        if(rs.getString("date_completed") != null)
        {
            patientProgram.setDateCompleted(Misc.getXMLdateTime(rs.getDate("date_completed")));
        }
        
        patientProgram.setOutcomeConceptId(rs.getInt("outcome_concept_id"));
        
        
        patientProgram.setCreator(rs.getInt("creator"));
        patientProgram.setDateCreated(Misc.getXMLdateTime(rs.getDate("date_created")));
        patientProgram.setChangedBy(rs.getInt("changed_by"));
        if(rs.getString("date_changed") != null)
        {
            patientProgram.setDateChanged(Misc.getXMLdateTime(rs.getDate("date_changed")));
        }
        patientProgram.setVoided(rs.getInt("voided"));
        patientProgram.setVoidedBy(rs.getInt("voided_by"));
        //patientProgram.setVoidedByName("");
        if(rs.getString("date_voided") != null)
        {
             patientProgram.setDateVoided(Misc.getXMLdateTime(rs.getDate("date_voided")));
        }
       
        patientProgram.setPatientProgramUuid(rs.getString("uuid"));
        
        return patientProgram;
        
    }
     public Date getPatientProgramTimestamp(int patientID){
        Date lastModifiedDate = null;
        String sql_text = "select MAX(GREATEST(patient_program.date_created,COALESCE(patient_program.date_changed,0),COALESCE(patient_program.date_voided,0))) as most_recent from patient_program where patient_id=?";
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
