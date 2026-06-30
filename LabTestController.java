package com.smarthospital.platform.controller;

import com.smarthospital.platform.dto.request.LabTestRequest;
import com.smarthospital.platform.dto.response.LabTestResponse;
import com.smarthospital.platform.entity.LabTestStatus;
import com.smarthospital.platform.service.LabTestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab-tests")
@RequiredArgsConstructor
public class LabTestController {

    private final LabTestService labTestService;

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<LabTestResponse> orderLabTest(@Valid @RequestBody LabTestRequest request) {
        LabTestResponse response = labTestService.orderLabTest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or @securityService.isOwnLabTest(#id)")
    public ResponseEntity<LabTestResponse> getLabTestById(@PathVariable Long id) {
        LabTestResponse response = labTestService.getLabTestById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'RECEPTIONIST') or @securityService.isOwnPatient(#patientId)")
    public ResponseEntity<List<LabTestResponse>> getLabTestsByPatient(@PathVariable Long patientId) {
        List<LabTestResponse> response = labTestService.getLabTestsByPatient(patientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/patient/{patientId}/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'RECEPTIONIST') or @securityService.isOwnPatient(#patientId)")
    public ResponseEntity<List<LabTestResponse>> getLabTestsByPatientAndStatus(
            @PathVariable Long patientId, @PathVariable LabTestStatus status) {
        List<LabTestResponse> response = labTestService.getLabTestsByPatientAndStatus(patientId, status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/doctor/{doctorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR') or @securityService.isOwnDoctor(#doctorId)")
    public ResponseEntity<List<LabTestResponse>> getLabTestsByDoctor(@PathVariable Long doctorId) {
        List<LabTestResponse> response = labTestService.getLabTestsByDoctor(doctorId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<LabTestResponse> updateLabTest(
            @PathVariable Long id, @Valid @RequestBody LabTestRequest request) {
        LabTestResponse response = labTestService.updateLabTest(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/complete")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST', 'DOCTOR')")
    public ResponseEntity<LabTestResponse> completeLabTest(
            @PathVariable Long id, @RequestParam String result) {
        LabTestResponse response = labTestService.completeLabTest(id, result);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<Void> deleteLabTest(@PathVariable Long id) {
        labTestService.deleteLabTest(id);
        return ResponseEntity.noContent().build();
    }
}
