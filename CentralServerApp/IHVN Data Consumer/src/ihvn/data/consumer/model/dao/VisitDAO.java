/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.EncounterType;
import ihvn.data.consumer.model.xml.ObsType;
import ihvn.data.consumer.model.xml.VisitType;
import java.math.BigDecimal;
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
    
    public static  void saveVisits(String datimId, String messageUUID,  List<VisitType> visits)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        int visitId = 0;
        StringBuilder query = new StringBuilder("INSERT INTO  visit(visit_uuid, visit_id, patient_id, location_id, visit_type_id, date_started, date_stopped, creator, date_created, changed_by, date_changed,");
        query.append(" voided, voided_by, date_voided, patient_uuid, datim_id, message_uuid)VALUES ");
        
        if(visits != null){
    
            for(int i=0; i<visits.size(); i++)
            {
                query.append("(?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?),");
            }
            query.setLength(query.length() - 1);//remove the last comma
            query.append(" ON DUPLICATE KEY UPDATE location_id=VALUES(location_id), visit_type_id=VALUES(visit_type_id), date_started=VALUES(date_started), date_stopped=VALUES(date_stopped),");
        query.append(" changed_by=VALUES(changed_by), date_changed=VALUES(date_changed), voided=VALUES(voided), voided_by=VALUES(voided_by), date_voided=VALUES(date_voided)");
            try { 
                    con = Database.connectionPool.getConnection();
                    //stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                    stmt= con.prepareStatement(query.toString()); 
                    int i = 1;
                    for(int j=0; j<visits.size(); j++){
                        stmt.setString(i++, visits.get(j).getVisitUuid());
                        stmt.setLong(i++, visits.get(j).getVisitId());
                        stmt.setLong(i++, visits.get(j).getPatientId());
                        stmt.setLong(i++, visits.get(j).getLocationId());
                        stmt.setLong(i++, visits.get(j).getVisitTypeId());
                        
                        stmt.setDate(i++, (visits.get(j).getDateStarted()!= null) ? new java.sql.Date(visits.get(j).getDateStarted().toGregorianCalendar().getTime().getTime()) : new java.sql.Date(new Date().getTime()));
                        stmt.setDate(i++, (visits.get(j).getDateStopped() != null) ? new java.sql.Date(visits.get(j).getDateStopped().toGregorianCalendar().getTime().getTime()) : new java.sql.Date(new Date().getTime()));
                       
                        stmt.setLong(i++, visits.get(j).getCreator());
                        stmt.setDate(i++,  (visits.get(j).getDateCreated() != null) ? new java.sql.Date(visits.get(j).getDateCreated().toGregorianCalendar().getTime().getTime()) : null);
                        stmt.setLong(i++, visits.get(j).getChangedBy());
                        stmt.setDate(i++, (visits.get(j).getDateChanged()!= null) ? new java.sql.Date(visits.get(j).getDateChanged().toGregorianCalendar().getTime().getTime()) : null);
                        
                        stmt.setInt(i++, visits.get(j).getVoided());
                        stmt.setLong(i++, visits.get(j).getVoidedBy());
                        stmt.setDate(i++, (visits.get(j).getDateVoided() != null) ? new java.sql.Date(visits.get(j).getDateVoided().toGregorianCalendar().getTime().getTime()) : null);
                       
                        stmt.setString(i++, visits.get(j).getPatientUuid());
                        stmt.setString(i++, datimId);
                        stmt.setString(i++, messageUUID);
                    }
                    
                    if(visits.size() > 0)
                        stmt.executeUpdate();

            }
            catch (SQLException ex) {
                System.out.println(query);
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
    }
    
    
    public static long saveEncounters(String patientUUID, String datimId, String messageUUID,  List<EncounterType> encounters)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        int encounterId = 0;
        StringBuilder query = new StringBuilder("INSERT INTO  encounter(encounter_uuid, encounter_id, encounter_type_id, patient_id, location_id, form_id, pmm_form, encounter_datetime, creator, date_created, voided, voided_by, date_voided,");
        query.append("changed_by, date_changed, visit_id, patient_uuid, datim_id, visit_uuid, message_uuid)VALUES");
        

        if(encounters != null){//ensure that the current visit has an encounter
             for(int j=0; j<encounters.size(); j++)
            {
                query.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),");
                //let's init the encounter validation data here
                
            }
        }
        query.setLength(query.length() - 1);//remove the last comma
        query.append(" ON DUPLICATE KEY UPDATE location_id=VALUES(location_id), form_id=VALUES(form_id), pmm_form=VALUES(pmm_form), encounter_datetime=VALUES(encounter_datetime), ");
        query.append(" voided=VALUES(voided), voided_by=VALUES(voided_by), date_voided=VALUES(date_voided), changed_by=VALUES(changed_by), date_changed=VALUES(date_changed)");
        try {
                con = Database.connectionPool.getConnection();
                //stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
                stmt= con.prepareStatement(query.toString());
                int i = 1;
                EncounterType currEncounter = null;
                

                if(encounters != null){//ensure that the current visit has an encounter
                    for(int k=0; k<encounters.size(); k++)
                    {
                        currEncounter = encounters.get(k);
                        stmt.setString(i++,currEncounter.getEncounterUuid());
                        stmt.setInt(i++,currEncounter.getEncounterId());
                        stmt.setInt(i++,currEncounter.getEncounterTypeId());
                        stmt.setInt(i++,currEncounter.getPatientId());
                        stmt.setInt(i++,currEncounter.getLocationId());
                        stmt.setInt(i++,currEncounter.getFormId());
                        stmt.setString(i++,currEncounter.getPmmForm());
                        stmt.setDate(i++, (currEncounter.getEncounterDatetime()!= null) ? new java.sql.Date(currEncounter.getEncounterDatetime().toGregorianCalendar().getTime().getTime()) : null);
                        stmt.setInt(i++, currEncounter.getCreator());
                        stmt.setDate(i++, (currEncounter.getDateCreated()!= null) ? new java.sql.Date(currEncounter.getDateCreated().toGregorianCalendar().getTime().getTime()) : null);
                        stmt.setInt(i++, currEncounter.getVoided());
                        stmt.setInt(i++, currEncounter.getVoidedBy());
                        stmt.setDate(i++, (currEncounter.getDateVoided()!= null) ? new java.sql.Date(currEncounter.getDateVoided().toGregorianCalendar().getTime().getTime()) : null);
                        stmt.setInt(i++, currEncounter.getChangedBy());
                        stmt.setDate(i++, (currEncounter.getDateChanged()!= null) ? new java.sql.Date(currEncounter.getDateChanged().toGregorianCalendar().getTime().getTime()) : null);
                        stmt.setInt(i++, currEncounter.getVisitId());
                        stmt.setString(i++, currEncounter.getPatientUuid());
                        stmt.setString(i++, currEncounter.getDatimId());
                        stmt.setString(i++,currEncounter.getVisitUuid());
                        stmt.setString(i++, messageUUID);
                        
                    }
                    //firstVisitId++;//we did a bulk insert for visit. for each iteration, increment the visit id to match with the right vist
                      
                }
                if(encounters.size() > 0)
                    stmt.executeUpdate();
                /*int affectedRows = stmt.executeUpdate();
              
               try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                      encounterId = (int)generatedKeys.getLong(1);
                       
                    }
                    
                }*/
               
               
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
    
    
    public static long saveObs(String datimId, String messageUUID,  List<ObsType> obsList)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        int encounterId = 0;
        StringBuilder query = new StringBuilder("INSERT INTO  obs(obs_uuid, obs_id, person_id, concept_id, encounter_id, form_id, pmm_form, encounter_type,");
        query.append("obs_datetime, location_id, obs_group_id, value_coded, value_datetime, value_numeric, value_text, creator, date_created, voided, voided_by,");
        query.append(" date_voided, variable_name, variable_value, datim_id, patient_uuid, encounter_uuid, visit_uuid, datatype)VALUES");
        
        
        if(obsList != null){
            for(int k=0; k<obsList.size(); k++)
             {
                 query.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),");
             }
        }
                        
        query.setLength(query.length() - 1);//remove the last comma
        
        query.append(" ON DUPLICATE KEY UPDATE  pmm_form=VALUES(pmm_form), encounter_type=VALUES(encounter_type), obs_datetime=VALUES(obs_datetime), location_id=VALUES(location_id), obs_group_id=VALUES(obs_group_id), ");
        query.append(" value_coded=VALUES(value_coded), value_datetime=VALUES(value_datetime), value_numeric=VALUES(value_numeric), value_text=VALUES(value_text), voided=VALUES(voided), voided_by=VALUES(voided_by),");
        query.append(" date_voided=VALUES(date_voided), variable_name=VALUES(variable_name), variable_value=VALUES(variable_name), datatype=VALUES(datatype)");
         String[] obsValues = null;
         ObsType currObs = null;
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                int i = 1;
                
              
                if(obsList != null){
                    for(int l=0; l<obsList.size(); l++)
                    {
                        currObs = obsList.get(l);
                        
                        stmt.setString(i++, currObs.getObsUuid());
                        stmt.setInt(i++,currObs.getObsId());
                        stmt.setInt(i++,currObs.getPersonId());
                        stmt.setInt(i++,currObs.getConceptId());
                        stmt.setInt(i++,currObs.getEncounterId());
                        stmt.setInt(i++,currObs.getFormId());
                        stmt.setString(i++,currObs.getPmmForm());
                        stmt.setInt(i++,currObs.getEncounterType());
                        stmt.setDate(i++, (currObs.getObsDatetime()!= null) ? new java.sql.Date(currObs.getObsDatetime().toGregorianCalendar().getTime().getTime()) : new java.sql.Date(new Date().getTime()));
                        stmt.setInt(i++, currObs.getLocationId());    
                        stmt.setInt(i++, currObs.getObsGroupId());    
                        stmt.setInt(i++, currObs.getValueCoded());    
                        stmt.setDate(i++, (currObs.getValueDatetime()!= null) ? new java.sql.Date(currObs.getValueDatetime().toGregorianCalendar().getTime().getTime()) : new java.sql.Date(new Date().getTime()));
                        stmt.setBigDecimal(i++, currObs.getValueNumeric());  
                        stmt.setString(i++, currObs.getValueText()); 
                        stmt.setLong(i++, currObs.getCreator());
                        stmt.setDate(i++, (currObs.getDateCreated() != null) ? new java.sql.Date(currObs.getDateCreated().toGregorianCalendar().getTime().getTime()) : null);
                        stmt.setInt(i++, currObs.getVoided());
                        stmt.setInt(i++, currObs.getVoidedBy());
                        stmt.setDate(i++, (currObs.getDateVoided() != null) ? new java.sql.Date(currObs.getDateVoided().toGregorianCalendar().getTime().getTime()) : null);
                        stmt.setString(i++, currObs.getVariableName());
                        stmt.setString(i++, currObs.getVariableValue());
                        stmt.setString(i++, datimId);
                        stmt.setString(i++, currObs.getPatientUuid());
                        stmt.setString(i++, currObs.getEncounterUuid());
                        stmt.setString(i++, currObs.getVisitUuid());
                        stmt.setInt(i++, currObs.getDatatype());
                        
                       
                    }
                    //firstEncounterId++;//we did a bulk insert for visit. for each iteration, increment the visit id to match with the right vist
                }
                                
                       
                if(obsList.size() > 0)
                    stmt.executeUpdate();
              
               
               
               
        }
        catch (SQLException ex) {
                System.out.println(query);
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
