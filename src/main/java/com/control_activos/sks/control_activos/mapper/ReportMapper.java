package com.control_activos.sks.control_activos.mapper;

import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportDetailDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportHistoryDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportDashboardDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportTableDTO;
import com.control_activos.sks.control_activos.models.entity.Report;

import java.util.List;
import java.util.Optional;

public class ReportMapper {

    public static ReportDashboardDTO toReportDashboardDTO(Report report) {
        return new ReportDashboardDTO(
                report.getId(),
                report.getTitle(),
                Optional.ofNullable(report.getPriority()).map(Object::toString).orElse("N/A"),
                Optional.ofNullable(report.getDueDate()).map(Object::toString).orElse("N/A")
        );
    }

    public static ReportHistoryDTO toReportHistoryDTO(Report report) {
        return new ReportHistoryDTO(
                report.getId(),
                Optional.ofNullable(report.getDueDate()).map(Object::toString).orElse("N/A"),
                Optional.ofNullable(report.getPriority()).map(Object::toString).orElse("N/A"),
                report.getTitle(),
                report.getReportedBy().getFullName(),
                report.getStatus()
        );
    }

    public static ReportTableDTO toReportTableDTO(Report report) {
        return new ReportTableDTO(
                report.getId(),
                report.getTitle(),
                report.getHardware().getBranch().getClient().getId(),
                report.getHardware().getBranch().getClient().getName(),
                report.getHardware().getBranch().getId(),
                report.getHardware().getBranch().getName(),
                report.getHardware().getId(),
                report.getHardware().getName(),
                Optional.ofNullable(report.getPriority()).map(Object::toString).orElse("N/A"),
                report.getCreatedAt(),
                report.getDueDate(),
                report.getStatus()
        );
    }

    public static ReportDetailDTO toReportDetailDTO(Report report) {
        return new ReportDetailDTO(
                report.getId(),
                report.getTitle(),
                report.getReportDetails(),
                report.getPhotos().stream().map(PhotoMapper::toPhotoDTO).toList(),
                Optional.ofNullable(report.getComments()).orElse(List.of()).stream().map(Mapper::entityToDTO).toList(),
                report.getStatus(),
                report.getHardware().getName(),
                report.getReportedBy().getFullName(),
                Optional.ofNullable(report.getCreatedAt()).map(Object::toString).orElse("N/A"),
                Optional.ofNullable(report.getUpdatedAt()).map(Object::toString).orElse("N/A"),
                Optional.ofNullable(report.getClosedAt()).map(Object::toString).orElse("N/A"),
                Optional.ofNullable(report.getDueDate()).map(Object::toString).orElse("N/A"),
                report.getPriority().toString()
        );
    }
}
