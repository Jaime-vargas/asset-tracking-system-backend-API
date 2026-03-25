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
    List<Hardware> findHardwareByBranchId(Long branchId);
}
