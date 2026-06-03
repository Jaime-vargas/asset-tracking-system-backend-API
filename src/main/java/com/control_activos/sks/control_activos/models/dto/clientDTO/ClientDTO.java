package com.control_activos.sks.control_activos.models.dto.clientDTO;

import com.control_activos.sks.control_activos.models.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private Photo photo;
}
