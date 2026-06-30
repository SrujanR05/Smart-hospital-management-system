package com.smarthospital.platform.dto.request;

import com.smarthospital.platform.entity.AppointmentPriority;
import com.smarthospital.platform.entity.AppointmentStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Request DTO for booking or updating an Appointment.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequest {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotNull(message = "Appointment date is required")
    @Future(message = "Appointment date must be in the future")
    private LocalDate date;

    @NotNull(message = "Appointment time is required")
    private LocalTime time;

    /**
     * Status update. Defaults to PENDING on creation; used for status transitions (confirm, cancel, complete).
     */
    private AppointmentStatus status;

    /**
     * Priority level. Defaults to NORMAL if not provided.
     */
    private AppointmentPriority priority;
}
