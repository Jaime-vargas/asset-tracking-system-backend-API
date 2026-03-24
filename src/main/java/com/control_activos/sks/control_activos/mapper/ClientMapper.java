package com.control_activos.sks.control_activos.mapper;

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
}
