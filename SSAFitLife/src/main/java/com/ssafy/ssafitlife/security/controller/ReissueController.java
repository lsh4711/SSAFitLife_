package com.ssafy.ssafitlife.security.controller;

import com.ssafy.ssafitlife.security.jwt.JWTUtil;
import com.ssafy.ssafitlife.security.model.dto.RefreshToken;
import com.ssafy.ssafitlife.security.model.service.RefreshTokenService;
import com.ssafy.ssafitlife.security.model.service.ReissueService;
import com.ssafy.ssafitlife.user.model.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@ResponseBody
public class ReissueController {
    private final ReissueService reissueService;

    public ReissueController(ReissueService reissueService) {
        this.reissueService = reissueService;
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        // 원래 요청 URL을 request 속성에 저장
        request.setAttribute("originalUrl", request.getRequestURI());

        return reissueService.reissueTokens(request, response);
    }

}
