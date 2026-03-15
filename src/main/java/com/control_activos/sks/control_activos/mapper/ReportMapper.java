package com.control_activos.sks.control_activos.mapper;

import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportTableDTO;
import com.control_activos.sks.control_activos.models.entity.Report;
import java.util.Optional;

public class ReportMapper {

    public static ReportTableDTO toReportTableDTO(Report report) {
        return new ReportTableDTO(
            report.getId(),
            report.getTitle(),
            Optional.ofNullable(report.getPriority()).map(Object::toString).orElse("N/A"),
            Optional.ofNullable(report.getDueDate()).map(Object::toString).orElse("N/A")
        );
    }
}
