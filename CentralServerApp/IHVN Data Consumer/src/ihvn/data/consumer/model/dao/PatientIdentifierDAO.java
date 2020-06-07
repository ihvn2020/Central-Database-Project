/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.PatientIdentifierType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lordmaul
 */
public class PatientIdentifierDAO {
 
    public static void deleteExisitingIdentifiers(long patientId)
    {
        String query = "DELETE FROM patient_identifiers  WHERE patient_id= ?" ; 

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
    
    public static void  savePatientIdentifiers(long patientId, List<PatientIdentifierType> patientIdentifiers) {
        long id = patientId;
        //Connection connection;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {

            con = Database.connectionPool.getConnection();
            StringBuilder query = new StringBuilder("INSERT INTO  patient_identifiers(patient_id, identifier, identifier_type, patient_identifier_uuid, ");
            query.append("created_by, date_created, voided, date_voided, voided_by)VALUES");
            
            
            for(int i=0; i<patientIdentifiers.size(); i++)
            {
                query.append("(?,?,?,?,?,?,?,?,?),");
                
            }
            
            query.setLength(query.length() - 1);//remove the last comma
            if(patientIdentifiers.size() > 0)//this is to ensure that there is actually biometrics before we try to prepare the statement
            {
                 stmt = con.prepareStatement(query.toString());
                  int index=1;
                 for(int i=0; i<patientIdentifiers.size(); i++)
                 {
                    
                    stmt.setLong(index++, patientId);
                    stmt.setString(index++, patientIdentifiers.get(i).getIdentifier());
                    stmt.setInt(index++, patientIdentifiers.get(i).getIdentifierType());
                    stmt.setString(index++, patientIdentifiers.get(i).getPatientIdentifierUUID());
                    stmt.setLong(index++, patientIdentifiers.get(i).getCreator());
                    stmt.setLong(index++, (patientIdentifiers.get(i).getDateCreated() != null) ? patientIdentifiers.get(i).getDateCreated().toGregorianCalendar().getTime().getTime() : 0);
                    stmt.setInt(index++, patientIdentifiers.get(i).getVoided());
                    stmt.setLong(index++, (patientIdentifiers.get(i).getDateVoided() != null) ? patientIdentifiers.get(i).getDateVoided().toGregorianCalendar().getTime().getTime() : 0);
                    stmt.setLong(index++, patientIdentifiers.get(i).getVoidedBy());
                    
                    
                 }
            }
           
            
            int affectedRows = stmt.executeUpdate();
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

