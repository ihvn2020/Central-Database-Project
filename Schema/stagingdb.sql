
CREATE TABLE obs (
                obs_uuid VARCHAR(255) NOT NULL,
                obs_id INT,
                person_id INT,
                concept_id INT,
                encounter_id INT,
                form_id INT,
                pmm_form VARCHAR(255),
                encounter_type INT,
                obs_datetime DATETIME,
                location_id INT,
                obs_group_id INT,
                value_coded INT,
                value_datetime DATETIME,
                value_numeric DECIMAL(19,4),
                value_text VARCHAR(255),
                creator INT,
                date_created DATETIME,
                voided INT,
                voided_by INT,
                date_voided DATETIME,
                variable_name VARCHAR(255),
                variable_value VARCHAR(255),
                datim_id VARCHAR(255),
                patient_uuid VARCHAR(255),
                encounter_uuid VARCHAR(255),
                visit_uuid VARCHAR(255),
                datatype INT,
                PRIMARY KEY (obs_uuid)
);


CREATE TABLE validation_error (
                error_id INT NOT NULL,
                patient_uuid VARCHAR(255),
                datim_id VARCHAR(60),
                visit_uuid VARCHAR(255),
                encounter_uuid VARCHAR(255),
                obs_uuid VARCHAR(255),
                error_code VARCHAR(255),
                error_message VARCHAR(255),
                pmm_form VARCHAR(255),
                variable_name VARCHAR(255),
                variable_value VARCHAR(255),
                visit_date DATETIME,
                ignore_patient INT DEFAULT 0 NOT NULL,
                ignore_form INT DEFAULT 0 NOT NULL,
                ignore_variable INT DEFAULT 0 NOT NULL,
                message_uuid VARCHAR(255),
                PRIMARY KEY (error_id)
);


CREATE TABLE visit (
                visit_uuid VARCHAR(255) NOT NULL,
                visit_id INT,
                patient_id INT,
                location_id INT,
                visit_type_id INT,
                date_started DATETIME,
                date_stopped DATETIME,
                creator INT,
                date_created DATETIME,
                changed_by INT,
                date_changed DATETIME,
                voided INT,
                voided_by INT,
                date_voided DATETIME,
                patient_uuid VARCHAR(255),
                datim_id VARCHAR(255),
                message_uuid VARCHAR(255),
                PRIMARY KEY (visit_uuid)
);


CREATE TABLE patient_program (
                patient_program_uuid VARCHAR(255) NOT NULL,
                patient_program_id INT,
                patient_id INT,
                program_id INT,
                location_id INT,
                program_name VARCHAR(255),
                date_enrolled DATETIME,
                date_completed DATETIME,
                outcome_concept_id INT,
                creator INT,
                date_created DATETIME,
                changed_by INT,
                date_changed DATETIME,
                voided INT,
                voided_by INT,
                date_voided DATETIME,
                patient_uuid VARCHAR(255),
                datim_id VARCHAR(255),
                message_uuid VARCHAR(255),
                PRIMARY KEY (patient_program_uuid)
);


CREATE TABLE patient_identifier (
                patient_identifier_uuid VARCHAR(255) NOT NULL,
                patient_identifier_id INT,
                patient_id INT,
                location_id INT,
                identifier VARCHAR(255),
                identifier_type INT,
                preferred INT,
                creator INT,
                date_created DATETIME,
                date_changed DATETIME,
                changed_by INT,
                voided INT,
                voided_by INT,
                date_voided DATETIME,
                patient_uuid VARCHAR(255),
                datim_id VARCHAR(255),
                message_uuid VARCHAR(255),
                PRIMARY KEY (patient_identifier_uuid)
);


CREATE TABLE message (
                message_unique_id VARCHAR(60) NOT NULL,
                message_status VARCHAR(60),
                message_schema_version VARCHAR(60),
                message_source VARCHAR(60),
                message_creation_datetime DATE,
                facility_datim_id VARCHAR(60),
                facility_name VARCHAR(80),
                file_name VARCHAR(80),
                upload_date DATE,
                processed_date DATE,
                touch_time DATETIME NOT NULL,
                PRIMARY KEY (message_unique_id)
);


CREATE TABLE patient_biometric (
                biometric_info_id INT NOT NULL,
                datim_id VARCHAR(255) NOT NULL,
                patient_id INT,
                template VARCHAR(255),
                image_height INT,
                image_width INT,
                image_dpi INT,
                image_quality INT,
                finger_position VARCHAR(255),
                serial_number VARCHAR(255),
                model VARCHAR(255),
                manufacturer VARCHAR(255),
                creator INT,
                date_created DATETIME,
                patient_uuid VARCHAR(255),
                message_uuid VARCHAR(255),
                PRIMARY KEY (biometric_info_id, datim_id)
);


CREATE TABLE encounter_provider (
                encounter_provider_uuid VARCHAR(255) NOT NULL,
                encounter_provider_id INT,
                encounter_id INT,
                provider_id INT,
                location_id INT,
                encounter_role_id INT,
                creator INT,
                date_created DATETIME,
                changed_by INT,
                date_changed DATETIME,
                voided INT,
                date_voided DATETIME,
                voided_by INT,
                voided_reason VARCHAR(255),
                datim_id VARCHAR(255),
                patient_uuid VARCHAR(255),
                encounter_uuid VARCHAR(255),
                visit_uuid VARCHAR(255),
                message_uuid VARCHAR(255),
                PRIMARY KEY (encounter_provider_uuid)
);


CREATE TABLE encounter (
                encounter_uuid VARCHAR(255) NOT NULL,
                encounter_id INT,
                encounter_type_id INT,
                patient_id INT,
                location_id INT,
                form_id INT,
                pmm_form VARCHAR(255),
                encounter_datetime DATETIME,
                creator INT,
                date_created DATETIME,
                voided INT,
                voided_by INT,
                date_voided DATETIME,
                changed_by INT,
                date_changed DATETIME,
                visit_id INT,
                patient_uuid VARCHAR(255),
                datim_id VARCHAR(255),
                visit_uuid VARCHAR(255),
                message_uuid VARCHAR(255),
                PRIMARY KEY (encounter_uuid)
);


CREATE TABLE demographics (
                patient_uuid VARCHAR(255) NOT NULL,
                patient_id INT,
                first_name VARCHAR(255),
                last_name VARCHAR(255),
                middle_name VARCHAR(255),
                gender VARCHAR(255),
                birthdate DATETIME,
                birthdate_estimated INT,
                dead INT,
                death_date DATETIME,
                cause_of_death VARCHAR(255),
                creator INT,
                date_created DATETIME,
                phone_number VARCHAR(255),
                address1 VARCHAR(255),
                address2 VARCHAR(255),
                city_village VARCHAR(255),
                state_province VARCHAR(255),
                country VARCHAR(255),
                changed_by INT,
                date_changed DATETIME,
                voided INT,
                voided_by INT,
                date_voided DATETIME,
                voided_reason VARCHAR(255),
                deathdate_estimated INT,
                datim_id VARCHAR(255),
                message_uuid VARCHAR(255),
                PRIMARY KEY (patient_uuid)
);