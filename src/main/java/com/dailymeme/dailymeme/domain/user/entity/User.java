package com.dailymeme.dailymeme.domain.user.entity;


import com.dailymeme.dailymeme.domain.user.enums.UserAuth;
import com.dailymeme.dailymeme.domain.user.enums.UserStatus;
import com.dailymeme.dailymeme.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserAuth userAuth = UserAuth.USER; //SoftDelete

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.ACTIVE;

    private LocalDateTime deletedAt;

    @Column(nullable = false)
    private Boolean mail_send_agree;

    public User() {

    }

    @Builder
    public User(String userName, String email, String password, Boolean mail_send_agree) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.mail_send_agree = mail_send_agree;
    }
    public Boolean isActive() {
        return userStatus.equals(UserStatus.ACTIVE);
    }




}
