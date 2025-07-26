package com.dailymeme.dailymeme.domain.post.service;

import com.dailymeme.dailymeme.domain.post.dto.create.PostCreateRequestDto;
import com.dailymeme.dailymeme.domain.post.dto.create.PostCreateResponseDto;
import com.dailymeme.dailymeme.domain.post.dto.delete.PostDeleteResponseDto;
import com.dailymeme.dailymeme.domain.post.dto.info.PostListResponseDto;
import com.dailymeme.dailymeme.domain.post.dto.info.PostResponseDto;
import com.dailymeme.dailymeme.domain.post.dto.update.PostUpdateRequestDto;
import com.dailymeme.dailymeme.domain.post.entity.Post;
import com.dailymeme.dailymeme.domain.post.enums.PostStatus;
import com.dailymeme.dailymeme.domain.post.repository.PostRepository;
import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.domain.user.repository.UserRepository;
import com.dailymeme.dailymeme.global.exception.ExceptionType;
import com.dailymeme.dailymeme.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //게시글 생성
    @Transactional
    public PostCreateResponseDto createPost(Long userId, PostCreateRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException(ExceptionType.USER_NOT_FOUND));

        Post post = Post.builder().title(requestDto.getTitle()).content(requestDto.getContent()).user(user).postStatus(com.dailymeme.dailymeme.domain.post.enums.PostStatus.ACTIVE).build();

        postRepository.save(post);

        return PostCreateResponseDto.builder().id(post.getId()).username(user.getUserName())
                .title(post.getTitle()).content(post.getContent()).createdAt(post.getCreatedAt())
                .updatedAt(post.getModifiedAt()).build();
    }

    //게시물 전체 조회
    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllPosts() {
        return postRepository.findPostByPostStatus();
    }

    //게시물 단건 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPostById(Long postId) {
        return postRepository.getPostByIdAndPostStatus(postId);
    }

    //게시물 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, Long userId, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findByIdAndUserIdOrElseThrow(postId, userId);
        post.updatePost(requestDto.getTitle(), requestDto.getContent());

        return PostResponseDto.builder()
                .title(post.getTitle())
                .userName(post.getUser().getUserName())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getModifiedAt()).build();
    }

    //게시물 삭제
    @Transactional
    public PostDeleteResponseDto deletePost(Long postId, Long userId) {
        Post post = postRepository.findByIdAndUserIdOrElseThrow(postId, userId);
        post.deletePost(PostStatus.DELETED);
        return PostDeleteResponseDto.builder().message("정상적으로 삭제되었습니다.").build();
    }


}
