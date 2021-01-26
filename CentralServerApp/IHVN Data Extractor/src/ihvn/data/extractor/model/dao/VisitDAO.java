/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.extractor.model.dao;

import ihvn.data.extractor.model.xml.EncounterProviderType;
import ihvn.data.extractor.model.xml.EncounterType;
import ihvn.data.extractor.model.xml.ObsType;
import ihvn.data.extractor.model.xml.VisitType;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rsuth
 */
public class VisitDAO extends MasterDAO {
    
    private Integer[] confidentialConcepts={159635};//Phone no(159635)
    private List<Integer> confidentialConceptsList=new ArrayList<Integer>(Arrays.asList(confidentialConcepts));
    
    public List<VisitType> getAllVisits(int patientId, String patient_uuid, String datim_id) {
        StringBuilder query = new StringBuilder("SELECT visit.* FROM visit WHERE patient_id=");
        query.append(patientId);
        // String query = "SELECT visit.* FROM visit WHERE patient_id="+patientId;  

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<VisitType> allVisits = new ArrayList<>();
        //System.out.println("Connection list "+Database.connectionPool.totalConnections());
        try {
            con = Database.connectionPool.getConnection();
            stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);

            stmt.setFetchSize(Integer.MIN_VALUE);
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                VisitType visit = buildVisit(rs);
                visit.setPatientUuid(patient_uuid);
                visit.setDatimId(datim_id);
                allVisits.add(visit);

            }
            //rs.close();
            //stmt.close();
            return allVisits;
        } catch (SQLException ex) {
            //screen.updateStatus(ex.getMessage());
            ex.printStackTrace();
            return null;

        } finally {
            cleanUp(rs, stmt, con);
        }
    }

    public List<EncounterProviderType> getAllEncountersProvidersByPatient(int patientId, String patient_uuid, String datim_id) {
        String sql_text = "select \n"
                + "encounter_provider.*,\n"
                + "encounter.patient_id,\n"
                + "encounter.uuid as encounter_uuid,\n"
                + "encounter.location_id as location_id,\n"
                + "visit.uuid as visit_uuid\n"
                + "from \n"
                + "encounter_provider \n"
                + "LEFT JOIN encounter on(encounter.encounter_id=encounter_provider.encounter_id)\n"
                + "LEFT JOIN visit on(visit.visit_id=encounter.visit_id)\n"
                + "where encounter.patient_id=";
        StringBuilder query = new StringBuilder(sql_text);
        query.append(patientId);
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        EncounterProviderType encounterProvider = null;
        List<EncounterProviderType> encounterProviderTypesList = new ArrayList<>();
        try {
            con = Database.connectionPool.getConnection();
            stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);

            stmt.setFetchSize(Integer.MIN_VALUE);
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                encounterProvider = buildEncounterProvider(rs);
                encounterProvider.setPatientUuid(patient_uuid);
                encounterProvider.setDatimId(datim_id);
                encounterProviderTypesList.add(encounterProvider);

            }
            //rs.close();
            //stmt.close();
            return encounterProviderTypesList;
        } catch (SQLException ex) {
            //screen.updateStatus(ex.getMessage());
            ex.printStackTrace();
            return null;

        } finally {
            cleanUp(rs, stmt, con);
        }

    }

    public void handleException(Exception ex) {
        ex.printStackTrace();
    }

    public void cleanUp(ResultSet rs, Statement stmt, Connection con) {
        try {
            rs.close();
            stmt.close();
            Database.connectionPool.free(con);
        } catch (Exception ex) {
            handleException(ex);
        }
    }

    public List<EncounterType> getAllEncountersByPatient(int patientId, String patient_uuid, String datim_id) {
        StringBuilder query = new StringBuilder("SELECT encounter.*,form.name as pmm_form,visit.uuid as visit_uuid  FROM encounter "
                + "LEFT JOIN form on(form.form_id=encounter.form_id) "
                + "LEFT JOIN visit on(visit.visit_id=encounter.visit_id) "
                + "WHERE encounter.patient_id=");
        query.append(patientId);

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        EncounterType encounter = null;
        List<EncounterType> allEncounters = new ArrayList<>();
        //System.out.println("Connection list "+Database.connectionPool.totalConnections());
        try {
            con = Database.connectionPool.getConnection();
            stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);

            stmt.setFetchSize(Integer.MIN_VALUE);
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                encounter = buildEncounter(rs);
                encounter.setPatientUuid(patient_uuid);
                encounter.setDatimId(datim_id);
                allEncounters.add(encounter);

            }
            //rs.close();
            //stmt.close();
            return allEncounters;
        } catch (SQLException ex) {
            //screen.updateStatus(ex.getMessage());
            ex.printStackTrace();
            return null;

        } finally {
            cleanUp(rs, stmt, con);
        }
    }

    public List<EncounterType> getAllEncountersByVisit(int visitId, String patient_uuid, String datim_id) {
        StringBuilder query = new StringBuilder("SELECT encounter.* FROM encounter WHERE visit_id=");
        query.append(visitId);

        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        EncounterType encounter = null;
        List<EncounterType> allEncounters = new ArrayList<>();
        //System.out.println("Connection list "+Database.connectionPool.totalConnections());
        try {
            con = Database.connectionPool.getConnection();
            stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);

            stmt.setFetchSize(Integer.MIN_VALUE);
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                encounter = buildEncounter(rs);
                encounter.setPatientUuid(patient_uuid);
                encounter.setDatimId(datim_id);
                allEncounters.add(encounter);

            }
            //rs.close();
            //stmt.close();
            return allEncounters;
        } catch (SQLException ex) {
            //screen.updateStatus(ex.getMessage());
            ex.printStackTrace();
            return null;

        } finally {
            cleanUp(rs, stmt, con);
        }
    }

    public List<ObsType> getAllObsByPatient(int patient_id, String patient_uuid, String datim_id) {
        String sql_text = "select\n"
                + "obs.*,\n"
                + "encounter.uuid as encounter_uuid,\n"
                + "encounter.location_id,\n"
                + "encounter.form_id,\n"
                + "encounter.encounter_type,\n"
                + "form.name as pmm_form,\n"
                + "visit.uuid as visit_uuid,\n"
                + "cn1.name as variable_name,\n"
                + "COALESCE(value_datetime,value_text,value_numeric,cn2.name) as variable_value,\n"
                + "concept.datatype_id\n"
                + "from obs \n"
                + "LEFT JOIN encounter on(encounter.encounter_id=obs.encounter_id)\n"
                + "LEFT JOIN visit on(visit.visit_id=encounter.visit_id)\n"
                + "LEFT JOIN form on(encounter.form_id=form.form_id)\n"
                + "LEFT JOIN concept_name cn1 on(cn1.concept_id=obs.concept_id and cn1.locale_preferred=1 and cn1.locale='en' and cn1.voided=0)\n"
                + "LEFT JOIN concept_name cn2 on(cn2.concept_id=obs.value_coded and cn2.locale_preferred=1 and cn2.locale='en' and cn2.voided=0)\n"
                + "LEFT JOIN concept on(obs.concept_id=concept.concept_id)\n"
                + "where obs.person_id=";
        StringBuilder query = new StringBuilder(sql_text);
        query.append(patient_id);
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<ObsType> allObs = new ArrayList<>();
        ObsType obs = null;
        //System.out.println("Connection list "+Database.connectionPool.totalConnections());
        try {
            con = Database.connectionPool.getConnection();
            stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);
            stmt.setFetchSize(Integer.MIN_VALUE);
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                obs = buildObs(rs);
                
                obs.setDatimId(datim_id);
                obs.setPatientUuid(patient_uuid);
                allObs.add(obs);
            }
            //rs.close();
            //stmt.close();
            return allObs;
        } catch (SQLException ex) {
            //screen.updateStatus(ex.getMessage());
            ex.printStackTrace();
            return null;

        } finally {
            cleanUp(rs, stmt, con);
        }
    }

    public List<ObsType> getAllObsByEncounter(int encounterId, String patient_uuid, String datim_id) {
        StringBuilder query = new StringBuilder("SELECT obs.*,encounter.uuid as encounter_uuid FROM obs INNER JOIN encounter on(encounter.encounter_id=obs.encounter_id) WHERE encounter_id=");
        query.append(encounterId);
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<ObsType> allObs = new ArrayList<>();
        //System.out.println("Connection list "+Database.connectionPool.totalConnections());
        try {
            con = Database.connectionPool.getConnection();
            stmt = con.createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY, java.sql.ResultSet.CONCUR_READ_ONLY);

            stmt.setFetchSize(Integer.MIN_VALUE);
            rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                ObsType obs = buildObs(rs);

                allObs.add(obs);

            }
            //rs.close();
            //stmt.close();
            return allObs;
        } catch (SQLException ex) {
            //screen.updateStatus(ex.getMessage());
            ex.printStackTrace();
            return null;

        } finally {
            cleanUp(rs, stmt, con);
        }
    }

    private VisitType buildVisit(ResultSet rs) throws SQLException {
        VisitType visit = new VisitType();

        visit.setVisitId(rs.getInt("visit_id"));
        visit.setPatientId(rs.getInt("patient_id"));
        visit.setVisitTypeId(rs.getInt("visit_type_id"));
        visit.setDateStarted(Misc.getXMLdateTime(rs.getDate("date_started")));
        visit.setDateStopped(Misc.getXMLdateTime(rs.getDate("date_stopped")));
      
        visit.setCreator(rs.getInt("creator"));
        visit.setDateCreated(Misc.getXMLdateTime(rs.getDate("date_created")));
        visit.setChangedBy(rs.getInt("changed_by"));
        if (rs.getString("date_changed") != null) {
            visit.setDateChanged(Misc.getXMLdateTime(rs.getDate("date_changed")));
        }
        visit.setVoided(rs.getInt("voided"));
        visit.setVoidedBy(rs.getInt("voided_by"));
        //visit.setVoidedByName("");
        if (rs.getString("date_voided") != null) {
            visit.setDateVoided(Misc.getXMLdateTime(rs.getDate("date_voided")));
        }

        visit.setVisitUuid(rs.getString("uuid"));
        visit.setLocationId(rs.getInt("location_id"));

        //visit.getEncounter().addAll(this.getAllEncounters(visit.getVisitId()));
        return visit;

    }

    private EncounterType buildEncounter(ResultSet rs) throws SQLException {
        EncounterType encounterType = new EncounterType();

        encounterType.setVisitId(rs.getInt("visit_id"));
        encounterType.setEncounterUuid(rs.getString("uuid"));
        encounterType.setEncounterId(rs.getInt("encounter_id"));
        encounterType.setEncounterTypeId(rs.getInt("encounter_type"));
        encounterType.setPatientId(rs.getInt("patient_id"));
        encounterType.setFormId(rs.getInt("form_id"));
        encounterType.setLocationId(rs.getInt("location_id"));
        encounterType.setPmmForm(rs.getString("pmm_form"));
        encounterType.setVisitUuid(rs.getString("visit_uuid"));
        encounterType.setEncounterDatetime(Misc.getXMLdateTime(rs.getDate("encounter_datetime")));

        encounterType.setCreator(rs.getInt("creator"));
        encounterType.setDateCreated(Misc.getXMLdateTime(rs.getDate("date_created")));
        encounterType.setChangedBy(rs.getInt("changed_by"));
        if (rs.getString("date_changed") != null) {
            encounterType.setDateChanged(Misc.getXMLdateTime(rs.getDate("date_changed")));
        }
        encounterType.setVoided(rs.getInt("voided"));
        encounterType.setVoidedBy(rs.getInt("voided_by"));
        //encounterType.setVoidedByName("");
        if (rs.getString("date_voided") != null) {
            encounterType.setDateVoided(Misc.getXMLdateTime(rs.getDate("date_voided")));
        }

        //encounterType.getObs().addAll(this.getAllObs(encounterType.getEncounterId()));
        return encounterType;

    }

    private EncounterProviderType buildEncounterProvider(ResultSet rs) throws SQLException {
        EncounterProviderType encounterProvider = new EncounterProviderType();
        encounterProvider.setEncounterProviderId(rs.getInt("encounter_provider_id"));
        encounterProvider.setEncounterId(rs.getInt("encounter_id"));
        encounterProvider.setProviderId(rs.getInt("provider_id"));
        encounterProvider.setEncounterRoleId(rs.getInt("encounter_role_id"));
        encounterProvider.setCreator(rs.getInt("creator"));
        encounterProvider.setDateCreated(Misc.getXMLdateTime(rs.getDate("date_created")));
        encounterProvider.setChangedBy(rs.getInt("changed_by"));
        encounterProvider.setDateChanged(Misc.getXMLdateTime(rs.getDate("date_changed")));
        encounterProvider.setVoided(rs.getInt("voided"));
        encounterProvider.setDateVoided(Misc.getXMLdateTime(rs.getDate("date_voided")));
        encounterProvider.setVoidedBy(rs.getInt("voided_by"));
        encounterProvider.setVoidedReason(rs.getString("void_reason"));
        encounterProvider.setEncounterProviderUuid(rs.getString("uuid"));
        encounterProvider.setEncounterUuid(rs.getString("encounter_uuid"));
        encounterProvider.setVisitUuid(rs.getString("visit_uuid"));
        encounterProvider.setLocationId(rs.getInt("location_id"));
        return encounterProvider;
    }

    private ObsType buildObs(ResultSet rs) throws SQLException {
        ObsType obsType = new ObsType();

        obsType.setObsUuid(rs.getString("uuid"));
        obsType.setObsId(rs.getInt("obs_id"));
        obsType.setPersonId(rs.getInt("person_id"));
        obsType.setConceptId(rs.getInt("concept_id"));
        obsType.setEncounterId(rs.getInt("encounter_id"));
        // obsType.setEncounterType(rs.getInt("encounter_type"));
        obsType.setObsDatetime(Misc.getXMLdateTime(rs.getDate("obs_datetime")));
        obsType.setObsGroupId(rs.getInt("obs_group_id"));
        obsType.setValueCoded(rs.getInt("value_coded"));
        obsType.setValueDatetime(Misc.getXMLdateTime(rs.getDate("value_datetime")));
        obsType.setValueNumeric(BigDecimal.valueOf(rs.getDouble("value_numeric")));
        if(confidentialConceptsList.contains(obsType.getConceptId())){
            obsType.setValueText(Misc.encrypt(rs.getString("value_text")));
        }else{
            obsType.setValueText(rs.getString("value_text"));
        }
        
        obsType.setEncounterUuid(rs.getString("encounter_uuid"));
        obsType.setCreator(rs.getInt("creator"));
        obsType.setDateCreated(Misc.getXMLdateTime(rs.getDate("date_created")));
        obsType.setPmmForm(rs.getString("pmm_form"));
        obsType.setFormId(rs.getInt("form_id"));
        obsType.setVariableName(rs.getString("variable_name"));
        if(confidentialConceptsList.contains(obsType.getConceptId())){
            obsType.setVariableValue(Misc.encrypt(rs.getString("variable_value")));
        }else{
            obsType.setVariableValue(rs.getString("variable_value"));
        }
        obsType.setDatatype(rs.getInt("datatype_id"));
        obsType.setEncounterType(rs.getInt("encounter_type"));
        obsType.setVisitUuid(rs.getString("visit_uuid"));
        obsType.setLocationId(rs.getInt("location_id"));
        obsType.setVoided(rs.getInt("voided"));
        obsType.setVoidedBy(rs.getInt("voided_by"));

        //obsType.setVoidedByName("");
        if (rs.getString("date_voided") != null) {
            obsType.setDateVoided(Misc.getXMLdateTime(rs.getDate("date_voided")));
        }

        return obsType;

    }
    public Date getVisitTimestamp(int patientID) {
        Date lastModifiedDate = null;
        String sql_text = "select MAX(GREATEST(visit.date_created,COALESCE(visit.date_changed,0),COALESCE(visit.date_voided,0))) as most_recent from visit where patient_id=?";
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
            rs = ps.executeQuery();
            while (rs.next()) {
                lastModifiedDate = rs.getDate("most_recent");
            }
            cleanUp(rs, ps, con);
        } catch (SQLException ex) {
            handleException(ex);
        } finally {
            cleanUp(rs, stmt, con);
        }
        return lastModifiedDate;
    }
    
    public Date getEncounterTimestamp(int patientID) {
        Date lastModifiedDate = null;
        String sql_text = "select MAX(GREATEST(encounter.date_created,COALESCE(encounter.date_changed,0),COALESCE(encounter.date_voided,0))) as most_recent from encounter where patient_id=?";
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
    
    public Date getObsTimestamp(int patientID) {
        Date lastModifiedDate = null;
        String sql_text = "select MAX(GREATEST(obs.date_created,COALESCE(obs.date_voided,0))) as most_recent from obs where person_id=?";
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
