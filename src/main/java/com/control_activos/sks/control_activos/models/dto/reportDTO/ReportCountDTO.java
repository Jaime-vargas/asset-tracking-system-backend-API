package com.control_activos.sks.control_activos.models.dto.reportDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class ReportCountDTO {

    Long clientId;
    Long reportId;
    OffsetDateTime dueDate;
}
