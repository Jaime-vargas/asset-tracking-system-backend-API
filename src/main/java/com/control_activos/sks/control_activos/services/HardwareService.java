package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.enums.ResourceNotFoundExceptionEnum;
import com.control_activos.sks.control_activos.exception.ResourceNotFoundException;
import com.control_activos.sks.control_activos.mapper.HardwareMapper;
import com.control_activos.sks.control_activos.mapper.ReportMapper;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.CameraDetailDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareDetailDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareTableDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportHistoryDTO;
import com.control_activos.sks.control_activos.models.entity.Hardware;
import com.control_activos.sks.control_activos.models.entity.Report;
import com.control_activos.sks.control_activos.repository.HardwareRepository;
import com.control_activos.sks.control_activos.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HardwareService {

    private final BranchService branchService;
    private final ClientService clientService;
    private final HardwareRepository hardwareRepository;
    private final ReportRepository reportRepository;

    public HardwareService(BranchService branchService, ClientService clientService, HardwareRepository hardwareRepository, ReportRepository reportRepository) {
        this.branchService = branchService;
        this.clientService = clientService;
        this.hardwareRepository = hardwareRepository;
        this.reportRepository = reportRepository;
    }

    // HARDWARE DETAILS BY ID
    public HardwareDetailDTO getHardwareDetailById(Long clientId, Long branchID, Long hardwareID) {
        clientService.findClientById(clientId);
        branchService.findBranchById(branchID);
        Hardware hardware = findHardwareById(hardwareID);
        List<Report> recentActiveReports = reportRepository.findTop4ByHardwareIdAndActiveTrueOrderByDueDateDesc(hardwareID);
        HardwareDetailDTO hardwareDetailDTO = new HardwareMapper().hardwareDetailDTO(hardware);
        List<ReportHistoryDTO> reportHistoryDTO = recentActiveReports.stream().map(ReportMapper::toReportHistoryDTO).toList();
        hardwareDetailDTO.setRecentActiveReports(reportHistoryDTO);
        return hardwareDetailDTO;
    }

    // LIST OF HARDWARE BY BRANCH
    public List<HardwareTableDTO> getHardwareByBranch(Long clientID, Long branchID) {
        clientService.findClientById(clientID);
        branchService.findBranchById(branchID);
        List<Hardware> hardwareList = hardwareRepository.findAllByBranchId(branchID);
        List<ReportCountDTO> activeReports = hardwareRepository.findActiveReportsByBranchId(branchID);
        Map<Long, List<ReportCountDTO>> reportsByHardwareId = activeReports.stream().collect(
                Collectors.groupingBy(ReportCountDTO::getId));
        return hardwareList.stream().map(hardware -> {
            HardwareTableDTO hardwareTableDTO = HardwareMapper.toHardwareTableDTO(hardware);
            hardwareTableDTO.setReportsActive(reportsByHardwareId.getOrDefault(hardware.getId(), List.of()));
            return hardwareTableDTO;
                }
        ).toList();
    }

    public Hardware findHardwareById(Long hardwareId) {
        return hardwareRepository.findById(hardwareId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ResourceNotFoundExceptionEnum.HARDWARE_NOT_FOUND.build(hardwareId)));
    }
}
