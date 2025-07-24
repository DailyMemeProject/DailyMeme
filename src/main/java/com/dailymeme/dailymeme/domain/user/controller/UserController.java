package com.dailymeme.dailymeme.domain.user.controller;


import com.dailymeme.dailymeme.domain.user.dto.delete.UserDeleteRequestDto;
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
import com.dailymeme.dailymeme.domain.user.service.UserService;
import com.dailymeme.dailymeme.global.auth.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDto> createUser(
            @RequestBody UserSignupRequestDto userSignupRequestDto) {
        UserSignupResponseDto userSignupResponseDto = userService.signup(userSignupRequestDto);

        return new ResponseEntity<>(userSignupResponseDto, HttpStatus.CREATED);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(
            @RequestBody UserLoginRequestDto userLoginRequestDto,
            HttpServletResponse response) {

        UserLoginResponseDto userLoginResponseDto = userService.login(userLoginRequestDto);

        ResponseCookie cookie = ResponseCookie.from("Authorization", userLoginResponseDto.getAccessToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(3600)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK);
    }

    //회원 정보 전체 조회
    @GetMapping("/info")
    public ResponseEntity<List<UserInfoListResponseDto>> getUserInfo(
            @AuthenticationPrincipal UserDetails userDetails) {

        List<UserInfoListResponseDto> userInfoResponseDtos = userService.getAllUsers();

        return new ResponseEntity<>(userInfoResponseDtos, HttpStatus.OK);
    }

    //회원 정보 단건 조회
    @GetMapping("/info/{userId}")
    public ResponseEntity<UserInfoResponseDto> getUserInfo(@PathVariable Long userId) {

        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);

    }

    //본인 정보 변경
    @PatchMapping("/info/update")
    public ResponseEntity<UserInfoUpdateResponseDto>  updateUserInfo(
            @RequestBody UserInfoUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();

        UserInfoUpdateResponseDto responseDto = userService.updateUserInfo(requestDto, user);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //회원 탈퇴
    @PatchMapping("/delete")
    public ResponseEntity<UserDeleteResponseDto> deleteUser(@RequestBody UserDeleteRequestDto requestDto,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        User user = userDetails.getUser();

        String password = requestDto.getPassword();

        UserDeleteResponseDto responseDto = userService.deleteUser(user,password);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }


}
