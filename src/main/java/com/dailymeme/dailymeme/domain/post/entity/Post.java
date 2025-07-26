package com.dailymeme.dailymeme.domain.post.entity;

import com.dailymeme.dailymeme.domain.comment.entity.Comment;
import com.dailymeme.dailymeme.domain.post.enums.PostStatus;
import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "post")
@DynamicInsert
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus =  PostStatus.ACTIVE;

    //댓글 연관관계
//    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<Comment> comments = new ArrayList<>();


    public Post() {}

    @Builder
    public Post(User user, String title, String content,  PostStatus postStatus) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.postStatus = postStatus;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void deletePost(PostStatus postStatus) {
        this.postStatus = postStatus;
    }
}
