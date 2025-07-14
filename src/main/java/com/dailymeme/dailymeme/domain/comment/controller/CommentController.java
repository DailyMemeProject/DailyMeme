package com.dailymeme.dailymeme.domain.comment.controller;

import com.dailymeme.dailymeme.domain.comment.dto.CommentRequestDto;
import com.dailymeme.dailymeme.domain.comment.dto.CommentResponseDto;
import com.dailymeme.dailymeme.domain.comment.entity.Comment;
import com.dailymeme.dailymeme.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

//    /*
//    댓글 작성
//     */
//    @PostMapping
//    public ResponseEntity<CommentResponseDto> createComment(
//            @PathVariable Long postId,
//            @Valid @RequestBody CommentRequestDto commentRequestDto
//    ) {
//        CommentResponseDto commentResponseDto = commentService.createComment(postId, commentRequestDto);
//        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
//    }
//
//    /*
//    댓글 수정
//     */
//    @PutMapping
//    public ResponseEntity<CommentResponseDto> updateComment(
//            @PathVariable Long postId,
//            @PathVariable Long commentId,
//            @Valid @RequestBody CommentRequestDto commentRequestDto
//    ) {
//        CommentResponseDto commentResponseDto = commentService.updateComment(postId, commentId, commentRequestDto);
//        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
//    }
//
//    /*
//    댓글 조회
//     */
//    @GetMapping
//    public ResponseEntity<List<CommentResponseDto>> findAllComment(
//            @PathVariable Long postId
//    ) {
//        List<CommentResponseDto> commentResponseDto = commentService.findAllComment(postId);
//        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
//    }
//
//    /*
//    댓글 삭제
//     */
//    @DeleteMapping
//    public ResponseEntity<CommentResponseDto> deleteComment(
//            @PathVariable Long postId,
//            @PathVariable Long commentId
//    ) {
//        CommentResponseDto commentResponseDto = commentService.deleteComment(postId, commentId);
//        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
//    }
}
