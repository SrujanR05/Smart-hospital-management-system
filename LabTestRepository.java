package com.smarthospital.platform.repository;

import com.smarthospital.platform.entity.LabTest;
import com.smarthospital.platform.entity.LabTestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Long> {
    List<LabTest> findByPatientId(Long patientId);
    List<LabTest> findByPatientIdAndStatus(Long patientId, LabTestStatus status);
    List<LabTest> findByDoctorId(Long doctorId);
}
