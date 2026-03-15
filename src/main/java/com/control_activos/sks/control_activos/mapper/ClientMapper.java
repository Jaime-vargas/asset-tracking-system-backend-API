package com.control_activos.sks.control_activos.mapper.clientMapper;

import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientDashboardDTO;
import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientTableDTO;
import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import com.control_activos.sks.control_activos.models.entity.Client;

public class ClientMapper {

    public static ClientDashboardDTO toClientDashboardDTO(Client client) {
        return new ClientDashboardDTO(
            client.getId(),
            client.getName()
        );
    }

    public static ClientTableDTO toClientTableDTO(Client client) {
        Long branches = (long) client.getBranches().size();
        Long totalHardware = client.getBranches().stream()
                .mapToLong(branch -> branch.getHardware()
                        .size()).sum();

        return new ClientTableDTO(
                client.getId(),
                client.getName(),
                branches,
                totalHardware,
                client.getBranches().stream().flatMap(branch -> branch.getHardware().stream())
                        .flatMap(hardware -> hardware.getReports().stream())
                        .filter(report -> Boolean.TRUE.equals(report.getActive()))
                        .map(report -> new ReportCountDTO(client.getId(), report.getId(), report.getDueDate()))
                        .toList()
        );
    }
}
