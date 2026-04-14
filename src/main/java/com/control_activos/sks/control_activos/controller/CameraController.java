package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.hardwareDTO.CameraDetailDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.CameraRequestDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareDetailDTO;
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

    @PostMapping("/{branchId}/cameras")
    public ResponseEntity<List<HardwareDetailDTO>> saveCamera(@PathVariable Long branchId, @RequestBody List<CameraRequestDTO> cameraRequestDTO) {
        List<HardwareDetailDTO> cameraList = cameraRequestDTO.stream().map(camera -> cameraService.saveCamera(branchId, camera)).toList();
        return ResponseEntity.ok().body(cameraList);
    }

    /*
    @GetMapping("/{branchId}/cameras")
    public ResponseEntity<List<CameraDetailDTO>> getCameraDTOList (@PathVariable Long branchId){
        List<CameraDetailDTO> cameraDetailDTOList = cameraService.getCameraDTOList(branchId);
        return ResponseEntity.ok().body(cameraDetailDTOList);
    }

    @PostMapping("/{branchId}/cameras")
    public ResponseEntity<CameraDetailDTO> saveCamera(@PathVariable Long branchId, @RequestBody CameraDetailDTO cameraDetailDTO) {
        CameraDetailDTO savedCameraDetailDTO = cameraService.saveCamera(branchId, cameraDetailDTO);
        return ResponseEntity.ok().body(savedCameraDetailDTO);
    }

    @PutMapping("/{branchId}/cameras/{cameraId}")
    public ResponseEntity<CameraDetailDTO> editCamera(@PathVariable Long branchId, @PathVariable Long cameraId, @RequestBody CameraDetailDTO cameraDetailDTO) {
        CameraDetailDTO updatedCameraDetailDTO = cameraService.editCamera(branchId, cameraId, cameraDetailDTO);
        return ResponseEntity.ok().body(updatedCameraDetailDTO);
    }
 */
}
