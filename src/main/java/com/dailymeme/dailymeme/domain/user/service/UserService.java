package com.dailymeme.dailymeme.domain.user.service;

import com.dailymeme.dailymeme.domain.user.dto.login.UserLoginRequestDto;
import com.dailymeme.dailymeme.domain.user.dto.login.UserLoginResponseDto;
import com.dailymeme.dailymeme.domain.user.dto.signup.UserSignupRequestDto;
import com.dailymeme.dailymeme.domain.user.dto.signup.UserSignupResponseDto;
import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.domain.user.repository.UserRepository;
import com.dailymeme.dailymeme.global.entity.RefreshToken;
import com.dailymeme.dailymeme.global.jwtutil.JwtUtil;
import com.dailymeme.dailymeme.global.token.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    //Signup
    @Transactional
    public UserSignupResponseDto signup(
            UserSignupRequestDto userSignupRequestDto) {

        User user = buildUser(userSignupRequestDto);

        Optional<User> duplicateCheck = userRepository.findByEmail(userSignupRequestDto.getEmail());

        if(duplicateCheck.isPresent()) {
            throw new RuntimeException("already exist User");
        }

        userRepository.save(user);

        return new UserSignupResponseDto(user.getId(), user.getUserName(), user.getEmail(), user.getMail_send_agree());

    }

    @Transactional
    public UserLoginResponseDto login(
            UserLoginRequestDto userLoginRequestDto) {

        String email = userLoginRequestDto.getEmail();
        String password = userLoginRequestDto.getPassword();

        User user = userRepository.findByEmailOrElseThrow(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호 오류 나중에 수정하기(영웅)");
        }

        String accessToken = jwtUtil.generateAccessToken(user.getId());
        String refreshToken = jwtUtil.generateRefreshToken(user.getId());

        log.info("Access 토큰 생성 : {}", accessToken);
        log.info("Refresh 토큰 생성 : {}", refreshToken);

        refreshTokenRepository.findByUser(user)
                .ifPresentOrElse(
                        existing -> existing.updateRefreshToken(refreshToken),
                        () -> refreshTokenRepository.save(new RefreshToken(user, refreshToken))
                );


        return new UserLoginResponseDto(user.getId(), user.getUserName(), user.getEmail(),accessToken, refreshToken);
    }


    //User create Builder
    private User buildUser(UserSignupRequestDto userSignupRequestDto) {
        return User.builder()
                .userName(userSignupRequestDto.getUsername())
                .email(userSignupRequestDto.getEmail())
                .password(getEncodingPassword(userSignupRequestDto.getPassword()))
                .mail_send_agree(userSignupRequestDto.getMail_send_agree())
                .build();
    }

    private String getEncodingPassword(String password) {
        return passwordEncoder.encode(password);
    }

}


