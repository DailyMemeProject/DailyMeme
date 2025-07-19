package com.dailymeme.dailymeme.global.token.tokenservice;

import com.dailymeme.dailymeme.global.token.dto.TokenResponseDto;
import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.global.entity.RefreshToken;
import com.dailymeme.dailymeme.global.jwtutil.JwtUtil;
import com.dailymeme.dailymeme.global.token.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public TokenResponseDto generateAndStoreRefreshToken(User user) {

        String accessToken = jwtUtil.generateAccessToken(user.getId());
        String refreshTokenKey = jwtUtil.generateRefreshToken(user.getId());
        RefreshToken oldRefreshToken = refreshTokenRepository.findByUserOrElseThrow(user);
        oldRefreshToken.updateRefreshToken(refreshTokenKey);

        return new TokenResponseDto(accessToken, refreshTokenKey);
    }

    public void deleteRefreshToken(User user) {
        Long userId = user.getId();
        refreshTokenRepository.deleteRefreshTokenById(userId);
    }

}