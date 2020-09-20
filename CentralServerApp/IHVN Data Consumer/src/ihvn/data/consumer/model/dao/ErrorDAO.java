/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.PatientBiometricType;
import ihvn.data.consumer.models.ValidationError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lordmaul
 */
public class ErrorDAO {
    public static void  saveErrors(String patientUUID, String datimId, String messageUUID, List<ValidationError> errors) {
        
        //Connection connection;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {

            con = Database.connectionPool.getConnection();
            StringBuilder query = new StringBuilder("INSERT INTO  validation_error(error_id, patient_uuid, datim_id, visit_uuid, encounter_uuid, obs_uuid, error_code, ");
            query.append("error_message, pmm_form, variable_name, variable_value, visit_date, ignore_patient, ignore_form, ignore_variable, message_uuid)VALUES");
            
            
            for(int i=0; i<errors.size(); i++)
            {
               query.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),");
                
            } 
            query.setLength(query.length() - 1);//remove the last comma
            if(errors.size() > 0)//this is to ensure that there is actually biometrics before we try to prepare the statement
            {
                 stmt = con.prepareStatement(query.toString());
                 int index=1;
                 for(int i=0; i<errors.size(); i++)
                 {
                    
                    stmt.setLong(index++, errors.get(i).getErrorId());
                    stmt.setString(index++, errors.get(i).getPatientUUID());
                    stmt.setString(index++, datimId);
                    stmt.setString(index++, errors.get(i).getVisitUUID());
                    stmt.setString(index++, errors.get(i).getEncounterUUID());
                    stmt.setString(index++, errors.get(i).getObsUUID());
                    stmt.setString(index++, errors.get(i).getErrorCode());
                    stmt.setString(index++, errors.get(i).getErrorMessage());
                    stmt.setString(index++, errors.get(i).getPmmForm());
                    stmt.setString(index++, errors.get(i).getVariableName());
                    stmt.setString(index++, errors.get(i).getVariableValue());
                    stmt.setString(index++, (errors.get(i).getVisitDate() != null) ? errors.get(i).getVisitDate().toString() : null);
                    stmt.setInt(index++, errors.get(i).getIgnorePatient());
                    stmt.setInt(index++, errors.get(i).getIgnoreForm());
                    stmt.setInt(index++, errors.get(i).getIgnoreVariable());
                    stmt.setString(index++, messageUUID);
                    
                    
                 }
            }
           
            if(errors.size() > 0)
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
