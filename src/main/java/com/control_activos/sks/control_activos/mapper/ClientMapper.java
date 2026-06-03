package com.control_activos.sks.control_activos.mapper;

import com.control_activos.sks.control_activos.models.dto.clientDTO.ClientDTO;
import com.control_activos.sks.control_activos.models.entity.Client;

public class ClientMapper {

    public static ClientDTO toClientDTO(Client client) {
        return new ClientDTO(
            client.getId(),
            client.getName(),
            client.getPhoto()
        );
    }
}
