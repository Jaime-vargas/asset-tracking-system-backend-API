package com.control_activos.sks.control_activos.mapper;

import com.control_activos.sks.control_activos.models.dto.branchDTO.BranchTableDTO;
import com.control_activos.sks.control_activos.models.entity.Branch;

public class BranchMapper {

    public static BranchTableDTO toBranchTableDTO(Branch branch) {
        BranchTableDTO branchTableDTO = new BranchTableDTO();
        branchTableDTO.setId(branch.getId());
        branchTableDTO.setName(branch.getName());
        branchTableDTO.setTotalHardware((long) branch.getHardware().size());
        return branchTableDTO;
    }
}
