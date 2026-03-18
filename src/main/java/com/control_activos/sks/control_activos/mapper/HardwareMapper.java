package com.control_activos.sks.control_activos.mapper;

import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareTableDTO;
import com.control_activos.sks.control_activos.models.entity.Hardware;

import java.util.Optional;

public class HardwareMapper {

    public static HardwareTableDTO toHardwareTableDTO(Hardware hardware) {
        return new HardwareTableDTO(
            hardware.getId(),
            hardware.getClass().getSimpleName(),
            hardware.getName(),
            hardware.getModel(),
            hardware.getSerialNumber(),
            hardware.getLocation(),
            Optional.ofNullable(hardware.getLastMaintenanceDate()).map(Object::toString).orElse("N/A")
        );
    }
}
