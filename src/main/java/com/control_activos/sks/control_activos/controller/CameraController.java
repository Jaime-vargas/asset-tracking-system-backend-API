package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.enums.CameraPhotoUploads;
import com.control_activos.sks.control_activos.models.entity.Camera;
import com.control_activos.sks.control_activos.services.CameraService;
import com.control_activos.sks.control_activos.services.FilesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/hardware/{hardwareID}/camera")
public class CameraController {

    private final CameraService cameraService;
    private final FilesService filesService;
    public CameraController(CameraService cameraService, FilesService filesService) {
        this.filesService = filesService;
        this.cameraService = cameraService;
    }




    @PostMapping("/photos")
    public ResponseEntity<?> addPhoto(@PathVariable Long hardwareID, @RequestPart("file") MultipartFile file,
                                      @RequestParam CameraPhotoUploads photoType,
                                      @RequestParam(defaultValue = "false") Boolean replaceExisting) {
        filesService.uploadCameraPhoto(hardwareID, file, photoType, replaceExisting);
        return ResponseEntity.noContent().build();
    }

    /*
    @GetMapping("/{branchId}/cameras")
    public ResponseEntity<List<CameraDetailDTO>> getCameraDTOList (@PathVariable Long branchId){
        List<CameraDetailDTO> cameraDetailDTOList = cameraService.getCameraDTOList(branchId);
        return ResponseEntity.ok().body(cameraDetailDTOList);
    }

    @PostMapping("/{branchId}/cameras")
    public ResponseEntity<CameraDetailDTO> saveCamera(@PathVariable Long branchId, @RequestBody CameraDetailDTO cameraDetailDTO) {
        CameraDetailDTO savedCameraDetailDTO = cameraService.saveCamera(branchId, cameraDetailDTO);
        return ResponseEntity.ok().body(savedCameraDetailDTO);
    }

    @PutMapping("/{branchId}/cameras/{cameraId}")
    public ResponseEntity<CameraDetailDTO> editCamera(@PathVariable Long branchId, @PathVariable Long cameraId, @RequestBody CameraDetailDTO cameraDetailDTO) {
        CameraDetailDTO updatedCameraDetailDTO = cameraService.editCamera(branchId, cameraId, cameraDetailDTO);
        return ResponseEntity.ok().body(updatedCameraDetailDTO);
    }
 */
}
