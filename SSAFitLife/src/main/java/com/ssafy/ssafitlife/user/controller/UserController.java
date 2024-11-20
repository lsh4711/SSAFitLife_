package com.ssafy.ssafitlife.user.controller;

import com.ssafy.ssafitlife.user.model.dto.User;
import com.ssafy.ssafitlife.user.model.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 사용자 목록 반환
    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.getUserList();
    }
}
