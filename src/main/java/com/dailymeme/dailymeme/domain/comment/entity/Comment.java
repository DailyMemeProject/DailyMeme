package com.dailymeme.dailymeme.domain.comment.entity;

import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Table(name = "`comment`")
@SQLDelete(sql = "UPDATE Comment SET deleted = true WHERE id = ?") // DELETE 요청 시 이 쿼리 실행
@SQLRestriction("deleted = false")
@DynamicUpdate
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_id", nullable = false)
//    private Post post;

    private String comment ;

    private boolean deleted = Boolean.FALSE;

    public Comment() {}

//    public Comment(User user, Post post, String comment) {
//        this.Post = post;
//        this.user = user;
//        this.comment = comment;
//    }
//
//    public void update(String newContent) {
//        this.content = newContent;
//    }
}
