package com.ssafy.ssafitlife.security.model.service;

import com.ssafy.ssafitlife.user.model.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface JoinService {
    public void joinProcess(User user);
}
