package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.enums.OperationNotAllowedExceptionEnum;
import com.control_activos.sks.control_activos.enums.ResourceNotFoundExceptionEnum;
import com.control_activos.sks.control_activos.exception.OperationNotAllowedException;
import com.control_activos.sks.control_activos.exception.ResourceNotFoundException;
import com.control_activos.sks.control_activos.mapper.Mapper;
import com.control_activos.sks.control_activos.models.dto.BranchDTO;
import com.control_activos.sks.control_activos.models.entity.Client;
import com.control_activos.sks.control_activos.models.entity.Branch;
import com.control_activos.sks.control_activos.repository.BranchRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    private final ClientService clientService;
    private final BranchRepository branchRepository;
    public BranchService(ClientService clientService, BranchRepository branchRepository) {
        this.clientService = clientService;
        this.branchRepository = branchRepository;
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
        Branch branch = findSucursalById(sucursalId);
        if (!branch.getClient().getId().equals(clientId)) {
            throw new OperationNotAllowedException(
                    OperationNotAllowedExceptionEnum.SUCURSAL_NOT_BELONG_TO_CLIENT.getMessage());
        }
        branch.setName(branchDTO.getName());
        branch = branchRepository.save(branch);
        return Mapper.entityToDTO(branch);
    }

    public Branch findSucursalById(Long sucursalId) {
        return branchRepository.findById(sucursalId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        ResourceNotFoundExceptionEnum.SUCURSAL_NOT_FOUND.build(sucursalId)));
    }

}
