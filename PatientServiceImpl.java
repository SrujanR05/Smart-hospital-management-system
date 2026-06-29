package com.smarthospital.platform.service.impl;

import com.smarthospital.platform.dto.request.PatientRequest;
import com.smarthospital.platform.dto.response.PatientResponse;
import com.smarthospital.platform.entity.Patient;
import com.smarthospital.platform.entity.User;
import com.smarthospital.platform.exception.ResourceNotFoundException;
import com.smarthospital.platform.mapper.PatientMapper;
import com.smarthospital.platform.repository.PatientRepository;
import com.smarthospital.platform.repository.UserRepository;
import com.smarthospital.platform.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link PatientService}.
 *
 * <p>Business rules:
 * <ul>
 *   <li>A patient profile requires a pre-existing {@link User} with Role = PATIENT.</li>
 *   <li>Cascaded collections (appointments, prescriptions, labs, bills)
 *       are managed by their own services — not touched here.</li>
 * </ul>
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository    userRepository;
    private final PatientMapper     patientMapper;

    @Override
    public PatientResponse createPatient(PatientRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        Patient patient = patientMapper.toEntity(request);
        patient.setUser(user);

        Patient saved = patientRepository.save(patient);
        log.info("Patient profile created with id={} for userId={}", saved.getId(), user.getId());
        return patientMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponse getPatientById(Long id) {
        return patientMapper.toResponse(findPatientOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponse getPatientByUserId(Long userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "userId", userId));
        return patientMapper.toResponse(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse updatePatient(Long id, PatientRequest request) {
        Patient patient = findPatientOrThrow(id);
        patientMapper.updateEntity(request, patient);
        return patientMapper.toResponse(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(Long id) {
        findPatientOrThrow(id);
        patientRepository.deleteById(id);
        log.info("Patient profile deleted with id={}", id);
    }

    private Patient findPatientOrThrow(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
    }
}
