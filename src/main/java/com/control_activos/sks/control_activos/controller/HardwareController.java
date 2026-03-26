package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareDetailDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareTableDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportTableDTO;
import com.control_activos.sks.control_activos.services.HardwareService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hardware")
public class HardwareController {

    private final HardwareService hardwareService;

    public  HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping() // #TODO: refactor route for better handling of hardware related endpoints
    public ResponseEntity<List<HardwareTableDTO>> getAllHardwareList(){
        List<HardwareTableDTO> hardwareList = hardwareService.getAllHardwareList();
        return ResponseEntity.ok().body(hardwareList);
    }

    @GetMapping("/{hardwareID}")
    public ResponseEntity<HardwareDetailDTO> getHardwareById(@PathVariable Long hardwareID) {
        HardwareDetailDTO hardwareDetailDTO = hardwareService.getHardwareById(hardwareID);
        return ResponseEntity.ok().body(hardwareDetailDTO);
    }

    @GetMapping("/{hardwareID}/reports")
    public ResponseEntity<List<ReportTableDTO>> getReportsByHardwareId(@PathVariable Long hardwareID) {
        List<ReportTableDTO> reports = hardwareService.getReportsByHardwareId(hardwareID);
        return ResponseEntity.ok().body(reports);
    }

}
