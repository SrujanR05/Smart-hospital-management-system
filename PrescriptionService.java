package com.smarthospital.platform.service;

import com.smarthospital.platform.dto.request.PrescriptionRequest;
import com.smarthospital.platform.dto.response.PrescriptionResponse;

import java.util.List;

/**
 * Service contract for Prescription issuance and retrieval.
 */
public interface PrescriptionService {

    /** Issue a new prescription for a patient with one or more medicines. */
    PrescriptionResponse createPrescription(PrescriptionRequest request);

    /** Retrieve a prescription by ID. */
    PrescriptionResponse getPrescriptionById(Long id);

    /** List all prescriptions issued to a patient. */
    List<PrescriptionResponse> getPrescriptionsByPatient(Long patientId);

    /** List all prescriptions issued by a doctor. */
    List<PrescriptionResponse> getPrescriptionsByDoctor(Long doctorId);

    /**
     * Update prescription fields (diagnosis, medicines).
     * Replaces the full medicine list if provided.
     */
    PrescriptionResponse updatePrescription(Long id, PrescriptionRequest request);

    /** Delete a prescription. */
    void deletePrescription(Long id);
}
