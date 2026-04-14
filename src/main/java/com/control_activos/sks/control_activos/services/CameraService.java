package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.enums.DuplicateResourceExceptionEnum;
import com.control_activos.sks.control_activos.enums.OperationNotAllowedExceptionEnum;
import com.control_activos.sks.control_activos.enums.ResourceNotFoundExceptionEnum;
import com.control_activos.sks.control_activos.exception.DuplicatedResourceException;
import com.control_activos.sks.control_activos.exception.OperationNotAllowedException;
import com.control_activos.sks.control_activos.exception.ResourceNotFoundException;
import com.control_activos.sks.control_activos.mapper.CameraMapper;
import com.control_activos.sks.control_activos.mapper.HardwareMapper;
import com.control_activos.sks.control_activos.mapper.Mapper;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.CameraDetailDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.CameraRequestDTO;
import com.control_activos.sks.control_activos.models.dto.hardwareDTO.HardwareDetailDTO;
import com.control_activos.sks.control_activos.models.entity.Camera;
import com.control_activos.sks.control_activos.models.entity.Branch;
import com.control_activos.sks.control_activos.repository.CameraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {

    private final CameraRepository cameraRepository;
    private final FormatDataValidationService formatDataValidationService;
    private final BranchService branchService;
    public CameraService(CameraRepository cameraRepository, FormatDataValidationService formatDataValidationService, BranchService branchService) {
        this.cameraRepository = cameraRepository;
        this.formatDataValidationService = formatDataValidationService;
        this.branchService = branchService;
    }

    @Transactional
    public HardwareDetailDTO saveCamera(Long branchId, CameraRequestDTO cameraRequestDTO) {
        Branch branch = branchService.findBranchById(branchId);
        formatDataValidation(cameraRequestDTO);
        Camera camera = CameraMapper.toCameraEntity(cameraRequestDTO, branch);
        camera = cameraRepository.save(camera);
        return HardwareMapper.hardwareDetailDTO(camera);
    }

    public void formatDataValidation(CameraRequestDTO cameraRequestDTO) {
        cameraRequestDTO.setMacAddress(formatDataValidationService.validateMacAddressFormat(cameraRequestDTO.getMacAddress()));
        cameraRequestDTO.setIpAddress(formatDataValidationService.validateIpAddressFormat(cameraRequestDTO.getIpAddress()));
    }

    public Camera findCameraById(Long cameraId) {
        return cameraRepository.findById(cameraId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundExceptionEnum.CAMERA_NOT_FOUND.getMessage()));
    }

    /*    IMPLEMENT METHOD TO VALIDATE DUPLICATE DATA IN CAMERA ENTITY, THIS METHOD WILL BE USED IN BOTH CREATE AND EDIT CAMERA, IN CREATE CAMERA THE CURRENT CAMERA ID WILL BE NULL, IN EDIT CAMERA THE CURRENT CAMERA ID WILL BE THE ID OF THE CAMERA BEING EDITED, THIS WAY WE CAN EXCLUDE THE CURRENT CAMERA FROM THE DUPLICATE CHECKS

    public void validateDuplicateData(Long branchId, CameraRequestDTO cameraRequestDTO, Long currentCameraId) {
        if (cameraRepository.existsByCameraIdAndBranchIdAndIdNot(cameraRequestDTO.getCameraId(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_ID.build(cameraRequestDTO.getCameraId()));
        }
        if (cameraRepository.existsByNameAndBranchIdAndIdNot(cameraRequestDTO.getName(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_NAME.build(cameraRequestDTO.getName()));
        }
        if (cameraRepository.existsBySerialNumberAndBranchIdAndIdNot(cameraRequestDTO.getSerialNumber(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_SERIAL_NUMBER.build(cameraRequestDTO.getSerialNumber()));
        }
        if (cameraRepository.existsByMacAddressAndBranchIdAndIdNot(cameraRequestDTO.getMacAddress(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_MAC_ADDRESS.build(cameraRequestDTO.getMacAddress()));
        }
        if (cameraRepository.existsByIpAddressAndBranchIdAndIdNot(cameraRequestDTO.getIpAddress(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_IP_ADDRESS.build(cameraRequestDTO.getIpAddress()));
        }
    }

    public List<CameraDetailDTO> getCameraDTOList (Long sucursalId){
        List<Camera> cameraList = cameraRepository.findByBranchId(sucursalId);
        return cameraList.stream().map(Mapper::entityToDTO).toList();

    }



    @Transactional
    public CameraDetailDTO editCamera(Long branchId, Long cameraId, CameraDetailDTO cameraDetailDTO) {
        formatDataValidation(cameraDetailDTO);
        Camera camera = findCameraById(cameraId);
        Branch branch = branchService.findBranchById(branchId);
        if (!camera.getBranch().getId().equals(branchId)) {
            throw new OperationNotAllowedException(OperationNotAllowedExceptionEnum.CAMERA_NOT_BELONG_TO_SUCURSAL.getMessage());
        }
        validateDuplicateData(branch.getId(), cameraDetailDTO, camera.getId());
        setDataToEntity(branch, camera, cameraDetailDTO);
        camera = cameraRepository.save(camera);
        return Mapper.entityToDTO(camera);
    }

    public void setDataToEntity(Branch branch, Camera camera, CameraDetailDTO cameraDetailDTO) {
        camera.setName(cameraDetailDTO.getName());
        camera.setSerialNumber(cameraDetailDTO.getSerialNumber());
        camera.setModel(cameraDetailDTO.getModel());
        camera.setLocation(cameraDetailDTO.getLocation());
        camera.setBranch(branch);
        camera.setCameraId(cameraDetailDTO.getCameraId());
        camera.setMacAddress(cameraDetailDTO.getMacAddress());
        camera.setIpAddress(cameraDetailDTO.getIpAddress());
    }





      */
}


