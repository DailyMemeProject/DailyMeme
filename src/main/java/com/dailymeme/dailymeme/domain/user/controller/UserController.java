package com.dailymeme.dailymeme.domain.user.controller;


import com.dailymeme.dailymeme.domain.user.dto.login.UserLoginRequestDto;
import com.dailymeme.dailymeme.domain.user.dto.login.UserLoginResponseDto;
import com.dailymeme.dailymeme.domain.user.dto.signup.UserSignupRequestDto;
import com.dailymeme.dailymeme.domain.user.dto.signup.UserSignupResponseDto;
import com.dailymeme.dailymeme.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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


}
