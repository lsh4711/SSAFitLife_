package com.ssafy.ssafitlife.user.model.service;

import com.ssafy.ssafitlife.user.model.dto.User;

import java.util.List;

public interface UserService {
    //전체 사용자 목록 불러오기
    public List<User> getUserList();
    //Email 중복 체크
    Boolean existsByEmail(String email);
    //Email로 검색
    User findByEmail(String email);
    //등록
    void saveUser(User user);
}
