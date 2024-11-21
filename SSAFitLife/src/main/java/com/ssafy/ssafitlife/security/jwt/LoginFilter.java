package com.ssafy.ssafitlife.security.jwt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssafitlife.security.model.dto.RefreshToken;
import com.ssafy.ssafitlife.security.model.service.RefreshTokenService;
import com.ssafy.ssafitlife.user.model.dao.UserDao;
import com.ssafy.ssafitlife.user.model.dto.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Date;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserDao userDao;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RefreshTokenService refreshTokenService, UserDao userDao) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        this.userDao = userDao;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = null;
        String password = null;

        try {
            // JSON 데이터를 파싱
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                json.append(line);
            }

            // JSON 객체 파싱 (Jackson 또는 Gson을 사용)
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json.toString());
            username = jsonNode.get("username").asText();
            password = jsonNode.get("password").asText();
        } catch (Exception e) {
            throw new AuthenticationException("Invalid JSON format") {
            };
        }

        // 인증 토큰 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.stream().findFirst().orElseThrow().getAuthority();

        User user = userDao.selectByEmail(username);

        int memNo = user.getMemNo();

        String accessToken = jwtUtil.createJwt("access", username, role, memNo, 600000L); // 10분
        String refreshToken = jwtUtil.createJwt("refresh", username, role, memNo, 86400000L); // 1일

        saveRefreshToken(username, refreshToken);

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.addCookie(createCookie("refresh", refreshToken));
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    private void saveRefreshToken(String email, String refreshToken) {
        User user = userDao.selectByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        RefreshToken rf = new RefreshToken();
        rf.setRefreshToken(refreshToken);
        rf.setExpiration(new Date(System.currentTimeMillis() + 86400000L));
        rf.setMemNo(user.getMemNo());

        refreshTokenService.saveToken(rf);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
}
