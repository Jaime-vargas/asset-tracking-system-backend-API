package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.CameraDTO;
import com.control_activos.sks.control_activos.services.CameraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class CameraController {

    private final CameraService cameraService;
    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;
    }

    @GetMapping("/{branchId}/cameras")
    public ResponseEntity<List<CameraDTO>> getCameraDTOList (@PathVariable Long branchId){
        List<CameraDTO> cameraDTOList = cameraService.getCameraDTOList(branchId);
        return ResponseEntity.ok().body(cameraDTOList);
    }

    @PostMapping("/{branchId}/cameras")
    public ResponseEntity<CameraDTO> saveCamera(@PathVariable Long branchId, @RequestBody CameraDTO cameraDTO) {
        CameraDTO savedCameraDTO = cameraService.saveCamera(branchId, cameraDTO);
        return ResponseEntity.ok().body(savedCameraDTO);
    }

    @PutMapping("/{branchId}/cameras/{cameraId}")
    public ResponseEntity<CameraDTO> editCamera(@PathVariable Long branchId, @PathVariable Long cameraId, @RequestBody CameraDTO cameraDTO) {
        CameraDTO updatedCameraDTO = cameraService.editCamera(branchId, cameraId, cameraDTO);
        return ResponseEntity.ok().body(updatedCameraDTO);
    }

}
