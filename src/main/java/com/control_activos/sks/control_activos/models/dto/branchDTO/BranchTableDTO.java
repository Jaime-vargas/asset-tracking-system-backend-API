package com.control_activos.sks.control_activos.models.dto.branchDTO;

import com.control_activos.sks.control_activos.models.dto.reportDTO.ReportCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchTableDTO {
    Long id;
    String name;
    Long totalHardware;
    List<ReportCountDTO> reportsActive;
}
