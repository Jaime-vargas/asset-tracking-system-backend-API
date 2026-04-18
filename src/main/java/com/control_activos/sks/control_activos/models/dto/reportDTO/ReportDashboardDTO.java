package com.control_activos.sks.control_activos.models.dto.reportDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportDashboardDTO {
    private Long id;
    private String title;
    private String priority;
    private String dueDate;
    private String status;
}
