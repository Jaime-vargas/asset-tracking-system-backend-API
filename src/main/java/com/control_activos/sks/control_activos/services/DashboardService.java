package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.mapper.ClientMapper;
import com.control_activos.sks.control_activos.mapper.ReportMapper;
import com.control_activos.sks.control_activos.models.dto.DashboardDataDTO;
import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientDashboardDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportTableDTO;
import com.control_activos.sks.control_activos.repository.*;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class DashboardService {

    private final CameraRepository cameraRepository;
    private final ClientRepository clientRepository;
    private final HardwareRepository hardwareRepository;
    private final ReportRepository reportRepository;
    private final SwitchRepository switchRepository;

    public DashboardService(CameraRepository cameraRepository, ClientRepository clientRepository, HardwareRepository hardwareRepository, ReportRepository reportRepository, SwitchRepository switchRepository) {
        this.cameraRepository = cameraRepository;
        this.clientRepository = clientRepository;
        this.hardwareRepository = hardwareRepository;
        this.reportRepository = reportRepository;
        this.switchRepository = switchRepository;
    }

    public DashboardDataDTO getDashboardData() {
        Long openReports = reportRepository.countByActiveTrue();
        Long overdueReports = reportRepository.countByActiveTrueAndDueDateBefore(OffsetDateTime.now());
        Long totalHardware = hardwareRepository.count();
        Long totalClients = clientRepository.count();
        List<ReportTableDTO> recentReports = reportRepository.findTop5ByActiveTrueOrderByCreatedAtDesc().stream()
                .map(ReportMapper::toReportTableDTO)
                .toList();
        List<ClientDashboardDTO> recentClients = clientRepository.findTop4ByOrderByNameAscIdAsc().stream()
                .map(ClientMapper::toClientDashboardDTO)
                .toList();
        Long totalCameras = cameraRepository.count();
        Long totalSwitches = switchRepository.count();
        Long otherHardware = 0L; // #TODO: Implements logic to count other hardware types
        return new DashboardDataDTO(
                openReports,
                overdueReports,
                totalHardware,
                totalClients,
                recentReports,
                recentClients,
                totalCameras,
                totalSwitches,
                otherHardware
        );
    }

}
