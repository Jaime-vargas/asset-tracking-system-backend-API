package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.models.dto.commentDTO.CommentDTO;
import com.control_activos.sks.control_activos.models.dto.commentDTO.CommentRequestDTO;
import com.control_activos.sks.control_activos.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable long reportId, @PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        commentDTO = commentService.updateComment(reportId, commentId, commentDTO);
        return   ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }
}
