package com.ssafy.ssafitlife.user.model.dao;

import com.ssafy.ssafitlife.user.model.dto.User;

import java.util.List;

public interface UserDao {
    List<User> selectAll();
    Boolean existsByEmail(String email);
    User selectByEmail(String email);
    void insertUser(User user);
}
