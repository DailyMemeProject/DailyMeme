package com.dailymeme.dailymeme.domain.user.enums;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public enum UserAuth {

    USER("user"),
    ADMIN("admin");

    private String name;
    UserAuth(String name) {this.name = name;}

    public static UserAuth of(String authName) throws IllegalArgumentException {
        for(UserAuth auth : UserAuth.values()) {
            if(auth.getName().equals(authName.toLowerCase())) {
                return auth;
            }
        }
        throw new IllegalArgumentException("Wrong Auth" + authName);
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("auth" + this.name()));
    }

}
