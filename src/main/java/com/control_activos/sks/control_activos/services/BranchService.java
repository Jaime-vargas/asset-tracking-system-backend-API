package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.enums.OperationNotAllowedExceptionEnum;
import com.control_activos.sks.control_activos.enums.ResourceNotFoundExceptionEnum;
import com.control_activos.sks.control_activos.exception.OperationNotAllowedException;
import com.control_activos.sks.control_activos.exception.ResourceNotFoundException;
import com.control_activos.sks.control_activos.mapper.BranchMapper;
import com.control_activos.sks.control_activos.mapper.HardwareMapper;
import com.control_activos.sks.control_activos.mapper.Mapper;
import com.control_activos.sks.control_activos.models.dto.BranchDTO;
import com.control_activos.sks.control_activos.models.dto.branchDTO.BranchTableDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareTableDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.entity.Client;
import com.control_activos.sks.control_activos.models.entity.Branch;
import com.control_activos.sks.control_activos.models.entity.Hardware;
import com.control_activos.sks.control_activos.models.entity.Report;
import com.control_activos.sks.control_activos.repository.BranchRepository;
import com.control_activos.sks.control_activos.repository.HardwareRepository;
import com.control_activos.sks.control_activos.repository.ReportRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BranchService {

    private final ClientService clientService;
    private final BranchRepository branchRepository;
    private final HardwareRepository hardwareRepository;
    private final ReportRepository reportRepository;
    public BranchService(ClientService clientService, BranchRepository branchRepository, HardwareRepository hardwareRepository, ReportRepository reportRepository) {
        this.clientService = clientService;
        this.branchRepository = branchRepository;
        this.hardwareRepository = hardwareRepository;
        this.reportRepository = reportRepository;
    }


    public List<HardwareTableDTO> getHardwareByBranchId(@PathVariable Long branchId){
        findBranchById(branchId);
        List<Hardware> hardwareList = hardwareRepository.findHardwareByBranchId(branchId);
        List<ReportCountDTO> activeReports = reportRepository.findActiveReportsByBranchId(branchId);
        return mergeHardwareAndReportsToDTO(hardwareList, activeReports);
    }

    // HELPER METHODS
    private Map<Long, List<ReportCountDTO>> groupReportsById(List<ReportCountDTO> activeReports) {
        return activeReports.stream().collect(Collectors.groupingBy(ReportCountDTO::getId));
    }

    private List<HardwareTableDTO> mergeHardwareAndReportsToDTO(List<Hardware> hardwareList, List<ReportCountDTO> activeReports) {
        Map<Long, List<ReportCountDTO>> reportsByBranchId = groupReportsById(activeReports);
        return hardwareList.stream().map(hardware -> {
            HardwareTableDTO hardwareTableDTO = HardwareMapper.toHardwareTableDTO(hardware);
            hardwareTableDTO.setReportsActive(reportsByBranchId.getOrDefault(hardware.getId(), List.of()));
            return hardwareTableDTO;
        }).toList();
    }

    // VALIDATIONS
    public Branch findBranchById(Long sucursalId) {
        return branchRepository.findById(sucursalId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ResourceNotFoundExceptionEnum.BRANCH_NOT_FOUND.build(sucursalId)));
    }

    // #TODO: check functions below this comment

    @Transactional
    public BranchDTO saveBranch(Long clientId, BranchDTO branchDTO) {
        Client client = clientService.findClientById(clientId);
        Branch branch = new Branch();
        branch.setClient(client);
        branch.setName(branchDTO.getName());
        branch = branchRepository.save(branch);
        return Mapper.entityToDTO(branch);
    }

    @Transactional
    public BranchDTO editBranch(Long clientId, Long sucursalId, BranchDTO branchDTO) {
        Branch branch = findBranchById(sucursalId);
        if (!branch.getClient().getId().equals(clientId)) {
            throw new OperationNotAllowedException(
                    OperationNotAllowedExceptionEnum.SUCURSAL_NOT_BELONG_TO_CLIENT.getMessage());
        }
        branch.setName(branchDTO.getName());
        branch = branchRepository.save(branch);
        return Mapper.entityToDTO(branch);
    }
}
