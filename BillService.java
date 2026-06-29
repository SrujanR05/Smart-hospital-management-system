package com.smarthospital.platform.service;

import com.smarthospital.platform.dto.request.BillRequest;
import com.smarthospital.platform.dto.response.BillResponse;
import com.smarthospital.platform.entity.BillStatus;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service contract for Billing and payment management.
 */
public interface BillService {

    /** Generate a new bill for a patient. */
    BillResponse createBill(BillRequest request);

    /** Retrieve a bill by ID. */
    BillResponse getBillById(Long id);

    /** List all bills for a patient. */
    List<BillResponse> getBillsByPatient(Long patientId);

    /** List all bills for a patient filtered by status. */
    List<BillResponse> getBillsByPatientAndStatus(Long patientId, BillStatus status);

    /** Update bill details. */
    BillResponse updateBill(Long id, BillRequest request);

    /**
     * Record a payment against a bill.
     * Automatically transitions status to PAID when {@code paidAmount >= amount}.
     * Throws {@code IllegalOperationException} if payment exceeds the total amount.
     *
     * @param id            bill ID
     * @param paymentAmount the amount being paid now
     */
    BillResponse recordPayment(Long id, BigDecimal paymentAmount);

    /** Delete a bill record. */
    void deleteBill(Long id);
}
