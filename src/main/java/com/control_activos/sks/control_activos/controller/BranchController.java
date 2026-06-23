package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.BranchDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.CameraRequestDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareDetailDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareTableDTO;
import com.control_activos.sks.control_activos.services.BranchService;
import com.control_activos.sks.control_activos.services.CameraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;
    private final CameraService cameraService;

<<<<<<< HEAD
    /** Branch Endpoints */
    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateSucursal(@PathVariable Long branchId, @RequestBody BranchDTO branchDTO) {
=======
    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long branchId, @RequestBody BranchDTO branchDTO) {
>>>>>>> 0464c3d (new contrllers on branch and client entities)
        branchDTO = branchService.editBranch(branchId, branchDTO);
        return ResponseEntity.ok().body(branchDTO);
    }

    /** Hardware related endpoints  */
    @GetMapping("/{branchId}/hardware")
    public ResponseEntity<List<HardwareTableDTO>> getHardwareByBranchId(@PathVariable Long branchId){
        List<HardwareTableDTO> hardwareTableDTO = branchService.getHardwareByBranchId(branchId);
        return ResponseEntity.ok().body(hardwareTableDTO);
    }

    @PostMapping("/{branchId}/hardware")
    public ResponseEntity<HardwareDetailDTO>createCamera(@PathVariable Long branchId, @RequestBody CameraRequestDTO cameraRequestDTO){
        HardwareDetailDTO hardwareDetailDTO = cameraService.saveCamera(branchId, cameraRequestDTO);
        return ResponseEntity.ok().body(hardwareDetailDTO);
    }
}
