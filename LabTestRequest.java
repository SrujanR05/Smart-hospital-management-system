package com.smarthospital.platform.dto.request;

import com.smarthospital.platform.entity.LabTestStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Request DTO for ordering or updating a Lab Test.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabTestRequest {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    /**
     * Optional: ID of the doctor ordering the test.
     */
    private Long doctorId;

    @NotBlank(message = "Test name is required")
    private String testName;

    /**
     * Test result. Populated when the status is updated to COMPLETED.
     */
    private String result;

    /**
     * Lab test status — used for status update operations (e.g. PENDING → COMPLETED).
     */
    private LabTestStatus status;

    /**
     * Date the test is scheduled or was performed. Defaults to today if omitted.
     */
    private LocalDate testDate;
}
