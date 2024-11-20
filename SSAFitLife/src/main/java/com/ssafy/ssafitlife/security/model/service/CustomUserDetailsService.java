package com.ssafy.ssafitlife.security.model.service;

import com.ssafy.ssafitlife.security.model.dto.CustomUserDetails;
import com.ssafy.ssafitlife.user.model.dao.UserDao;
import com.ssafy.ssafitlife.user.model.dto.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 이메일을 기반으로 사용자 정보 가져오기
        User user = userDao.selectByEmail(email);

        // 사용자가 존재하지 않으면 예외 처리
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // CustomUserDetails에 DTO(User) 변환하여 반환
        return new CustomUserDetails(user);
    }
}
