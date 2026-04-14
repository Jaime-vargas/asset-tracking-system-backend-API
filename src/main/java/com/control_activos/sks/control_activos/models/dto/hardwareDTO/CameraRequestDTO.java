package com.control_activos.sks.control_activos.models.dto.hardwareDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CameraRequestDTO {
    String name;
    String serialNumber;
    String model;
    String location;
    String cameraId;
    String macAddress;
    String ipAddress;
}
