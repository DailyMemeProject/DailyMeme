package com.dailymeme.dailymeme.domain.post.repository;

import com.dailymeme.dailymeme.domain.post.dto.info.PostListResponseDto;
import com.dailymeme.dailymeme.domain.post.dto.info.PostResponseDto;
import com.dailymeme.dailymeme.domain.post.entity.Post;
import com.dailymeme.dailymeme.global.exception.ExceptionType;
import com.dailymeme.dailymeme.global.exception.NotFoundPostOrNotMatchUserException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("select new com.dailymeme.dailymeme.domain.post.dto.info.PostListResponseDto(p.title, p.user.userName, p.createdAt, p.modifiedAt) " +
            "from Post p join p.user u " +
            "where p.postStatus ='ACTIVE' ")
    List<PostListResponseDto> findPostByPostStatus();

    @Query("select new com.dailymeme.dailymeme.domain.post.dto.info.PostResponseDto(p.title, p.user.userName, p.content, p.createdAt, p.modifiedAt)" +
            "from Post p join p.user u " +
            "where p.postStatus = com.dailymeme.dailymeme.domain.post.enums.PostStatus.ACTIVE and p.id = :postId ")
    PostResponseDto getPostByIdAndPostStatus(Long postId);

    @Query("SELECT p FROM Post p WHERE p.id = :postId AND p.user.id = :userId AND p.postStatus = 'ACTIVE'")
    Optional<Post> findActiveByIdAndUserId(Long postId, Long userId);

    default Post findByIdAndUserIdOrElseThrow(Long postId, Long userId) {
        return findActiveByIdAndUserId(postId, userId)
                .orElseThrow(() -> new NotFoundPostOrNotMatchUserException(ExceptionType.NOT_FOUND_POST_OR_NOT_MATCH_USER));
    }
}
