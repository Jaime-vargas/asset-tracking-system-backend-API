package com.control_activos.sks.control_activos.models.dto.hardwareDTO;

import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HardwareTableDTO {
    Long id;
    String type;
    String name;
    String model;
    String serialNumber;
    String location;
    String lastMaintenanceDate;
    List<ReportCountDTO> reportsActive;
    String clientName;
    String branchName;

    public HardwareTableDTO(Long id, String type, String name, String model, String serialNumber, String location, String lastMaintenanceDate, String clientName, String branchName) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.model = model;
        this.serialNumber = serialNumber;
        this.location = location;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.clientName = clientName;
        this.branchName = branchName;
    }
}
