package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.mapper.HardwareMapper;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareTableDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.entity.Hardware;
import com.control_activos.sks.control_activos.repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HardwareService {

    private final BranchService branchService;
    private final ClientService clientService;
    private final HardwareRepository hardwareRepository;

    public HardwareService(BranchService branchService, ClientService clientService, HardwareRepository hardwareRepository) {
        this.branchService = branchService;
        this.clientService = clientService;
        this.hardwareRepository = hardwareRepository;
    }

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
}
