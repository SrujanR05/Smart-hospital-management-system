package com.smarthospital.platform.service;

import com.smarthospital.platform.dto.request.PatientRequest;
import com.smarthospital.platform.dto.response.PatientResponse;

import java.util.List;

/**
 * Service contract for Patient profile management.
 */
public interface PatientService {

    /** Create a patient profile linked to an existing user. */
    PatientResponse createPatient(PatientRequest request);

    /** Retrieve a patient by profile ID. */
    PatientResponse getPatientById(Long id);

    /** Retrieve a patient by their linked user ID. */
    PatientResponse getPatientByUserId(Long userId);

    /** List all patients. */
    List<PatientResponse> getAllPatients();

    /** Update patient profile fields. Null fields are preserved (PATCH semantics). */
    PatientResponse updatePatient(Long id, PatientRequest request);

    /** Delete a patient profile. */
    void deletePatient(Long id);
}
