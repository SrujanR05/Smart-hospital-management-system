package com.smarthospital.platform.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for adding a medicine line-item to a prescription.
 * Used as a nested object within {@link PrescriptionRequest}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionMedicineRequest {

    /**
     * ID of the Medicine catalogue entry.
     */
    private Long medicineId;

    /**
     * Dosage instructions, e.g. "1-0-1 after food", "twice daily".
     */
    @NotBlank(message = "Dosage instructions are required")
    private String dosage;
}
