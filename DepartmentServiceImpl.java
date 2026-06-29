package com.smarthospital.platform.service.impl;

import com.smarthospital.platform.dto.request.DepartmentRequest;
import com.smarthospital.platform.dto.response.DepartmentResponse;
import com.smarthospital.platform.entity.Department;
import com.smarthospital.platform.exception.DuplicateResourceException;
import com.smarthospital.platform.exception.ResourceNotFoundException;
import com.smarthospital.platform.mapper.DepartmentMapper;
import com.smarthospital.platform.repository.DepartmentRepository;
import com.smarthospital.platform.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link DepartmentService}.
 *
 * <p>Business rules:
 * <ul>
 *   <li>Department names must be globally unique (enforced at DB and service level).</li>
 *   <li>Deleting a department with assigned doctors will cascade and un-assign them
 *       via the {@code orphanRemoval} setting on the entity — the FK becomes null.
 *       Note: if this is unwanted, guard with a doctor-count check before deletion.</li>
 * </ul>
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper     departmentMapper;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Department", "name", request.getName());
        }
        Department saved = departmentRepository.save(departmentMapper.toEntity(request));
        log.info("Department '{}' created with id={}", saved.getName(), saved.getId());
        return departmentMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentById(Long id) {
        return departmentMapper.toResponse(findDepartmentOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department department = findDepartmentOrThrow(id);

        // Check name uniqueness only when it is actually changing
        if (request.getName() != null && !request.getName().equals(department.getName())
                && departmentRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Department", "name", request.getName());
        }

        departmentMapper.updateEntity(request, department);
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    @Override
    public void deleteDepartment(Long id) {
        findDepartmentOrThrow(id);
        departmentRepository.deleteById(id);
        log.info("Department deleted with id={}", id);
    }

    private Department findDepartmentOrThrow(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
    }
}
