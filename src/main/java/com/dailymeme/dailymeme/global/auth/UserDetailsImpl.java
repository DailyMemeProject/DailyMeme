package com.dailymeme.dailymeme.global.auth;

import com.dailymeme.dailymeme.domain.user.entity.User;
import com.dailymeme.dailymeme.domain.user.enums.UserAuth;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserAuth role = this.user.getUserAuth();

        return new ArrayList<>(role.getAuthorities());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
