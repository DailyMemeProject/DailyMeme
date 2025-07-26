package com.dailymeme.dailymeme.domain.post.dto.info;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private String title;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostResponseDto(String title, String userName, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


}

