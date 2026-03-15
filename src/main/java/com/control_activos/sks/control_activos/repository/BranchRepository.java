package com.control_activos.sks.control_activos.repository;

import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
    List<Branch>findAllByClientId(Long clientId);
    @Query("""
        SELECT new com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO(
            b.id,
            r.id,
            r.dueDate
        )
        FROM Branch b
        JOIN b.hardware h
        JOIN h.reports r
        WHERE b.client.id = :clientId
        AND r.active = true
        """)
    List<ReportCountDTO> findActiveReportsByClientId(Long clientId);
}
