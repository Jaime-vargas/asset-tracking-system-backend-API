package com.control_activos.sks.control_activos.models.dto.clientDTO;

import com.control_activos.sks.control_activos.models.dto.PhotoDTO;
import com.control_activos.sks.control_activos.models.entity.Photo;
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
    Photo photo;
}
