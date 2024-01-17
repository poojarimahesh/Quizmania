package com.quizmania.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

public class UserInfoUserDetails implements UserDetails {
    private String userName;
    private String password;
    List<GrantedAuthority> authorities;

    public UserInfoUserDetails(User user) {
        userName = user.getUserName();
        password = user.getPassword();
        authorities= Arrays.asList(new SimpleGrantedAuthority(user.getRole().getRoleName()));
//        authorities= Arrays.stream(user.getRole().getRoleName()).map(SimpleGrantedAuthority::new).collect(Collector.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
