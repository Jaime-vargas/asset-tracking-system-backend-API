package com.control_activos.sks.control_activos.models.dto.commentDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String text;
    private String username;
    private OffsetDateTime createdAt;
}
