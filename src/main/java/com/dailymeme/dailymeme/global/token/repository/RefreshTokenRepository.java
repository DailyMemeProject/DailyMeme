package com.dailymeme.dailymeme.global.token.repository;

import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.global.entity.RefreshToken;
import com.dailymeme.dailymeme.global.exception.ExceptionType;
import com.dailymeme.dailymeme.global.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    Optional<RefreshToken> findByUser(User user);

    default RefreshToken deleteRefreshTokenById(Long id) {
        return findById(id).orElseThrow(()-> new NotFoundException(ExceptionType.USER_NOT_FOUND));
    }


    default RefreshToken findByUserOrElseThrow(User user) {
        return findByUser(user).orElseThrow(()-> new NotFoundException(ExceptionType.USER_NOT_FOUND));
    }

}