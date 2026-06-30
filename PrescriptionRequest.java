package com.smarthospital.platform.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Request DTO for issuing a new Prescription.
 * A prescription requires at least one medicine entry.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionRequest {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    /**
     * Date of the prescription. Defaults to today if omitted.
     */
    private LocalDate date;

    @NotBlank(message = "Diagnosis description is required")
    private String diagnosis;

    /**
     * List of medicines and their dosage instructions.
     * Must contain at least one entry.
     */
    @NotEmpty(message = "At least one medicine must be prescribed")
    @Valid
    private List<PrescriptionMedicineRequest> medicines;
}
