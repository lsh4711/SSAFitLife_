package com.ssafy.ssafitlife.security.model.dto;

import com.ssafy.ssafitlife.user.model.dto.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // 사용자 권한 (역할)을 반환하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자 역할을 Authority로 반환
        return Collections.singletonList(() -> user.getRole());
    }

    // 비밀번호 반환
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 사용자 이름 (보통 이메일)을 반환
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // 계정이 만료되었는지 확인 (여기서는 항상 true)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지 확인 (여기서는 항상 true)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 자격 증명이 만료되었는지 확인 (여기서는 항상 true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화 되어 있는지 확인 (User의 isActive 값을 체크)
    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

    // CustomUserDetails에서 User 객체 반환
    public User getUser() {
        return user;
    }
}
