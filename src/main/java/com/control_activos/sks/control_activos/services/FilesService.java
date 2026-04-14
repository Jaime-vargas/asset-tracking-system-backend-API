package com.control_activos.sks.control_activos.services;

import com.control_activos.sks.control_activos.enums.FileEnum;
import com.control_activos.sks.control_activos.exception.FileException;
import com.control_activos.sks.control_activos.models.entity.Photo;
import com.control_activos.sks.control_activos.models.entity.Report;
import com.control_activos.sks.control_activos.repository.PhotoRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FilesService {

    private final Path filePath = Path.of(
            System.getProperty("user.home"),
            "IdeaProjects",
            "asset-tracking-system-frontend",
            "public","uploads"
    );

    private final PhotoRepository photoRepository;
    private final ReportService reportService;
    public FilesService(PhotoRepository photoRepository, ReportService reportService) {
        this.photoRepository = photoRepository;
        this.reportService = reportService;
    }

    @Transactional
    public void uploadPhotos(Long reportId, List<MultipartFile> files) {
        List<String>errorsOnFileUpload = new ArrayList<>(List.of());
        Report report = reportService.findReportById(reportId);
        Path uploadPath = Path.of(this.filePath + "/reports/" + reportId);
        createDirectoriesIfNotExist(uploadPath);
        files.forEach(file -> {
            ValidateImageFile(file);
            Path storePath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            if(Files.exists(storePath)) {
                errorsOnFileUpload.add("File with name " + file.getOriginalFilename() + " already exists.");
            }else {
                saveFileToPath(file, storePath);
                Photo photo = new Photo(
                        file.getOriginalFilename(),
                        file.getContentType(),
                        file.getSize(),
                        storePath.toString(),
                        "/uploads/reports/" + reportId + "/" + file.getOriginalFilename(),
                        OffsetDateTime.now(),
                        report);
                report.getPhotos().add(photo);
                photoRepository.save(photo);
            }
        });
        if (!errorsOnFileUpload.isEmpty()) {
            throw new FileException(String.join(" ", errorsOnFileUpload));
        }
    }

    private void ValidateImageFile(MultipartFile file) {
        if (file.isEmpty() || !Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
            throw new FileException(FileEnum.IMAGE_FORMAT_ERROR.getMessage());
        }
    }

    private void saveFileToPath(MultipartFile file, Path storePath) {
        try {
            file.transferTo(storePath);
        } catch (IOException e) {
            throw new FileException(FileEnum.SAVE_ERROR.getMessage());
        }
    }

    private void createDirectoriesIfNotExist(Path savePath){
        try {
            Files.createDirectories(savePath);
        }catch (IOException e) {
            throw new FileException(FileEnum.CREATION_ERROR.getMessage());
        }
    }
}
