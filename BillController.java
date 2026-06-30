package com.smarthospital.platform.controller;

import com.smarthospital.platform.dto.request.BillRequest;
import com.smarthospital.platform.dto.response.BillResponse;
import com.smarthospital.platform.entity.BillStatus;
import com.smarthospital.platform.service.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    public ResponseEntity<BillResponse> createBill(@Valid @RequestBody BillRequest request) {
        BillResponse response = billService.createBill(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST') or @securityService.isOwnBill(#id)")
    public ResponseEntity<BillResponse> getBillById(@PathVariable Long id) {
        BillResponse response = billService.getBillById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST') or @securityService.isOwnPatient(#patientId)")
    public ResponseEntity<List<BillResponse>> getBillsByPatient(@PathVariable Long patientId) {
        List<BillResponse> response = billService.getBillsByPatient(patientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/patient/{patientId}/status/{status}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST') or @securityService.isOwnPatient(#patientId)")
    public ResponseEntity<List<BillResponse>> getBillsByPatientAndStatus(
            @PathVariable Long patientId, @PathVariable BillStatus status) {
        List<BillResponse> response = billService.getBillsByPatientAndStatus(patientId, status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    public ResponseEntity<BillResponse> updateBill(
            @PathVariable Long id, @Valid @RequestBody BillRequest request) {
        BillResponse response = billService.updateBill(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/payment")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPTIONIST')")
    public ResponseEntity<BillResponse> recordPayment(
            @PathVariable Long id, @RequestParam BigDecimal amount) {
        BillResponse response = billService.recordPayment(id, amount);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
