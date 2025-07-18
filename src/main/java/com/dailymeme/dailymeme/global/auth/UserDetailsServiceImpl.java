package com.dailymeme.dailymeme.global.auth;

import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = this.userRepository.findByIdOrElseThrow(Long.valueOf(userId));

        if (!user.isActive()) {
            throw new DisabledException("user is disabled");
        }
        return new UserDetailsImpl(user);
    }
}
