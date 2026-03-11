package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.DashboardDataDTO;
import com.control_activos.sks.control_activos.services.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardDataDTO> getDashboardData(){
        DashboardDataDTO dashboardDataDTO = dashboardService.getDashboardData();
        System.out.println(dashboardDataDTO);
        return ResponseEntity.ok(dashboardDataDTO);
    }
}
