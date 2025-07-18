package com.dailymeme.dailymeme.domain.post.controller;

import com.dailymeme.dailymeme.domain.post.dto.create.PostCreateRequestDto;
import com.dailymeme.dailymeme.domain.post.dto.create.PostCreateResponseDto;
import com.dailymeme.dailymeme.domain.post.service.PostService;
import com.dailymeme.dailymeme.global.auth.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시물 작성
    @PostMapping
    public ResponseEntity<PostCreateResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(postService.createPost(userDetails.getUser().getId(), requestDto),HttpStatus.CREATED);
    }

    //게시물 전체 조회

    //게시물 단건 조회

    //게시물 수정

    //게시물 삭제




}
