package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.entity.Hardware;
import com.control_activos.sks.control_activos.services.HardwareService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public List<Hardware> getHardwareList() {
        return hardwareService.getHardwareList();
    }
}
