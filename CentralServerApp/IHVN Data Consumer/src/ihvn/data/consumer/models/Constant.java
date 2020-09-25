package ihvn.data.consumer.models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lordmaul
 */
public class Constant {
    
    public static final String GENDER_MISSING = "gender_missing";
    public static final String INVALID_GENDER = "invalid_gender";
    public static final String BIRTHDATE_EMPTY = "birthdate_empty";
    public static final String BIRTHDATE_IN_FUTURE = "birthdate_in_future";
    public static final String AGE_ABOVE_200 = "age_above_200";
    public static final String NO_PROGRAM = "no_program";
    public static final String ENROLLMENT_DATE_IN_FUTURE = "enrollment_date_in_future";
    public static final String ENROLLMENT_DATE_MISSING = "enrollment_date_missing";
    public static final String MISSING_ENROLLMENT_METHOD = "missing_enrollment_method";
    public static final String NO_PEPFAR_ID = "no_pepfar_id";
    public static final String VISIT_START_DATE_IN_FUTURE = "visit_start_date_in_future";
    public static final String VISIT_START_DATE_MISSING = "visit_start_date_missing";
    public static final String VISIT_START_DATE_EARLIER_THAN_DOB = "visit_start_date_earlier_than_dob";
    public static final String VISIT_START_DATE_EARLIER_THAN_1990 = "visit_start_date_earlier_than_1990";
    public static final String VISIT_END_DATE_IN_FUTURE = "visit_end_date_in_future";
    public static final String VISIT_END_DATE_MISSING = "visit_end_date_missing";
    public static final String VISIT_END_DATE_EARLIER_THAN_DOB = "visit_end_date_earlier_than_dob";
    public static final String VISIT_END_DATE_EARLIER_THAN_1990 = "visit_end_date_earlier_1990";
    public static final String ENCOUNTER_DATE_IN_FUTURE = "encounter_date_in_future";
    public static final String ENCOUNTER_DATE_EMPTY = "encounter_date_empty";
    public static final String ENCOUNTER_DATE_EARLIER_THAN_DOB = "encounter_date_earlier_than_dob";
    public static final String DIAGNOSIS_DATE_MISSING = "diagnosis_date_missing";
    public static final String DIAGNOSIS_DATE_EARLIER_THAN_DOB = "diagnosis_date_earlier_than_dob";
    public static final String DIAGNOSIS_DATE_IN_FUTURE = "diagnosis_date_in_future";
    public static final String ENROLLMENT_METHOD_MISSING = "enrollment_method_missing";
    public static final String PRIOR_ART_MISSING = "prior_art_missing";
    public static final String TRANSFER_DATE_MISSING = "transfer_date_missing";
    public static final String FAC_TRANSFERRED_FROM_MISSING = "fac_tranferred_from_missing";
    public static final String TRANSFER_DATE_IN_FUTURE = "transfer_date_in_future";
    public static final String ART_START_DATE_MISSING = "art_start_date_missing";
    public static final String ART_START_DATE_IN_FUTURE = "art_start_date_in_future";
    public static final String ART_START_DATE_EARLIER_THAN_DOB = "art_start_date_earlier_than_dob";
    public static final String ART_START_DATE_EARLIER_THAN_ENROLLMENT_DATE = "art_start_date_earlier_than_enrollment_date";
    public static final String CURRENT_REGIMEN_LINE_MISSING = "current_regimen_line_missing";
    public static final String ADULT_PED_REGIMEN_LINE_MISSING = "adult_ped_regimen_line_missing";
    public static final String MEDICATION_DURATION_EMPTY = "medication_duration_empty";
    public static final String MEDICATION_DURATION_MORE_THAN_180_DAYS = "medication_duration_more_than_180_days";
    public static final String RETURN_VISIT_DATE_MISSING = "return_visit_date_missing";
    public static final String ORDER_DATE_MISSING = "order_date_missing";
    public static final String ORDER_DATE_IN_FUTURE = "order_date_in_future";
    public static final String REPORTED_DATE_MISSING = "reported_date_missing";
    public static final String REPORTED_DATE_IN_FUTURE = "reported_date_in_future";
    public static final String REPORTED_DATE_BEFORE_ORDER_DATE = "reported_date_before_order_date";
    public static final String WEIGHT_OUT_OF_RANGE = "weight_out_of_range";
    public static final String WEIGHT_TOO_SMALL_ADULT = "weight_too_small_adult";
    public static final String HEIGHT_OUT_OF_RANGE = "height_out_of_range";
    public static final String HEIGHT_TOO_SMALL_ADULT = "height_too_small_adult";
    public static final String WHO_STAGE_MISSING = "who_stage_missing";
    public static final String TB_STATUS_MISSING = "tb_status_missing";
    public static final String MALE_PATIENT_PREGNANT = "male_patient_pregnant";
    
