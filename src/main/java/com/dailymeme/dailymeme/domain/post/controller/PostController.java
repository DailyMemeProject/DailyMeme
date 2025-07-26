package com.dailymeme.dailymeme.domain.post.controller;

import com.dailymeme.dailymeme.domain.post.dto.create.PostCreateRequestDto;
import com.dailymeme.dailymeme.domain.post.dto.create.PostCreateResponseDto;
import com.dailymeme.dailymeme.domain.post.dto.delete.PostDeleteResponseDto;
import com.dailymeme.dailymeme.domain.post.dto.info.PostListResponseDto;
import com.dailymeme.dailymeme.domain.post.dto.info.PostResponseDto;
import com.dailymeme.dailymeme.domain.post.dto.update.PostUpdateRequestDto;
import com.dailymeme.dailymeme.domain.post.service.PostService;
import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.global.auth.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시물 작성
    @PostMapping
    public ResponseEntity<PostCreateResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto requestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(postService.createPost(userDetails.getUser().getId(), requestDto),HttpStatus.CREATED);
    }

    //게시물 전체 조회
    @GetMapping
    public ResponseEntity<List<PostListResponseDto>> getAllPost() {
        return new ResponseEntity<>(postService.findAllPosts(), HttpStatus.OK);
    }

    //게시물 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {

        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    //게시물 수정
    @PatchMapping("/update/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequestDto requestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        PostResponseDto postResponseDto = postService.updatePost(postId, user.getId(), requestDto);
        return new ResponseEntity<>(postResponseDto, HttpStatus.OK);
    }

    //게시물 삭제
    @PatchMapping("/delete/{postId}")
    public ResponseEntity<PostDeleteResponseDto> deletePost(@PathVariable Long postId,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        PostDeleteResponseDto responseDto = postService.deletePost(postId, user.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }





}
