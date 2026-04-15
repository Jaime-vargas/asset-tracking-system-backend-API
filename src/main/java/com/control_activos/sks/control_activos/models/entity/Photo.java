package com.control_activos.sks.control_activos.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String contentType;
    private Long size;
    private String filePath;
    private OffsetDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    public  Photo(String filename, String contentType, Long size, String filePath, OffsetDateTime uploadedAt, Report report) {
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
        this.report = report;
    }
}