    public static final String DOB = "dob";
    public static final String ENROLLMENT_DATE = "enrollment_date";
    public static final String VISITS ="visits";
    public static final String ENCOUNTERS = "encounters";
    public static final String HIV_ENROLLMENT_DATE = "hiv_enrollment_date";
    public static final String ART_START_DATE = "art_start_date";
    public static final String HIV_DIAGNOSIS_DATE = "hiv_diagnosis_date";
    
    public static final int HIV_ENROLLMENT_FORM = 14;
    public static final int LAB_ORDER_AND_RESULT_FORM = 11;
    public static final int PHARMACY_FORM = 13;
    public static final int CARE_CARD = 12;
    public static final int ART_COMMENCEMENT_FORM = 25;
    public static final int PED_INITIAL_EVAL_FORM = 24;
    public static final int ADULT_INITIAL_EVAL_FORM = 26;
    
    public static final int HIV_DIAGNOSIS_DATE_CONCEPT = 160554;
    public static final int ENROLLMENT_METHOD_CONCEPT = 160540;//this is the care entry point
    public static final int TRANSFER_IN_WITH_RECORDS = 160534;
    public static final int PRIOR_ART_CONCEPT = 165242;
    public static final int DATE_TRANSFERRED_CONCEPT = 160534;
    public static final int FAC_TRANSFERRED_FROM_CONCEPT = 160535;
    public static final int ART_START_DATE_CONCEPT = 159599;
    
    public static final int CURRENT_REGIMEN_LINE_CONCEPT = 165708;
    public static final int REGIMEN_FOR_DURATION_CONCEPT = 165724;
    public static final int REGIMEN_DURATION_CONCEPT = 159368;
    public static final int RETURN_VISIT_DATE_CONCEPT = 5096;
    public static final int DATE_ORDERED_CONCEPT = 164989;
    public static final int DATE_REPORTED_CONCEPT = 165414;
    public static final int WEIGHT_CONCEPT = 5089;
    public static final int HEIGHT_CONCEPT = 5090;
    public static final int WHO_CONCEPT = 5356;
    public static final int TB_STATUS_CONCEPT = 1659;
    public static final int ADULT_FIRST_LINE_REGIMEN_CONCEPT = 164506;
    public static final int ADULT_SECOND_LINE_REGIMEN_CONCEPT = 164513;
    
    public static final int PED_FIRST_LINE_REGIMEN_CONCEPT = 164507;
    public static final int PED_SECOND_LINE_REGIMEN_CONCEPT = 164514;
    public static final int PREGNANCY_STATUS_CONCEPT = 165050;
    public static final int VIRAL_LOAD_RESULT_CONCEPT = 856;
    public static final int VL_SAMPLE_COLLECTION_DATE = 159951;
    //public static final int VL_REPORT_DATE_CONCEPT = 165414;
    public static final int INDICATION_FOR_VL = 164980;
    public static final int DEATH_CONCEPT = 165889;
    public static final int TERMINATION_DATE = 165469;
    public static final int INH_DOSE_CONCEPT = 165080;
    
    public static final int TB_TREATMENT_CONCEPT = 1662;
    
    
    public static final int CHILD_AGE = 7;
    
    

}   
