package com.smarthospital.platform.dto.request;

import com.smarthospital.platform.entity.BillStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Request DTO for generating or updating a Bill.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillRequest {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Total bill amount is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount cannot be negative")
    private BigDecimal amount;

    /**
     * Amount paid so far. Defaults to 0.00 if not supplied.
     */
    @DecimalMin(value = "0.0", inclusive = true, message = "Paid amount cannot be negative")
    private BigDecimal paidAmount;

    /**
     * Bill status — used for status updates (e.g. PENDING → PAID).
     */
    private BillStatus status;

    /**
     * Optional insurance policy / claim reference.
     */
    private String insuranceInfo;
}
