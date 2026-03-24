package com.control_activos.sks.control_activos.repository;

import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Long countByStatusTrue();
    Long countByStatusTrueAndDueDateBefore(OffsetDateTime dueDate);
    List<Report> findTop5ByStatusTrueOrderByCreatedAtDesc();

    // LIST OF TOP 4 REPORTS BY HARDWARE ID, ORDERED BY STATUS (ACTIVE FIRST) AND DUE DATE (MOST RECENT FIRST)
    List<Report> findTop4ByHardwareIdOrderByStatusDescDueDateDesc(Long hardwareId);

    // LIST OF ALL ACTIVE REPORTS
    @Query("""
    SELECT new com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO(
        r.hardware.branch.client.id,
        r.id,
        r.dueDate
    )
    FROM Report r
    WHERE r.status = true
""")
    List<ReportCountDTO> getAllActiveReports();

    // LIST OF ACTIVE REPORTS BY CLIENT ID
    @Query("""
        SELECT new com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO(
            r.hardware.branch.id,
            r.id,
            r.dueDate
        )
        FROM Report r
        WHERE r.hardware.branch.client.id = :clientId
            AND r.status = true
    """)
    List<ReportCountDTO> findActiveReportsByClientId(Long clientId);
}

