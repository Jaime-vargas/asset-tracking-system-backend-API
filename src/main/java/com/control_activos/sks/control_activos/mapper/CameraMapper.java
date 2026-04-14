package com.control_activos.sks.control_activos.mapper;

import com.control_activos.sks.control_activos.models.dto.hardwareDTO.CameraRequestDTO;
import com.control_activos.sks.control_activos.models.entity.Branch;
import com.control_activos.sks.control_activos.models.entity.Camera;

public class CameraMapper {

    public static Camera toCameraEntity(CameraRequestDTO cameraRequestDTO, Branch branch) {
        Camera camera = new Camera();
        camera.setName(cameraRequestDTO.getName());
        camera.setSerialNumber(cameraRequestDTO.getSerialNumber());
        camera.setModel(cameraRequestDTO.getModel());
        camera.setLocation(cameraRequestDTO.getLocation());
        camera.setBranch(branch);
        camera.setCameraId(cameraRequestDTO.getCameraId());
        camera.setMacAddress(cameraRequestDTO.getMacAddress());
        camera.setIpAddress(cameraRequestDTO.getIpAddress());
        return camera;
    }
}
