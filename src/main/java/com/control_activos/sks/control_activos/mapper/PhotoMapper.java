package com.control_activos.sks.control_activos.mapper;

import com.control_activos.sks.control_activos.models.dto.PhotoDTO;
import com.control_activos.sks.control_activos.models.entity.Photo;

public class PhotoMapper {
    public static PhotoDTO toPhotoDTO(Photo photo) {
        return new PhotoDTO(
                photo.getId(),
                photo.getFilename(),
                photo.getContentType(),
                photo.getPublicPath(),
                photo.getUploadedAt(),
                photo.getReport().getId()
        );
    }
}
