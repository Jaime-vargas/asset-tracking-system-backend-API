package com.control_activos.sks.control_activos.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Camera extends Hardware {
    private String cameraId;
    private String macAddress;
    private String ipAddress;
    private String idf;
    private String username;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "view_from_photo_id")
    private Photo viewFromCameraPhoto;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "view_to_photo_id")
    private Photo viewToCameraPhoto;
}
