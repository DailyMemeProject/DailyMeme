package com.dailymeme.dailymeme.domain.post.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostListResponseDto {
    private String title;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
