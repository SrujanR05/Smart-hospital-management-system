package com.smarthospital.platform.service;

import com.smarthospital.platform.dto.request.LabTestRequest;
import com.smarthospital.platform.dto.response.LabTestResponse;
import com.smarthospital.platform.entity.LabTestStatus;

import java.util.List;

/**
 * Service contract for Lab Test ordering and result management.
 */
public interface LabTestService {

    /** Order a new lab test for a patient. */
    LabTestResponse orderLabTest(LabTestRequest request);

    /** Retrieve a lab test by ID. */
    LabTestResponse getLabTestById(Long id);

    /** List all lab tests for a patient. */
    List<LabTestResponse> getLabTestsByPatient(Long patientId);

    /** List lab tests for a patient filtered by status. */
    List<LabTestResponse> getLabTestsByPatientAndStatus(Long patientId, LabTestStatus status);

    /** List all lab tests ordered by a specific doctor. */
    List<LabTestResponse> getLabTestsByDoctor(Long doctorId);

    /** Update lab test details (result, status, testDate). */
    LabTestResponse updateLabTest(Long id, LabTestRequest request);

    /**
     * Record the result for a completed test and mark it COMPLETED.
     * Throws {@code IllegalOperationException} if the test is already completed.
     */
    LabTestResponse completeLabTest(Long id, String result);

    /** Delete a lab test record. */
    void deleteLabTest(Long id);
}
