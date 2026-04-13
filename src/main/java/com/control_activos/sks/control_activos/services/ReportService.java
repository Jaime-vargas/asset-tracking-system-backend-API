package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.enums.OperationNotAllowedExceptionEnum;
import com.control_activos.sks.control_activos.enums.ReportPriorityEnum;
import com.control_activos.sks.control_activos.enums.ResourceNotFoundExceptionEnum;
import com.control_activos.sks.control_activos.exception.OperationNotAllowedException;
import com.control_activos.sks.control_activos.exception.ResourceNotFoundException;
import com.control_activos.sks.control_activos.mapper.Mapper;
import com.control_activos.sks.control_activos.mapper.ReportMapper;
import com.control_activos.sks.control_activos.models.dto.ReportDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportDetailDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportTableDTO;
import com.control_activos.sks.control_activos.models.entity.Hardware;
import com.control_activos.sks.control_activos.models.entity.Report;
import com.control_activos.sks.control_activos.models.entity.UserEntity;
import com.control_activos.sks.control_activos.repository.HardwareRepository;
import com.control_activos.sks.control_activos.repository.ReportRepository;
import com.control_activos.sks.control_activos.repository.UserEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ReportService {

    private final HardwareRepository hardwareRepository;
    private final ReportRepository reportRepository;
    public ReportService(HardwareRepository hardwareRepository, ReportRepository reportRepository) {
        this.hardwareRepository = hardwareRepository;
        this.reportRepository = reportRepository;
    }

    public List<ReportTableDTO> getAllReports(){
        List<Report> reports = reportRepository.findAllByOrderByStatusDescDueDateAsc();
        return reports.stream().map(ReportMapper::toReportTableDTO).toList();
    }

    public ReportDetailDTO getReportDetail(Long reportId) {
        Report report = findReportById(reportId);
        return ReportMapper.toReportDetailDTO(report);
    }

    @Transactional
    public void closeReport (Long reportId) {
        Report report = findReportById(reportId);
        report.setStatus(false);
        report.setClosedAt(OffsetDateTime.now());
        reportRepository.save(report);
    }

    // #TODO set real user in report
    // #TODO Check all methods under this comment and refactor to use real user instead of hardcoding userId in service layer
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Transactional
    public ReportDTO saveReport(Long hardwareId, ReportDTO reportDTO) {
        UserEntity userEntity = userEntityRepository.findById(1L).get();
        Hardware hardware = hardwareRepository.findById(hardwareId).get(); // #TODO validate hardware exist with optional
        Report report = new Report();
        report.setTitle(reportDTO.getTitle());
        report.setStatus(true);
        report.setHardware(hardware);
        hardware.setLastMaintenanceDate(OffsetDateTime.now());
        report.setCreatedAt(OffsetDateTime.now()); // updated dado of camera
        report.setReportedBy(userEntity); // #TODO set real user in report
        report = reportRepository.save(report);
        report.setDueDate(OffsetDateTime.parse(reportDTO.getDueDate())); // #TODO Implement due date logic
        report.setPriority(ReportPriorityEnum.valueOf(reportDTO.getPriority()));
        return Mapper.entityToDTO(report);
    }




    public Report findReportById(Long reportId) {
        return reportRepository.findById(reportId).orElseThrow(
                () -> new ResourceNotFoundException(
                        ResourceNotFoundExceptionEnum.REPORT_NOT_FOUND.build(reportId)));
    }
}
