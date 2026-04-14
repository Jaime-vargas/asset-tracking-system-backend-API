package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.PhotoDTO;
import com.control_activos.sks.control_activos.services.FilesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/photos")
public class PhotoController {

    private final FilesService filesService;

    public PhotoController(FilesService filesService) {
        this.filesService = filesService;
     }

}
