package com.dailymeme.dailymeme.domain.post.service;

import com.dailymeme.dailymeme.domain.post.dto.create.PostCreateRequestDto;
import com.dailymeme.dailymeme.domain.post.dto.create.PostCreateResponseDto;
import com.dailymeme.dailymeme.domain.post.entity.Post;
import com.dailymeme.dailymeme.domain.post.repository.PostRepository;
import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.domain.user.repository.UserRepository;
import com.dailymeme.dailymeme.global.exception.ExceptionType;
import com.dailymeme.dailymeme.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    //게시글 생성
        @Transactional
        public PostCreateResponseDto createPost(Long userId, PostCreateRequestDto requestDto) {
            User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException(ExceptionType.USER_NOT_FOUND));

            Post post = Post.builder().title(requestDto.getTitle()).content(requestDto.getContent()).user(user).build();

            postRepository.save(post);

            return PostCreateResponseDto.builder().id(post.getId()).username(user.getUserName()).title(post.getTitle()).content(post.getContent()).createdAt(post.getCreatedAt()).updatedAt(post.getModifiedAt()).build();
        }

}
