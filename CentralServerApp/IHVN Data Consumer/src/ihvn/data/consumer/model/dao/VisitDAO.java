/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.EncounterType;
import ihvn.data.consumer.model.xml.ObsType;
import ihvn.data.consumer.model.xml.VisitType;
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
public class VisitDAO {
    
    public static void deleteVisits(long patientId)
    {
        String query = "DELETE FROM visits  WHERE patient_id= ?" ; 
        String queryEncounter = "DELETE FROM encounters  WHERE patient_id= ?" ; 
        String queryObs = "DELETE FROM obs  WHERE patient_id= ?" ; 
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query);  
                stmt.setLong(1,patientId); 
                stmt.executeUpdate();
                
                stmt= con.prepareStatement(queryEncounter);  
                stmt.setLong(1,patientId);  
                stmt.executeUpdate();
                
                stmt= con.prepareStatement(queryObs);  
                stmt.setLong(1,patientId);  
                stmt.executeUpdate();
               
              
                

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
    
    public static long saveVisits(long patientId, int facilityId,  List<VisitType> visits)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        int visitId = 0;
        StringBuilder query = new StringBuilder("INSERT INTO  visits(patient_id, visit_type_id, date_started, date_stopped, facility_id, created_by, date_created, date_changed, changed_by,");
        query.append("voided, date_voided, voided_by, visit_uuid)VALUES");
        if(visits != null){
    
            for(int i=0; i<visits.size(); i++)
            {
                query.append("(?,?,?,?,?,?,?,?,?,?,?,?,?),");
            }
            query.setLength(query.length() - 1);//remove the last comma

            try {
                    con = Database.connectionPool.getConnection();
                    stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                    int i = 1;
                    for(int j=0; j<visits.size(); j++){
                        stmt.setLong(i++,patientId);
                        stmt.setInt(i++,visits.get(j).getVisitTypeId());
                        stmt.setDate(i++, (visits.get(j).getDateStarted()!= null) ? new java.sql.Date(visits.get(j).getDateStarted().toGregorianCalendar().getTime().getTime()) : new java.sql.Date(new Date().getTime()));
                        stmt.setDate(i++, (visits.get(j).getDateStopped() != null) ? new java.sql.Date(visits.get(j).getDateStopped().toGregorianCalendar().getTime().getTime()) : new java.sql.Date(new Date().getTime()));
                        stmt.setInt(i++, facilityId);
                        stmt.setLong(i++, visits.get(j).getCreator());
                        stmt.setLong(i++,  (visits.get(j).getDateCreated() != null) ? visits.get(j).getDateCreated().toGregorianCalendar().getTime().getTime() : 0);
                        stmt.setLong(i++, (visits.get(j).getDateChanged()!= null) ? visits.get(j).getDateChanged().toGregorianCalendar().getTime().getTime() : 0);
                        stmt.setLong(i++, visits.get(j).getChangedBy());
                        stmt.setInt(i++, visits.get(j).getVoided());
                        stmt.setLong(i++, (visits.get(j).getDateVoided() != null) ? visits.get(j).getDateVoided().toGregorianCalendar().getTime().getTime() : 0);
                        stmt.setLong(i++, visits.get(j).getVoidedBy());
                        stmt.setString(i++, visits.get(j).getVisit_uuid());
                    }
                    int affectedRows = stmt.executeUpdate();

                   try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                          visitId = (int)generatedKeys.getLong(1);

                        }

                    }


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
        return visitId;
    }
    
    
    public static long saveEncounters(long patientId, long firstVisitId,  List<VisitType> visits)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        int encounterId = 0;
        StringBuilder query = new StringBuilder("INSERT INTO  encounters(patient_id, visit_id, encounter_type_id, form_id, encounter_datetime, encounter_uuid, created_by, date_created, date_changed, changed_by,");
        query.append("voided, date_voided, voided_by)VALUES");
        if(visits != null)//ensure that this patient has visits
        {
            for(int i=0; i<visits.size(); i++)
            {

                if(visits.get(i).getEncounter() != null){//ensure that the current visit has an encounter
                     for(int j=0; j<visits.get(i).getEncounter().size(); j++)
                    {
                        query.append("(?,?,?,?,?,?,?,?,?,?,?,?,?),");
                    }
                }
               

            }
        }
        
        query.setLength(query.length() - 1);//remove the last comma
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                int i = 1;
                EncounterType currEncounter = null;
                if(visits != null)//ensure that this patient has visits
                {
                    for(int j=0; j<visits.size(); j++){

                        if(visits.get(j).getEncounter() != null){//ensure that the current visit has an encounter
                            for(int k=0; k<visits.get(j).getEncounter().size(); k++)
                            {
                                currEncounter = visits.get(j).getEncounter().get(k);
                                stmt.setLong(i++,patientId);
                                stmt.setLong(i++, firstVisitId);
                                stmt.setInt(i++, currEncounter.getEncounterTypeId());
                                stmt.setInt(i++, currEncounter.getFormId());
                                stmt.setDate(i++, (currEncounter.getEncounterDateTime() != null) ? new java.sql.Date(currEncounter.getEncounterDateTime().toGregorianCalendar().getTime().getTime()) : new java.sql.Date(new Date().getTime()));
                                stmt.setString(i++, currEncounter.getEncounterUUID());
                                stmt.setLong(i++, currEncounter.getCreator());
                                stmt.setLong(i++, (currEncounter.getDateCreated() != null) ? currEncounter.getDateCreated().toGregorianCalendar().getTime().getTime() : 0);
                                stmt.setLong(i++, (currEncounter.getDateChanged() != null) ? currEncounter.getDateChanged().toGregorianCalendar().getTime().getTime() : 0);

                                stmt.setLong(i++, currEncounter.getChangedBy());

                                stmt.setInt(i++, currEncounter.getVoided());

                                stmt.setLong(i++, (currEncounter.getDateVoided() != null) ? currEncounter.getDateVoided().toGregorianCalendar().getTime().getTime() : 0);

                                stmt.setLong(i++, currEncounter.getVoidedBy());
                            }
                            firstVisitId++;//we did a bulk insert for visit. for each iteration, increment the visit id to match with the right vist
                        }
                    }
                }
                int affectedRows = stmt.executeUpdate();
              
               try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                      encounterId = (int)generatedKeys.getLong(1);
                       
                    }
                    
                }
               
               
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
        return encounterId;
    }
    
    
    public static long saveObs(long patientId, long firstEncounterId,  List<VisitType> visits)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        int encounterId = 0;
        StringBuilder query = new StringBuilder("INSERT INTO  obs(patient_id, concept_id, encounter_id, obs_datetime, obs_group_id, variable_name, variable_value, obs_value, obs_value_type, created_by, date_created,");
        query.append("voided, date_voided, voided_by)VALUES");
        
        if(visits != null){
            for(int i=0; i<visits.size(); i++)
            {
                if(visits.get(i).getEncounter() != null)
                {
                   for(int j=0; j<visits.get(i).getEncounter().size(); j++)
                   {
                       if(visits.get(i).getEncounter().get(j).getObsList() != null){
                           for(int k=0; k<visits.get(i).getEncounter().get(j).getObsList().size(); k++)
                            {
                                query.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
                            }
                       }
                        

                   } 
                }
                

            }
        }
        query.setLength(query.length() - 1);//remove the last comma
         String[] obsValues = null;
         ObsType currObs = null;
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                int i = 1;
                
               if(visits != null){
                    for(int j=0; j<visits.size(); j++){
                     
                        if(visits.get(j).getEncounter() != null){
                            for(int k=0; k<visits.get(j).getEncounter().size(); k++)
                            {
                                if(visits.get(j).getEncounter().get(k).getObsList() != null){
                                    for(int l=0; l<visits.get(j).getEncounter().get(k).getObsList().size(); l++)
                                    {
                                        currObs = visits.get(j).getEncounter().get(k).getObsList().get(l);
                                        stmt.setLong(i++,patientId);
                                        stmt.setInt(i++,currObs.getConceptId());
                                        stmt.setLong(i++, firstEncounterId);
                                        stmt.setDate(i++, (currObs.getObsDateTime() != null) ? new java.sql.Date(currObs.getObsDateTime().toGregorianCalendar().getTime().getTime()) : new java.sql.Date(new Date().getTime()));
                                        stmt.setInt(i++, currObs.getObsGroupId());                          
                                        obsValues = Misc.getObsValue(currObs);
                                        stmt.setString(i++, currObs.getVariableName());
                                        stmt.setString(i++, currObs.getVariableValue());
                                        stmt.setString(i++, (obsValues[0] != null) ?  obsValues[0] : "");
                                        stmt.setString(i++, (obsValues[1] != null) ? obsValues[1]: "");
                                        stmt.setLong(i++, currObs.getCreator());
                                        stmt.setLong(i++, (currObs.getDateCreated() != null) ? currObs.getDateCreated().toGregorianCalendar().getTime().getTime() : 0);

                                        stmt.setInt(i++, currObs.getVoided());
                                        stmt.setLong(i++, (currObs.getDateVoided() != null) ? currObs.getDateVoided().toGregorianCalendar().getTime().getTime() : 0);
                                        stmt.setLong(i++, currObs.getVoidedBy());
                                    }
                                    firstEncounterId++;//we did a bulk insert for visit. for each iteration, increment the visit id to match with the right vist
                                }
                                
                            }
                        }
                    }
               }
                int affectedRows = stmt.executeUpdate();
              
               
               
               
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
                            // con.close();
                        }

                }
                catch (SQLException ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        return 1;
    }
}
