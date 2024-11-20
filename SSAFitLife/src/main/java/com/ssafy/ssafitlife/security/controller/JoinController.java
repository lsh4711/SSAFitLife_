package com.ssafy.ssafitlife.security.controller;

import com.ssafy.ssafitlife.security.model.service.JoinService;
import com.ssafy.ssafitlife.user.model.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {
    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(@RequestBody User user) {
        joinService.joinProcess(user);
        return "ok";
    }
}
