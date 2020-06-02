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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rsuth
 */
public class VisitDAO {

    public List<VisitType> getAllVisits(int patientId) {
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

    public List<EncounterProviderType> getAllEncountersProvidersByPatient(int patientId) {
        String sql_text = "select \n"
                + "encounter_provider.*,\n"
                + "encounter.patient_id,\n"
                + "encounter.uuid as encounter_uuid\n"
                + "from \n"
                + "encounter_provider LEFT JOIN encounter on(encounter.encounter_id=encounter_provider.encounter_id)\n"
                + "where encounter.patient_id=";
        StringBuilder query = new StringBuilder(sql_text);
        query.append(patientId);
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        EncounterProviderType encounterProvider = null;
        List<EncounterProviderType> encounterProviderTypesList = new ArrayList<>();
        return encounterProviderTypesList;
    }

    public List<EncounterType> getAllEncountersByPatient(int patientId) {
        StringBuilder query = new StringBuilder("SELECT encounter.* FROM encounter WHERE patient_id=");
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

    public List<EncounterType> getAllEncountersByVisit(int visitId) {
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

    public List<ObsType> getAllObsByPatient(int patient_id) {
        StringBuilder query = new StringBuilder("SELECT obs.* FROM obs WHERE patient_id=");
        query.append(patient_id);
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

    public List<ObsType> getAllObsByEncounter(int encounterId) {
        StringBuilder query = new StringBuilder("SELECT obs.* FROM obs WHERE encounter_id=");
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
        obsType.setValueDatetime(Misc.getXMLdateTime(rs.getDate("obs_datetime")));
        obsType.setValueNumeric(BigDecimal.valueOf(rs.getDouble("value_numeric")));
        obsType.setValueText(rs.getString("value_text"));

        obsType.setCreator(rs.getInt("creator"));
        obsType.setDateCreated(Misc.getXMLdateTime(rs.getDate("date_created")));

        obsType.setVoided(rs.getInt("voided"));
        obsType.setVoidedBy(rs.getInt("voided_by"));
        //obsType.setVoidedByName("");
        if (rs.getString("date_voided") != null) {
            obsType.setDateVoided(Misc.getXMLdateTime(rs.getDate("date_voided")));
        }

        return obsType;

    }
}
