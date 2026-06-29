package com.smarthospital.platform.service;

import com.smarthospital.platform.dto.request.DoctorRequest;
import com.smarthospital.platform.dto.response.DoctorResponse;

import java.util.List;

/**
 * Service contract for Doctor profile management.
 */
public interface DoctorService {

    /** Create a doctor profile linked to an existing user and optional department. */
    DoctorResponse createDoctor(DoctorRequest request);

    /** Retrieve a doctor by profile ID. */
    DoctorResponse getDoctorById(Long id);

    /** List all doctors. */
    List<DoctorResponse> getAllDoctors();

    /** List all doctors belonging to a specific department. */
    List<DoctorResponse> getDoctorsByDepartment(Long departmentId);

    /** Update doctor profile fields. Null fields are preserved. */
    DoctorResponse updateDoctor(Long id, DoctorRequest request);

    /** Assign or reassign a doctor to a department. */
    DoctorResponse assignDepartment(Long doctorId, Long departmentId);

    /** Delete a doctor profile. */
    void deleteDoctor(Long id);
}
