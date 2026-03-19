package com.control_activos.sks.control_activos.repository;

import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportHistoryDTO;
import com.control_activos.sks.control_activos.models.entity.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
    // GET LIST OF ACTIVE REPORTS BY BRANCH ID
    @Query("""
      SELECT new com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO(
            h.id,
            r.id,
            r.dueDate
      )
        FROM Hardware h
        JOIN h.reports r
        WHERE h.branch.id = :branchId
        AND r.active = true
      """)
    List<ReportCountDTO> findActiveReportsByBranchId(Long branchId);
    List<Hardware> findAllByBranchId(Long branchId);

}
