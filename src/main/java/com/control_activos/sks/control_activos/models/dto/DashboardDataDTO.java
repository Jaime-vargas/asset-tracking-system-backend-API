package com.control_activos.sks.control_activos.models.dto;

import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientDashboardDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportDashboardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
public class DashboardDataDTO {
    Long openReports;
    Long overdueReports;
    Long totalHardware;
    Long totalClients;

    List<ReportDashboardDTO> recentReports;
    List<ClientDashboardDTO> clients;

    Long totalCameras;
    Long totalSwitches;
    Long totalOtherHardware;
}
