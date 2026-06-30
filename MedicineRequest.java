package com.smarthospital.platform.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for adding or updating a Medicine catalogue entry.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineRequest {

    @NotBlank(message = "Medicine name is required")
    private String name;

    /**
     * Optional notes about the medicine (composition, category, side effects, etc.).
     */
    private String description;
}
