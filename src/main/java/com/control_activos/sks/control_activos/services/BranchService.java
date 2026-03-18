package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.enums.OperationNotAllowedExceptionEnum;
import com.control_activos.sks.control_activos.enums.ResourceNotFoundExceptionEnum;
import com.control_activos.sks.control_activos.exception.OperationNotAllowedException;
import com.control_activos.sks.control_activos.exception.ResourceNotFoundException;
import com.control_activos.sks.control_activos.mapper.BranchMapper;
import com.control_activos.sks.control_activos.mapper.Mapper;
import com.control_activos.sks.control_activos.models.dto.BranchDTO;
import com.control_activos.sks.control_activos.models.dto.branchDTO.BranchTableDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.entity.Client;
import com.control_activos.sks.control_activos.models.entity.Branch;
import com.control_activos.sks.control_activos.repository.BranchRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BranchService {

    private final ClientService clientService;
    private final BranchRepository branchRepository;
    public BranchService(ClientService clientService, BranchRepository branchRepository) {
        this.clientService = clientService;
        this.branchRepository = branchRepository;
    }

    public List<BranchTableDTO> getBranchTableDTO(Long clientId) {
        List<Branch> branches = branchRepository.findAllByClientId(clientId);
        List<ReportCountDTO> reports = branchRepository.findActiveReportsByClientId(clientId);
        Map<Long, List<ReportCountDTO>> reportsByBranchId = reports.stream().collect(
                Collectors.groupingBy(ReportCountDTO::getId));

        return branches.stream().map(branch -> {
            BranchTableDTO branchTableDto = BranchMapper.toBranchTableDTO(branch);
            branchTableDto.setReportsActive(reportsByBranchId
                    .getOrDefault(branch.getId(), List.of()));
            return branchTableDto;
        }).toList();
    }

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

    public Branch findBranchById(Long sucursalId) {
        return branchRepository.findById(sucursalId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ResourceNotFoundExceptionEnum.BRANCH_NOT_FOUND.build(sucursalId)));
    }

}
