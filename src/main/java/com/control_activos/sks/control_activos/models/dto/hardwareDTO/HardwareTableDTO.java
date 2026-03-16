package com.control_activos.sks.control_activos.models.dto.hardwareDTO;

import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class HardwareTableDTO {
    Long id;
    String type;
    String name;
    String model;
    String serialNumber;
    String lastMaintenanceDate;
    List<ReportCountDTO> reportsActive;

    public HardwareTableDTO(Long id, String type, String name, String model, String serialNumber, String lastMaintenanceDate) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.model = model;
        this.serialNumber = serialNumber;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }
}
