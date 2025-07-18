package com.dailymeme.dailymeme.domain.post.repository;

import com.dailymeme.dailymeme.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
}
