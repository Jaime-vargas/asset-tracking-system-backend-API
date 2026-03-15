package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.enums.DuplicateResourceExceptionEnum;
import com.control_activos.sks.control_activos.enums.OperationNotAllowedExceptionEnum;
import com.control_activos.sks.control_activos.enums.ResourceNotFoundExceptionEnum;
import com.control_activos.sks.control_activos.exception.DuplicatedResourceException;
import com.control_activos.sks.control_activos.exception.OperationNotAllowedException;
import com.control_activos.sks.control_activos.exception.ResourceNotFoundException;
import com.control_activos.sks.control_activos.mapper.Mapper;
import com.control_activos.sks.control_activos.models.dto.CameraDTO;
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

    public List<CameraDTO> getCameraDTOList (Long sucursalId){
        List<Camera> cameraList = cameraRepository.findByBranchId(sucursalId);
        return cameraList.stream().map(Mapper::entityToDTO).toList();

    }
    @Transactional
    public CameraDTO saveCamera(Long branchId, CameraDTO cameraDTO) {
        formatDataValidation(cameraDTO);
        Branch branch = branchService.findSucursalById(branchId);
        validateDuplicateData(branch.getId(), cameraDTO, null);
        Camera camera = new Camera();
        setDataToEntity(branch, camera, cameraDTO);
        camera = cameraRepository.save(camera);
        return Mapper.entityToDTO(camera);
    }

    @Transactional
    public CameraDTO editCamera(Long branchId, Long cameraId, CameraDTO cameraDTO) {
        formatDataValidation(cameraDTO);
        Camera camera = findCameraById(cameraId);
        Branch branch = branchService.findSucursalById(branchId);
        if (!camera.getBranch().getId().equals(branchId)) {
            throw new OperationNotAllowedException(OperationNotAllowedExceptionEnum.CAMERA_NOT_BELONG_TO_SUCURSAL.getMessage());
        }
        validateDuplicateData(branch.getId(), cameraDTO, camera.getId());
        setDataToEntity(branch, camera, cameraDTO);
        camera = cameraRepository.save(camera);
        return Mapper.entityToDTO(camera);
    }

    public void setDataToEntity(Branch branch, Camera camera, CameraDTO cameraDTO) {
        camera.setName(cameraDTO.getName());
        camera.setSerialNumber(cameraDTO.getSerialNumber());
        camera.setModel(cameraDTO.getModel());
        camera.setLocation(cameraDTO.getLocation());
        camera.setBranch(branch);
        camera.setCameraId(cameraDTO.getCameraId());
        camera.setMacAddress(cameraDTO.getMacAddress());
        camera.setIpAddress(cameraDTO.getIpAddress());
    }

    public Camera findCameraById(Long cameraId) {
        return cameraRepository.findById(cameraId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundExceptionEnum.CAMERA_NOT_FOUND.getMessage()));
    }

    public void formatDataValidation(CameraDTO cameraDTO) {
        cameraDTO.setMacAddress(formatDataValidationService.validateMacAddressFormat(cameraDTO.getMacAddress()));
        cameraDTO.setIpAddress(formatDataValidationService.validateIpAddressFormat(cameraDTO.getIpAddress()));
    }

    public void validateDuplicateData(Long branchId, CameraDTO cameraDTO, Long currentCameraId) {
        if (cameraRepository.existsByCameraIdAndBranchIdAndIdNot(cameraDTO.getCameraId(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_ID.build(cameraDTO.getCameraId()));
        }
        if (cameraRepository.existsByNameAndBranchIdAndIdNot(cameraDTO.getName(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_NAME.build(cameraDTO.getName()));
        }
        if (cameraRepository.existsBySerialNumberAndBranchIdAndIdNot(cameraDTO.getSerialNumber(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_SERIAL_NUMBER.build(cameraDTO.getSerialNumber()));
        }
        if (cameraRepository.existsByMacAddressAndBranchIdAndIdNot(cameraDTO.getMacAddress(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_MAC_ADDRESS.build(cameraDTO.getMacAddress()));
        }
        if (cameraRepository.existsByIpAddressAndBranchIdAndIdNot(cameraDTO.getIpAddress(), branchId, currentCameraId)) {
            throw new DuplicatedResourceException(DuplicateResourceExceptionEnum
                    .DUPLICATE_CAMERA_IP_ADDRESS.build(cameraDTO.getIpAddress()));
        }
    }
}


