package com.dailymeme.dailymeme.domain.user.service;

import com.dailymeme.dailymeme.domain.user.dto.delete.UserDeleteResponseDto;
import com.dailymeme.dailymeme.domain.user.dto.info.UserInfoListResponseDto;
import com.dailymeme.dailymeme.domain.user.dto.info.UserInfoResponseDto;
import com.dailymeme.dailymeme.domain.user.dto.info.UserInfoUpdateRequestDto;
import com.dailymeme.dailymeme.domain.user.dto.info.UserInfoUpdateResponseDto;
import com.dailymeme.dailymeme.domain.user.dto.login.UserLoginRequestDto;
import com.dailymeme.dailymeme.domain.user.dto.login.UserLoginResponseDto;
import com.dailymeme.dailymeme.domain.user.dto.signup.UserSignupRequestDto;
import com.dailymeme.dailymeme.domain.user.dto.signup.UserSignupResponseDto;
import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.domain.user.enums.UserStatus;
import com.dailymeme.dailymeme.domain.user.repository.UserRepository;
import com.dailymeme.dailymeme.global.entity.RefreshToken;
import com.dailymeme.dailymeme.global.exception.ExceptionType;
import com.dailymeme.dailymeme.global.exception.WrongPassWordException;
import com.dailymeme.dailymeme.global.jwtutil.JwtUtil;
import com.dailymeme.dailymeme.global.token.repository.RefreshTokenRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        String message = "회원가입이 완료되었습니다!";

        Optional<User> duplicateCheck = userRepository.findByEmail(userSignupRequestDto.getEmail());

        if(duplicateCheck.isPresent()) {
            throw new RuntimeException("already exist User");
        }

        userRepository.save(user);

        return new UserSignupResponseDto(message);

    }

    //Login
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

    //사용자 전체 조회
    @Transactional
    public List<UserInfoListResponseDto> getAllUsers() {
        List<UserInfoListResponseDto> userList = new ArrayList<>();

        List<UserInfoListResponseDto> findUserList = userRepository.findUserByUserAuth();

        for(UserInfoListResponseDto user : findUserList) {
            userList.add(new UserInfoListResponseDto(
                    user.getUserName(),
                    user.getEmail(),
                    user.getMailSendAgree()));
        }
        return findUserList;
    }

    //사용자 단건 조회
    @Transactional
    public UserInfoResponseDto getUserById(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);

        return new UserInfoResponseDto(user.getUserName(), user.getEmail());
    }

    //사용자 정보 변경
    @Transactional
    public UserInfoUpdateResponseDto updateUserInfo(UserInfoUpdateRequestDto requestDto, User user) {

        User managedUser = userRepository.findByIdOrElseThrow(user.getId());

        log.info("Service - Before update: mailSendAgree = {}", managedUser.getMailSendAgree());
        log.info("Service - DTO value: mailSendAgree = {}", requestDto.getMailSendAgree());

        managedUser.updateInfo(requestDto.getMailSendAgree());

        log.info("Service - After update: mailSendAgree = {}", managedUser.getMailSendAgree());

        return new UserInfoUpdateResponseDto(managedUser);

    }

    //사용자 탈퇴
    @Transactional
    public UserDeleteResponseDto deleteUser(User user, String password) {

        User deleteUser = userRepository.findByIdOrElseThrow(user.getId());

        if(!passwordEncoder.matches(password, deleteUser.getPassword())) {
            throw new WrongPassWordException(ExceptionType.WRONG_PASSWORD);
        }
        deleteUser.deleteUser(UserStatus.DELETED);
        userRepository.save(deleteUser);

        String message = "정상적으로 삭제되었습니다.";

        return new UserDeleteResponseDto(message);
    }

    //User create Builder
    private User buildUser(UserSignupRequestDto userSignupRequestDto) {
        return User.builder()
                .userName(userSignupRequestDto.getUsername())
                .email(userSignupRequestDto.getEmail())
                .password(getEncodingPassword(userSignupRequestDto.getPassword()))
                .mailSendAgree(userSignupRequestDto.getMailSendAgree())
                .build();
    }

    private String getEncodingPassword(String password) {
        return passwordEncoder.encode(password);
    }

}


