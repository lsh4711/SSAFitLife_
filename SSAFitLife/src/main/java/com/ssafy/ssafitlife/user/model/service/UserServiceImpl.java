package com.ssafy.ssafitlife.user.model.service;

import com.ssafy.ssafitlife.user.model.dao.UserDao;
import com.ssafy.ssafitlife.user.model.dto.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getUserList() {
        return userDao.selectAll();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public Boolean existsByNickname(String nickname) {
        return userDao.existsByNickname(nickname);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.selectByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        // 이메일 중복 확인
        String email = user.getEmail();
        if (userDao.existsByEmail(email)) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        // 비밀번호 암호화 및 기본 데이터 설정
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER"); // 기본 권한 설정

        userDao.insertUser(user);
    }
}
