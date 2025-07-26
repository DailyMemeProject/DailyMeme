package com.dailymeme.dailymeme.domain.post.dto.delete;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDeleteResponseDto {
    private String message;
}