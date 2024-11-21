package com.ssafy.ssafitlife.security.jwt;

import com.ssafy.ssafitlife.security.model.service.RefreshTokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class CustomLogoutFilter extends GenericFilterBean {

    private final JWTUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public CustomLogoutFilter(JWTUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //path and method verify
        String resultURI = request.getRequestURI();
        if(!resultURI.matches("^\\/logout$")) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestMethod = request.getMethod();
        if(!requestMethod.equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        //refresh null check
        if(refresh == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //토큰이 refresh인지 확인( 발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);
        if(!category.equals("refresh")) {
            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshTokenService.isRefreshTokenExists(refresh);
        if(!isExist) {
            System.out.println("## doLogOut !isExist ##");
            //response status code
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //로그아웃 진행
        //Refresh 토큰 DB에서 제거
        refreshTokenService.removeRefreshTokenByMemNo(refreshTokenService.findMemberNoByRefresh(refresh));

        //Refresh 토큰 Cookie 값 0
        Cookie refreshCookie = new Cookie("refresh", null);
        refreshCookie.setMaxAge(0);
        refreshCookie.setPath("/");

        //액세스 토큰 쿠키도 삭제
        Cookie accessCookie = new Cookie("access", null);
        accessCookie.setMaxAge(0); // 액세스 토큰 쿠키 만료 처리
        accessCookie.setPath("/");

        response.addCookie(refreshCookie);
        response.addCookie(accessCookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}