package com.smarthospital.platform.repository;

import com.smarthospital.platform.entity.Bill;
import com.smarthospital.platform.entity.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByPatientId(Long patientId);
    List<Bill> findByPatientIdAndStatus(Long patientId, BillStatus status);
}
