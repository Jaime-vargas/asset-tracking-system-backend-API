package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.BranchDTO;
import com.control_activos.sks.control_activos.models.dto.branchDTO.BranchTableDTO;
import com.control_activos.sks.control_activos.services.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class BranchController {

    private final BranchService branchService;
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/{clientId}/branches")
    public ResponseEntity<List<BranchTableDTO>> getBranchTableDTO(@PathVariable Long clientId) {
        List<BranchTableDTO> branchTableDTO = branchService.getBranchTableDTO(clientId);
        return ResponseEntity.ok().body(branchTableDTO);
    }

    @PostMapping("/{clientId}/branches")
    public ResponseEntity<BranchDTO> saveBranch(@PathVariable Long clientId, @RequestBody BranchDTO branchDTO) {
        branchDTO = branchService.saveBranch(clientId, branchDTO);
        return ResponseEntity.ok().body(branchDTO);
}


    @PutMapping("/{clientId}/branches/{branchId}")
    public ResponseEntity<BranchDTO> updateSucursal(@PathVariable Long clientId, @PathVariable Long branchId, @RequestBody BranchDTO branchDTO) {
        branchDTO = branchService.editBranch(clientId, branchId, branchDTO);
        return ResponseEntity.ok().body(branchDTO);
    }
}
