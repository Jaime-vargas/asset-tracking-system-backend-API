package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.BranchDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.CameraRequestDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareDetailDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareTableDTO;
import com.control_activos.sks.control_activos.services.BranchService;
import com.control_activos.sks.control_activos.services.CameraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    private final BranchService branchService;
    private final CameraService cameraService;
    public BranchController(BranchService branchService,  CameraService cameraService) {
        this.branchService = branchService;
        this.cameraService = cameraService;
    }


    @GetMapping("/{branchId}/hardware")
    public ResponseEntity<List<HardwareTableDTO>> getHardwareByBranchId(@PathVariable Long branchId){
        List<HardwareTableDTO> hardwareTableDTO = branchService.getHardwareByBranchId(branchId);
        return ResponseEntity.ok().body(hardwareTableDTO);
    }

    @PostMapping("/{branchId}/hardware")
    public ResponseEntity<HardwareDetailDTO>saveCamera(@PathVariable Long branchId, @RequestBody CameraRequestDTO cameraRequestDTO){
        HardwareDetailDTO hardwareDetailDTO = cameraService.saveCamera(branchId, cameraRequestDTO);
        return ResponseEntity.ok().body(hardwareDetailDTO);
    }




    // #TODO: check endpoints below this comment
    @PostMapping
    public ResponseEntity<BranchDTO> saveBranch(@RequestBody BranchDTO branchDTO) {
        branchDTO = branchService.saveBranch(branchDTO.getId(), branchDTO); // TODO: Add clientId to BranchDTO and refactor this method to use it instead of hardcoding clientId in service layer
        return ResponseEntity.ok().body(branchDTO);
}

    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateSucursal(@PathVariable Long branchId, @RequestBody BranchDTO branchDTO) {
        branchDTO = branchService.editBranch(branchId, branchId, branchDTO); // TODO: Add clientId to BranchDTO and refactor this method to use it instead of hardcoding clientId in service layer
        return ResponseEntity.ok().body(branchDTO);
    }
}
