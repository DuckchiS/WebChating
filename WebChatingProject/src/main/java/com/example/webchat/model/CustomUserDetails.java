package com.example.webchat.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.webchat.entity.UserEntity;


public class CustomUserDetails implements UserDetails{
	private final UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    public String getNickname() {
        return user.getUserNickname(); // 수정된 닉네임 필드
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().toUpperCase())); // 수정된 역할 필드
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword(); // 수정된 비밀번호 필드
    }

    @Override
    public String getUsername() {
        return user.getUserId(); // 수정된 아이디 필드
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
