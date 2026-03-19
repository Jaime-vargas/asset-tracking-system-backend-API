package com.control_activos.sks.control_activos.repository;

import com.control_activos.sks.control_activos.models.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Long countByActiveTrue();
    Long countByActiveTrueAndDueDateBefore(OffsetDateTime dueDate);
    List<Report> findTop5ByActiveTrueOrderByCreatedAtDesc();

    // LIST OF REPORTS BY HARDWARE ID
    List<Report> findTop4ByHardwareIdAndActiveTrueOrderByDueDateDesc(Long hardwareId);
}

