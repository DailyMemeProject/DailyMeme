package com.dailymeme.dailymeme.domain.user.service;

import com.dailymeme.dailymeme.domain.user.dto.signup.UserSignupRequestDto;
import com.dailymeme.dailymeme.domain.user.dto.signup.UserSignupResponseDto;
import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

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

    private User buildUser(UserSignupRequestDto userSignupRequestDto) {
        return User.builder()
                .userName(userSignupRequestDto.getUsername())
                .email(userSignupRequestDto.getEmail())
                .password(userSignupRequestDto.getPassword())
                .mail_send_agree(userSignupRequestDto.getMail_send_agree())
                .build();
    }

}


