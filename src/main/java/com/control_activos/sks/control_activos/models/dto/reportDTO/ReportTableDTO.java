package com.control_activos.sks.control_activos.models.dto.reportDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class ReportTableDTO {
    private Long id;
    private String title;
    private Long clientId;
    private String clientName;
    private Long branchId;
    private String branchName;
    private Long hardwareId;
    private String hardwareName;
    private String priority;
    private OffsetDateTime createdDate;
    private OffsetDateTime dueDate;
    private Boolean status;
}
