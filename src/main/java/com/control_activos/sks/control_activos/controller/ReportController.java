package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.ReportDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportDetailDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportTableDTO;
import com.control_activos.sks.control_activos.models.entity.Report;
import com.control_activos.sks.control_activos.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<ReportTableDTO>> getAllReports() {
        List<ReportTableDTO> reportTableDTO = reportService.getAllReports();
        return ResponseEntity.ok().body(reportTableDTO);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportDetailDTO> getReportDetail(@PathVariable long reportId) {
        ReportDetailDTO reportDetailDTO = reportService.getReportDetail(reportId);
        return ResponseEntity.ok().body(reportDetailDTO);
    }

    // #TODO check endpoints below this comment
    @PostMapping
    public ResponseEntity<ReportDTO> createReport(@PathVariable long hardwareId, @RequestBody ReportDTO reportDTO) {
        reportDTO = reportService.saveReport(hardwareId, reportDTO);
        return ResponseEntity.ok().body(reportDTO);
    }

    @PutMapping("/{reportId}/close")
    public ResponseEntity<?> closeReport (@PathVariable long hardwareId, @PathVariable long reportId) {
        reportService.closeReport(hardwareId, reportId);
        return  ResponseEntity.noContent().build();
    }
}
