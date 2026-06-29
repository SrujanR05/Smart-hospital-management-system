package com.smarthospital.platform.service;

import com.smarthospital.platform.dto.request.DepartmentRequest;
import com.smarthospital.platform.dto.response.DepartmentResponse;

import java.util.List;

/**
 * Service contract for Department management.
 */
public interface DepartmentService {

    /** Create a department. Throws {@code DuplicateResourceException} if name already exists. */
    DepartmentResponse createDepartment(DepartmentRequest request);

    /** Retrieve a department by ID. */
    DepartmentResponse getDepartmentById(Long id);

    /** List all departments. */
    List<DepartmentResponse> getAllDepartments();

    /** Update a department. Null fields are preserved. */
    DepartmentResponse updateDepartment(Long id, DepartmentRequest request);

    /** Delete a department. */
    void deleteDepartment(Long id);
}
