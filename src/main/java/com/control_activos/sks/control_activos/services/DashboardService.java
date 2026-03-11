package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.mapper.Mapper;
import com.control_activos.sks.control_activos.mapper.clientMapper.ClientMapper;
import com.control_activos.sks.control_activos.mapper.reportMapper.ReportMapper;
import com.control_activos.sks.control_activos.models.dto.ClientDTO;
import com.control_activos.sks.control_activos.models.dto.DashboardDataDTO;
import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientDashboardDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportTableDTO;
import com.control_activos.sks.control_activos.repository.*;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class DashboardService {
    /** open reports
     * overdue reports
     * total hardware
     * total clientes
     * 8 reportes recientes
     * 4 clientes reciente
     * total camaras
     */

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
        List<ReportTableDTO> recentReports = reportRepository.findTop8ByOrderByCreatedAtDesc().stream()
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
