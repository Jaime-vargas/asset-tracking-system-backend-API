package com.control_activos.sks.control_activos.models.dto.hardwareDTO;

import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportHistoryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class HardwareDetailDTO {
    private Long id;
    private String type;
    private String model;
    private String serialNumber;
    private String location;
    private String name;
    private String lastMaintenanceDate;
    private List<ReportHistoryDTO> recentActiveReports;

    public HardwareDetailDTO(long id, String type, String model, String serialNumber, String location, String name, String lastMaintenanceDate) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.serialNumber = serialNumber;
        this.location = location;
        this.name = name;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }
}
