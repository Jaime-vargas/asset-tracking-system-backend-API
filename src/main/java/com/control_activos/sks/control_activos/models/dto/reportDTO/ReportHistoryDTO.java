package com.control_activos.sks.control_activos.models.dto.reportDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportHistoryDTO {
    private Long id;
    private String dueDate;
    private String priority;
    private String title;
    private String reportedBy;
}
