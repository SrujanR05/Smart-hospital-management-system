package com.smarthospital.platform.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating or updating a Patient profile.
 * The associated User account is linked separately via userId.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientRequest {

    /**
     * ID of the User account that will be linked to this patient profile.
     * Required when creating a new patient.
     */
    private Long userId;

    @NotBlank(message = "Patient name is required")
    private String name;

    @Min(value = 0, message = "Age must be a positive number")
    private Integer age;

    /**
     * Blood group, e.g. A+, B-, O+, AB+. Optional field.
     */
    private String bloodGroup;

    private String address;

    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,15}$", message = "Phone number must be valid (7-15 digits, optional leading +)")
    private String phone;
}
