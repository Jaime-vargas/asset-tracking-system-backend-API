package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.models.entity.Hardware;
import com.control_activos.sks.control_activos.repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareService {

    private final HardwareRepository hardwareRepository;

    public HardwareService(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    public List<Hardware> getHardwareList() {
        return hardwareRepository.findAll();
    }
}
