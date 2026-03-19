package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareDetailDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareTableDTO;
import com.control_activos.sks.control_activos.models.entity.Hardware;
import com.control_activos.sks.control_activos.services.HardwareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients/{clientId}/branches/{branchId}/hardware")
public class HardwareController {

    private final HardwareService hardwareService;

    public  HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public ResponseEntity<List<HardwareTableDTO>> getHardwareList(@PathVariable Long clientId, @PathVariable Long branchId) {
        List<HardwareTableDTO> hardwareList = hardwareService.getHardwareByBranch(clientId, branchId);
        return ResponseEntity.ok().body(hardwareList);
    }

    @GetMapping("/{hardwareID}")
    public ResponseEntity<HardwareDetailDTO> getHardwareDetailById(@PathVariable Long clientId, @PathVariable Long branchId, @PathVariable Long hardwareID) {
        HardwareDetailDTO hardwareDetailDTO = hardwareService.getHardwareDetailById(clientId, branchId, hardwareID);
        return ResponseEntity.ok().body(hardwareDetailDTO);
    }
}
