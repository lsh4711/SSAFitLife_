package com.ssafy.ssafitlife.security.model.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface ReissueService {
    ResponseEntity<?> reissueTokens(HttpServletRequest request, HttpServletResponse response);
}
