/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihvn.data.consumer.model.dao;

import ihvn.data.consumer.model.xml.DemographicsType;
import ihvn.data.consumer.models.Patient;
import ihvn.data.consumer.models.Radet;
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
public class PatientDAO {
    
    
    
    
    
    public static long getPatientIdWithUUID(String patientUUID)
    {
        String query = "SELECT patient_demographics.patient_id FROM patient_demographics where patient_uuid= ?" ; 

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        long patientId = 0;
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query);  
                stmt.setString(1,patientUUID);//1 specifies the first parameter in the query  
               
                rs = stmt.executeQuery();
                
                if (rs.next()) {
                        patientId = rs.getLong("patient_id");
                }

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
                catch (Exception ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        return patientId;
    }
    
    public static String insertOrUpdatePatient(DemographicsType patientDemo, String datimId, String messageUUID)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        
        StringBuilder query = new StringBuilder("INSERT INTO  demographics(patient_uuid, patient_id, first_name, last_name, middle_name, gender, birthdate, birthdate_estimated, dead, death_date, cause_of_death, creator, date_created, phone_number, address1, address2, city_village, state_province, country,");
        query.append(" changed_by, date_changed, voided_by, voided, date_voided,  voided_reason, deathdate_estimated, datim_id, message_uuid)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?)");
        query.append(" ON DUPLICATE KEY UPDATE patient_id=VALUES(patient_id), first_name=VALUES(first_name), last_name=VALUES(last_name), middle_name=VALUES(middle_name),  gender=VALUES(gender), birthdate=VALUES(birthdate), birthdate_estimated=VALUES(birthdate_estimated), dead=VALUES(dead), death_date=VALUES(death_date), ");
        query.append(" cause_of_death=VALUES(cause_of_death), creator=VALUES(creator), date_created=VALUES(date_created), phone_number=VALUES(phone_number), address1=VALUES(address1), address2=VALUES(address2), city_village=VALUES(city_village), ");
        query.append(" state_province=VALUES(state_province), country=VALUES(country), changed_by=VALUES(changed_by), date_changed=VALUES(date_changed), voided=VALUES(voided), voided_by=VALUES(voided_by), date_voided=VALUES(date_voided), voided_reason=VALUES(voided_reason), deathdate_estimated=VALUES(deathdate_estimated), message_uuid=VALUES(message_uuid)");
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                
                int i = 1;
                stmt.setString(i++, patientDemo.getPatientUuid());
                stmt.setLong(i++, patientDemo.getPatientId());
                stmt.setString(i++,patientDemo.getFirstName());
                stmt.setString(i++,patientDemo.getLastName());
                stmt.setString(i++,patientDemo.getMiddleName());
                stmt.setString(i++,patientDemo.getGender());
                stmt.setDate(i++, new java.sql.Date(patientDemo.getBirthdate().toGregorianCalendar().getTime().getTime()));
                stmt.setInt(i++,patientDemo.getBirthdateEstimated());
                stmt.setInt(i++,patientDemo.getDead());
                stmt.setDate(i++, (patientDemo.getDateChanged() != null) ? new java.sql.Date(patientDemo.getDeathDate().toGregorianCalendar().getTime().getTime()) : null);
                stmt.setString(i++, patientDemo.getCauseOfDeath());//cause of death
                stmt.setInt(i++,patientDemo.getCreator());
                stmt.setDate(i++, (patientDemo.getDateCreated() != null) ? new java.sql.Date(patientDemo.getDateCreated().toGregorianCalendar().getTime().getTime()) : null);
                stmt.setString(i++,patientDemo.getPhoneNumber());
                stmt.setString(i++,patientDemo.getAddress1());
                stmt.setString(i++,patientDemo.getAddress2());
                stmt.setString(i++,patientDemo.getCityVillage());
                stmt.setString(i++,patientDemo.getStateProvince());
                stmt.setString(i++,patientDemo.getCountry());
                stmt.setInt(i++,patientDemo.getChangedBy());
                stmt.setDate(i++, (patientDemo.getDateChanged() != null) ? new java.sql.Date(patientDemo.getDateChanged().toGregorianCalendar().getTime().getTime()) : null);
                stmt.setInt(i++, patientDemo.getVoidedBy());
                stmt.setInt(i++,patientDemo.getVoided());
                stmt.setDate(i++, (patientDemo.getDateVoided() != null) ? new java.sql.Date(patientDemo.getDateVoided().toGregorianCalendar().getTime().getTime()) : null);
                stmt.setString(i++, patientDemo.getVoidedReason());//voided reason
                stmt.setInt(i++, patientDemo.getDeathdateEstimated());
                stmt.setString(i++, datimId);
                stmt.setString(i++, messageUUID);
               
