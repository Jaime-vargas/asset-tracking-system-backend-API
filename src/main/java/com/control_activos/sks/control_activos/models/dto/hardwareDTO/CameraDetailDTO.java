package com.control_activos.sks.control_activos.models.dto.hardwareDTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CameraDetailDTO extends HardwareDetailDTO {

    private String cameraId;
    private String macAddress;
    private String ipAddress;

    public CameraDetailDTO(Long id, String type, String model, String serialNumber, String location, String name, String lastMaintenanceDate
    , String cameraId, String macAddress, String ipAddress) {
        super(id, type, model, serialNumber, location, name, lastMaintenanceDate);
        this.cameraId = cameraId;
        this.macAddress = macAddress;
        this.ipAddress = ipAddress;

    }
}
