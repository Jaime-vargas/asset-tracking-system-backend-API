package com.control_activos.sks.control_activos.models.dto.reportDTO;

import com.control_activos.sks.control_activos.models.dto.PhotoDTO;
import com.control_activos.sks.control_activos.models.dto.commentDTO.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReportDetailDTO {
    private Long id;
    private String title;
    private String reportDetails;
    private List<PhotoDTO> photos;
    private List<CommentDTO> comments;
    private String status;
    private String hardwareName;
    private String reportedBy;
    private String createdAt;
    private String updatedAt;
    private String closedAt;
    private String dueDate;
    private String priority;
}