                stmt.executeUpdate();
                //stmt.setInt(22,facilityId);
                /*int affectedRows = stmt.executeUpdate();
              
               try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        if(patientId == 0)
                            patientId = (int)generatedKeys.getLong(1);
                       
                    }
                    
                }catch(Exception e)
                {
                    e.printStackTrace();
                }*/
               
               return patientDemo.getPatientUuid();
               
        }
        catch (SQLException ex) {
                //screen.updateStatus(ex.getMessage());
                ex.printStackTrace();
                return patientDemo.getPatientUuid();
        }
        finally {
                try {
                        if (rs != null && stmt != null) {
                                //rs.close();
                                stmt.close();
                                Database.connectionPool.free(con);
                               // con.close();
                        }

                }
                catch (Exception ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }
    
    
    public static void savePatient(List<Patient> allPatients)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        
        StringBuilder query = new StringBuilder("INSERT INTO  patient_dm(id, patient_uuid, patient_omrs_id, person_id, first_name, last_name, sex, date_of_birth, current_age_yrs, current_age_months,"
                + " unique_id, patient_unique_id, patient_hospital_id, transferin_id, address_country, address_state, address_lga, address_ward, address_town, address_other, "
                + "created_by, date_created, updated_by, enrollment_date, date_confirmed_positive, registration_phone, ");
        query.append(" contact_phone_no, mark_as_deceased, mark_as_deceased_date, art_start_date, age_at_art_start_yrs, age_at_art_start_months, first_visit_date, last_visit_date, biometric_captured, biometric_capture_date,"
                + " calendar_year, calendar_quarter, financial_year, financial_quarter, month, datim_id, current_art_status)VALUES");
        
            for(int i=0; i<allPatients.size(); i++)
            {
                query.append("(?, ?, ?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),");
            }
          
        query.setLength(query.length() - 1);//remove the last comma 
        query.append(" ON DUPLICATE KEY UPDATE first_name=VALUES(first_name), last_name=VALUES(last_name), sex=VALUES(sex), date_of_birth=VALUES(date_of_birth),  current_age_yrs=VALUES(current_age_yrs), current_age_months=VALUES(current_age_months), unique_id=VALUES(unique_id), patient_unique_id=VALUES(patient_unique_id), patient_hospital_id=VALUES(patient_hospital_id), ");
        query.append(" transferin_id=VALUES(transferin_id), address_country=VALUES(address_country), address_state=VALUES(address_state), address_town=VALUES(address_town), address_ward=VALUES(address_ward), address_lga=VALUES(address_lga), address_town=VALUES(address_town), ");
        query.append(" address_other=VALUES(address_other), created_by=VALUES(created_by), date_created=VALUES(date_created), updated_by=VALUES(updated_by), enrollment_date=VALUES(enrollment_date), date_confirmed_positive=VALUES(date_confirmed_positive), registration_phone=VALUES(registration_phone), contact_phone_no=VALUES(contact_phone_no), mark_as_deceased=VALUES(mark_as_deceased), mark_as_deceased_date=VALUES(mark_as_deceased_date), art_start_date=VALUES(art_start_date), age_at_art_start_yrs=VALUES(age_at_art_start_yrs), age_at_art_start_months=VALUES(age_at_art_start_months), first_visit_date=VALUES(first_visit_date), last_visit_date=VALUES(last_visit_date), biometric_captured=VALUES(biometric_captured), biometric_capture_date=VALUES(biometric_capture_date), calendar_year=VALUES(calendar_year), financial_year=VALUES(financial_year), financial_quarter=VALUES(financial_quarter), month=VALUES(month), datim_id=VALUES(datim_id), current_art_status=VALUES(current_art_status)");
        
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                
                int index = 1;
                for(int i=0; i<allPatients.size(); i++)
                {
                    stmt.setInt(index++, 0);
                    stmt.setString(index++, allPatients.get(i).getPatientUUID());
                    stmt.setString(index++, allPatients.get(i).getPatientOMRSID());
                    stmt.setInt(index++, allPatients.get(i).getPersonId());
                    stmt.setString(index++, allPatients.get(i).getFirstName());
                    stmt.setString(index++, allPatients.get(i).getLastName());
                    stmt.setString(index++, allPatients.get(i).getSex());
                    stmt.setString(index++, allPatients.get(i).getDob().toString("yyyy-MM-dd"));
                    stmt.setInt(index++, allPatients.get(i).getCurrAgeYears());
                    stmt.setInt(index++, allPatients.get(i).getCurrAgeMonths());
                    stmt.setString(index++, allPatients.get(i).getUniqueId());
                    stmt.setString(index++, allPatients.get(i).getPatientUniqueID());
                    stmt.setString(index++, allPatients.get(i).getHospitalID());
                    stmt.setString(index++, allPatients.get(i).getTransferredIn());
                    stmt.setString(index++, allPatients.get(i).getAddressCountry());
                    stmt.setString(index++, allPatients.get(i).getAddressState());
                    stmt.setString(index++, allPatients.get(i).getAddressLGA());
                    stmt.setString(index++, allPatients.get(i).getAddressWard());
                    stmt.setString(index++, allPatients.get(i).getAddressTown());
                    stmt.setString(index++, allPatients.get(i).getAddressOther());
                    
                 
                    stmt.setString(index++, allPatients.get(i).getCreatedBy());
                    stmt.setString(index++, allPatients.get(i).getDateCreated().toString("yyyy-MM-dd"));
                    stmt.setString(index++, allPatients.get(i).getUpdatedBy());
                    stmt.setString(index++, (allPatients.get(i).getEnrollmentDate() != null) ? allPatients.get(i).getEnrollmentDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allPatients.get(i).getDateConfirmedPositive() != null) ? allPatients.get(i).getDateConfirmedPositive().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, allPatients.get(i).getRegistrationPhone());
                    stmt.setString(index++, allPatients.get(i).getContactPhoneNo());
                    stmt.setInt(index++, allPatients.get(i).getMarkedAsDeceased());
                    stmt.setString(index++, (allPatients.get(i).getDateDeceased() != null) ? allPatients.get(i).getDateDeceased().toString("yyyy-MM-dd"): null);
                    stmt.setString(index++, (allPatients.get(i).getArtStartDate() != null) ? allPatients.get(i).getArtStartDate().toString("yyyy-MM-dd") : null);
                    stmt.setInt(index++, allPatients.get(i).getAgeAtArtStartYears());
                    stmt.setInt(index++, allPatients.get(i).getAgeAtArtStartMonths());
                    stmt.setString(index++,(allPatients.get(i).getFirstVisitDate() != null) ? allPatients.get(i).getFirstVisitDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allPatients.get(i).getLastVisitDate() != null) ? allPatients.get(i).getLastVisitDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, allPatients.get(i).getBiometricCaptured());
                    stmt.setString(index++, (allPatients.get(i).getBiometricCaptureDate() != null) ? allPatients.get(i).getBiometricCaptureDate().toString("yyyy-MM-dd") : null);
                    stmt.setInt(index++, allPatients.get(i).getCalendarYear());
                    stmt.setInt(index++, allPatients.get(i).getCalendarQuarter());
                    stmt.setInt(index++, allPatients.get(i).getFinancialYear());
                    stmt.setInt(index++, allPatients.get(i).getFinancialQuarter());
                    
                    
                    stmt.setInt(index++, allPatients.get(i).getMonth());
                    stmt.setString(index++, allPatients.get(i).getDatimId());
                    stmt.setString(index++, allPatients.get(i).getCurrentARTStatus());
                    
                    
                }
                
               
               if(allPatients.size() > 0)
                 stmt.executeUpdate();
                
              
               
        }
        catch (SQLException ex) {
                //screen.updateStatus(ex.getMessage());
                ex.printStackTrace();
               
        }
        finally {
                try {
                        if (rs != null && stmt != null) {
                                //rs.close();
                                stmt.close();
                                Database.connectionPool.free(con);
                               // con.close();
                        }

                }
                catch (Exception ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }
    
    
    
    public static void saveRadet(List<Radet> allRadet)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        
        StringBuilder query = new StringBuilder("INSERT INTO  radet_fact(radet_id, financial_year, financial_quarter, calendar_year, calendar_quarter, month, datim_id, patient_uuid, transfer_in_status, transfer_in_date, care_entry_point, last_pickup_date, days_of_arv_refil, initial_regimen_line, initial_first_line_regimen, initial_first_line_regimen_date, initial_second_line_regimen, initial_second_line_regimen_date, current_regimen_line, current_first_line_regimen, current_first_line_regimen_date, current_second_line_regimen, current_second_line_regimen_date, ");
        query.append(" pregnancy_status, current_viral_load, viral_load_sample_collection_date, viral_load_reported_date, viral_load_indication, current_art_status, transfer_out_date, death_date, current_weight_kg, current_weight_date, tb_status, tb_status_date, inh_start_date, inh_stop_date, last_inh_dispensed_date, tb_treatment_start_date, tb_treatment_stop_date, last_viral_load_sample_collection_form_date, otz_start_date, otz_stop_date, art_start_date)VALUES");
        
            for(int i=0; i<allRadet.size(); i++)
            {
                query.append("(?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?),");
            }
          
        query.setLength(query.length() - 1);//remove the last comma 
        query.append(" ON DUPLICATE KEY UPDATE financial_year=VALUES(financial_year), financial_quarter=VALUES(financial_quarter), calendar_year=VALUES(calendar_year), calendar_quarter=VALUES(calendar_quarter),  month=VALUES(month), datim_id=VALUES(datim_id), patient_uuid=VALUES(patient_uuid), transfer_in_status=VALUES(transfer_in_status), transfer_in_date=VALUES(transfer_in_date), art_start_date=VALUES(art_start_date),");
        query.append(" care_entry_point=VALUES(care_entry_point), last_pickup_date=VALUES(last_pickup_date), days_of_arv_refil=VALUES(days_of_arv_refil), initial_regimen_line=VALUES(initial_regimen_line), initial_first_line_regimen=VALUES(initial_first_line_regimen), initial_first_line_regimen_date=VALUES(initial_first_line_regimen_date), initial_second_line_regimen=VALUES(initial_second_line_regimen_date), current_regimen_line=VALUES(current_regimen_line), ");
        query.append(" current_first_line_regimen=VALUES(current_first_line_regimen), current_first_line_regimen_date=VALUES(current_first_line_regimen_date), current_second_line_regimen=VALUES(current_second_line_regimen), current_second_line_regimen_date=VALUES(current_second_line_regimen_date), pregnancy_status=VALUES(pregnancy_status), current_viral_load=VALUES(current_viral_load), viral_load_sample_collection_date=VALUES(viral_load_sample_collection_date), viral_load_indication=VALUES(viral_load_indication), current_art_status=VALUES(current_art_status), transfer_out_date=VALUES(transfer_out_date), death_date=VALUES(death_date), current_weight_kg=VALUES(current_weight_kg), current_weight_date=VALUES(current_weight_date),  tb_status=VALUES(tb_status), tb_status_date=VALUES(tb_status_date), inh_start_date=VALUES(inh_start_date), inh_stop_date=VALUES(inh_stop_date), tb_treatment_start_date=VALUES(tb_treatment_start_date), tb_treatment_stop_date=VALUES(tb_treatment_stop_date), last_viral_load_sample_collection_form_date=VALUES(last_viral_load_sample_collection_form_date), otz_start_date=VALUES(otz_start_date), otz_stop_date=VALUES(otz_stop_date)");
        
        try {
                con = Database.connectionPool.getConnection();
                stmt= con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);  
                
                int index = 1;
                for(int i=0; i<allRadet.size(); i++)
                {
                    stmt.setInt(index++, 0);
                    stmt.setInt(index++, allRadet.get(i).getFinYear());
                    stmt.setInt(index++, allRadet.get(i).getFinQuarter());
                    stmt.setInt(index++, allRadet.get(i).getCalendarYear());
                    stmt.setInt(index++, allRadet.get(i).getCalendarQuarter());
                    stmt.setInt(index++, allRadet.get(i).getMonth());
                    stmt.setString(index++, allRadet.get(i).getDatimId());
                    stmt.setString(index++, allRadet.get(i).getPatientUUID());
                    stmt.setString(index++, allRadet.get(i).getTransferInStatus());
                    stmt.setString(index++, (allRadet.get(i).getTransferInDate() != null) ? allRadet.get(i).getTransferInDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++,  allRadet.get(i).getCareEntryPoint());
                    stmt.setString(index++, (allRadet.get(i).getLastPickupDate() != null) ? allRadet.get(i).getLastPickupDate().toString("yyyy-MM-dd") : null);
                    stmt.setInt(index++, allRadet.get(i).getDaysOfARVRefill());
                    stmt.setString(index++, allRadet.get(i).getInitialRegimenLine());
                    stmt.setString(index++, allRadet.get(i).getInitialFirstLineRegimen());
                    stmt.setString(index++, (allRadet.get(i).getInitialFirstLineRegimenDate() != null) ?  allRadet.get(i).getInitialFirstLineRegimenDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, allRadet.get(i).getInitialSecondLineRegimen());
                    stmt.setString(index++, (allRadet.get(i).getInitialSecondLineRegimenDate() != null) ? allRadet.get(i).getInitialSecondLineRegimenDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, allRadet.get(i).getCurrentRegimenLine());
                    stmt.setString(index++, allRadet.get(i).getCurrentFirstLineRegimen());
                    
                    
                    
                    stmt.setString(index++, (allRadet.get(i).getCurrentFirstLineRegimenDate() != null) ? allRadet.get(i).getCurrentFirstLineRegimenDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, allRadet.get(i).getCurrentSecondLineRegimen());
                    stmt.setString(index++, (allRadet.get(i).getCurrentSecondLineRegimenDate() != null) ? allRadet.get(i).getCurrentSecondLineRegimenDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, allRadet.get(i).getPregnancyStatus());
                    
                    
                    if(allRadet.get(i).getCurrentViralLoad() > -1) 
                        stmt.setDouble(index++, allRadet.get(i).getCurrentViralLoad() ); 
                    else
                        stmt.setNull(index++, java.sql.Types.DOUBLE);
                    
                    stmt.setString(index++, (allRadet.get(i).getViralLoadSampleCollectionDate() != null) ? allRadet.get(i).getViralLoadSampleCollectionDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getViralLoadReportedDate() != null) ?allRadet.get(i).getViralLoadReportedDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, allRadet.get(i).getViralLoadIndication());
                    stmt.setString(index++, allRadet.get(i).getCurrentArtStatus());
                    stmt.setString(index++, (allRadet.get(i).getTransferOutDate() != null) ?allRadet.get(i).getTransferOutDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getDeathDate() != null) ?allRadet.get(i).getDeathDate().toString("yyyy-MM-dd") : null);
                    stmt.setDouble(index++, allRadet.get(i).getCurrentWeight());
                    stmt.setString(index++, (allRadet.get(i).getCurrentWeightDate()!= null) ?allRadet.get(i).getCurrentWeightDate().toString("yyyy-MM-dd") : null);
                    
                    stmt.setString(index++, allRadet.get(i).getTbStatus());
                    stmt.setString(index++, (allRadet.get(i).getTbStatusDate() != null) ?allRadet.get(i).getTbStatusDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getInhStartDate() != null) ?allRadet.get(i).getInhStartDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getInhStopDate() != null) ?allRadet.get(i).getInhStopDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getLastInhDispensedDate() != null) ?allRadet.get(i).getLastInhDispensedDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getTbTreatmentStartDate() != null) ?allRadet.get(i).getTbTreatmentStartDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getTbTreatmentStopDate() != null) ?allRadet.get(i).getTbTreatmentStopDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getLastvLSampleCollectionFormDate() != null) ?allRadet.get(i).getLastvLSampleCollectionFormDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getOtzStartDate() != null) ?allRadet.get(i).getOtzStartDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getOtzStopDate() != null) ?allRadet.get(i).getOtzStopDate().toString("yyyy-MM-dd") : null);
                    stmt.setString(index++, (allRadet.get(i).getArtStartDate()!= null) ? allRadet.get(i).getArtStartDate().toString("yyyy-MM-dd") : null);
                }
                
                
               if(allRadet.size() > 0)
                 stmt.executeUpdate();
                
              
               
        }
        catch (SQLException ex) {
                //screen.updateStatus(ex.getMessage());
                ex.printStackTrace();
               
        }
        finally {
                try {
                        if (rs != null && stmt != null) {
                                //rs.close();
                                stmt.close();
                                Database.connectionPool.free(con);
                               // con.close();
                        }

                }
                catch (Exception ex) {
                        ex.printStackTrace();
                        //Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    }
}
