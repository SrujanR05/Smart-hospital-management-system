package com.smarthospital.platform.service;

import com.smarthospital.platform.dto.request.AppointmentRequest;
import com.smarthospital.platform.dto.response.AppointmentResponse;
import com.smarthospital.platform.entity.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Service contract for Appointment scheduling and management.
 */
public interface AppointmentService {

    /** Book a new appointment. */
    AppointmentResponse bookAppointment(AppointmentRequest request);

    /** Retrieve an appointment by ID. */
    AppointmentResponse getAppointmentById(Long id);

    /** List all appointments for a patient. */
    List<AppointmentResponse> getAppointmentsByPatient(Long patientId);

    /** List all appointments for a doctor. */
    List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId);

    /** List all appointments on a given date. */
    List<AppointmentResponse> getAppointmentsByDate(LocalDate date);

    /**
     * List all PENDING appointments ordered by priority queue:
     * CRITICAL → HIGH → NORMAL, then by date and time ascending.
     * Implements the Emergency Patient Priority Queue requirement.
     */
    List<AppointmentResponse> getPriorityQueue();

    /** Update appointment details or change status (confirm, cancel, complete). */
    AppointmentResponse updateAppointment(Long id, AppointmentRequest request);

    /**
     * Transition appointment status.
     * Enforces valid transitions: PENDING → CONFIRMED → COMPLETED, PENDING/CONFIRMED → CANCELLED.
     */
    AppointmentResponse changeStatus(Long id, AppointmentStatus newStatus);

    /** Cancel an appointment. Shortcut for {@code changeStatus(id, CANCELLED)}. */
    AppointmentResponse cancelAppointment(Long id);

    /** Delete an appointment record. */
    void deleteAppointment(Long id);
}
