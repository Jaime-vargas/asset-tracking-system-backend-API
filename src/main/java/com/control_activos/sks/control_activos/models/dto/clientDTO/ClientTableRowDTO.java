package com.control_activos.sks.control_activos.models.dto.clientDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class ClientTableRowDTO {
    Long id;
    String name;
    Long branches;
    Long totalHardware;
}
