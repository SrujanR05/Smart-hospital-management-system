package com.smarthospital.platform.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Request DTO for creating or updating a Doctor profile.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorRequest {

    /**
     * ID of the User account that will be linked to this doctor profile.
     * Required when creating a new doctor.
     */
    private Long userId;

    @NotBlank(message = "Doctor name is required")
    private String name;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotNull(message = "Years of experience is required")
    @Min(value = 0, message = "Experience cannot be negative")
    private Integer experience;

    @NotNull(message = "Consultation fee is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Fee cannot be negative")
    private BigDecimal fee;

    /**
     * Optional department ID. A doctor may be unassigned to a department initially.
     */
    private Long departmentId;
}
