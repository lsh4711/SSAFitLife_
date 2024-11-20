package com.ssafy.ssafitlife.security.model.service;

import com.ssafy.ssafitlife.user.model.dto.User;
import com.ssafy.ssafitlife.user.model.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JoinServiceImpl implements JoinService {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinServiceImpl(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void joinProcess(User user) {
        // 이메일 중복 확인
        String email = user.getEmail();
        if (userService.existsByEmail(email)) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        // 비밀번호 암호화 및 기본 데이터 설정
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER"); // 기본 권한 설정

        userService.saveUser(user);
    }
}
