/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.PatientIdentifierType;
import ihvn.data.consumer.model.xml.PatientProgramType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rsuth
 */
public class PatientProgramDAO {
     
    
    public static void  savePatientProgram(String datimId, String messageUUID, List<PatientProgramType> patientPrograms) {
       
        //Connection connection;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {

            con = Database.connectionPool.getConnection();
            StringBuilder query = new StringBuilder("INSERT INTO  patient_program(patient_program_uuid, patient_program_id, patient_id, program_id,  location_id, program_name, date_enrolled, date_completed, outcome_concept_id,  creator, date_created, date_changed, ");
            query.append("changed_by, voided, voided_by, date_voided, patient_uuid, datim_id, message_uuid)VALUES");
            
            
            for(int i=0; i<patientPrograms.size(); i++)
            {
                query.append("(?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),");
                
            }
            
            query.setLength(query.length() - 1);//remove the last comma 
            query.append(" ON DUPLICATE KEY UPDATE location_id=VALUES(location_id),  date_enrolled=VALUES(date_enrolled), date_completed=VALUES(date_completed), outcome_concept_id=VALUES(outcome_concept_id), ");
            query.append( " date_changed=VALUES(date_changed), changed_by=VALUES(changed_by), voided=VALUES(voided), voided_by=VALUES(voided_by), date_voided=VALUES(date_voided) ");
            if(patientPrograms.size() > 0)//this is to ensure that there is actually biometrics before we try to prepare the statement
            {
                 stmt = con.prepareStatement(query.toString());
                 int index=1;
                 for(int i=0; i<patientPrograms.size(); i++)
                 {
                    
                    stmt.setString(index++, patientPrograms.get(i).getPatientProgramUuid());
                    stmt.setInt(index++, patientPrograms.get(i).getPatientProgramId());
                    stmt.setInt(index++, patientPrograms.get(i).getPatientId());
                    stmt.setInt(index++, patientPrograms.get(i).getProgramId());
                    stmt.setInt(index++, patientPrograms.get(i).getLocationId());
                    stmt.setString(index++, patientPrograms.get(i).getProgramName());
                    stmt.setDate(index++, (patientPrograms.get(i).getDateEnrolled()!= null) ? new java.sql.Date(patientPrograms.get(i).getDateEnrolled().toGregorianCalendar().getTime().getTime()) : null);
                    stmt.setDate(index++, (patientPrograms.get(i).getDateCompleted()!= null) ? new java.sql.Date(patientPrograms.get(i).getDateCompleted().toGregorianCalendar().getTime().getTime()) : null);
                    stmt.setInt(index++, patientPrograms.get(i).getOutcomeConceptId());
                    stmt.setInt(index++, patientPrograms.get(i).getCreator());
                    stmt.setDate(index++, (patientPrograms.get(i).getDateCreated() != null) ? new java.sql.Date(patientPrograms.get(i).getDateCreated().toGregorianCalendar().getTime().getTime()) : null);
                    stmt.setDate(index++, (patientPrograms.get(i).getDateChanged()!= null) ? new java.sql.Date(patientPrograms.get(i).getDateChanged().toGregorianCalendar().getTime().getTime()) : null);
                    stmt.setInt(index++, patientPrograms.get(i).getChangedBy());
                    stmt.setInt(index++, patientPrograms.get(i).getVoided());
                    stmt.setLong(index++, patientPrograms.get(i).getVoidedBy());
                    stmt.setDate(index++, (patientPrograms.get(i).getDateVoided() != null) ? new java.sql.Date(patientPrograms.get(i).getDateVoided().toGregorianCalendar().getTime().getTime()) : null);
                    stmt.setString(index++, patientPrograms.get(i).getPatientUuid());
                    stmt.setString(index++, datimId);
                    stmt.setString(index++, messageUUID);
                    
                    
                 }
            }
           
           if(patientPrograms.size() > 0) 
             stmt.executeUpdate();
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
