package com.ssafy.ssafitlife.security.model.service;

import com.ssafy.ssafitlife.security.jwt.JWTUtil;
import com.ssafy.ssafitlife.security.model.dto.RefreshToken;
import com.ssafy.ssafitlife.security.model.service.RefreshTokenService;
import com.ssafy.ssafitlife.security.model.service.ReissueService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReissueServiceImpl implements ReissueService {

    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public ReissueServiceImpl(JWTUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public ResponseEntity<?> reissueTokens(HttpServletRequest request, HttpServletResponse response) {

        // get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        // expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인 (발급 시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        // DB에 저장되어 있는지 확인
        Integer memNo = refreshTokenService.findMemberNoByRefresh(refresh);
        if (memNo == null) {
            return new ResponseEntity<>("refresh token not found", HttpStatus.BAD_REQUEST);
        }

        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        // make new JWT
        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        // Refresh 토큰 저장 (기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장)
        refreshTokenService.removeRefreshTokenByRefresh(refresh);
        addRefreshEntity(memNo, newRefresh, 86400000L);

        // response
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void addRefreshEntity(Integer memNo, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setMemNo(memNo);
        refreshToken.setRefreshToken(refresh);
        refreshToken.setExpiration(date);

        refreshTokenService.saveToken(refreshToken);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60); // 1일
        cookie.setHttpOnly(true);
        //cookie.setSecure(true); // HTTPS일 경우
        return cookie;
    }
}
