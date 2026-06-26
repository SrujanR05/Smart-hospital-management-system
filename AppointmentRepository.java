package com.smarthospital.platform.repository;

import com.smarthospital.platform.entity.Appointment;
import com.smarthospital.platform.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByDate(LocalDate date);
    List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDate date);

    // Custom query to implement the Emergency Patient Priority Queue
    // Orders PENDING appointments by Priority first (CRITICAL -> HIGH -> NORMAL), then by Date and Time
    @Query("SELECT a FROM Appointment a WHERE a.status = :status " +
           "ORDER BY CASE a.priority " +
           "  WHEN 'CRITICAL' THEN 1 " +
           "  WHEN 'HIGH' THEN 2 " +
           "  ELSE 3 END ASC, a.date ASC, a.time ASC")
    List<Appointment> findAppointmentsByStatusOrderedByPriorityAndSchedule(AppointmentStatus status);
}
