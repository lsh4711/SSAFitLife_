package com.ssafy.ssafitlife.security.jwt;

import com.ssafy.ssafitlife.security.model.dto.CustomUserDetails;
import com.ssafy.ssafitlife.security.model.service.ReissueService;
import com.ssafy.ssafitlife.user.model.dto.User;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final ReissueService reissueService;

    public JWTFilter(JWTUtil jwtUtil, ReissueService reissueService) {
        this.jwtUtil = jwtUtil;
        this.reissueService = reissueService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 헤더에서 Access 토큰 추출
        String accessToken = request.getHeader("Authorization");
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7); // "Bearer " 부분을 제거하여 토큰만 추출
        }

        // 토큰이 없으면 다음 필터로 진행
        if (accessToken == null || accessToken.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 토큰 만료 여부 확인
            jwtUtil.isExpired(accessToken);

            // 토큰 타입 확인
            String category = jwtUtil.getCategory(accessToken);
            if (!"access".equals(category)) {
                sendErrorResponse(response, "Invalid access token", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            // 사용자 정보와 권한 획득
            String userEmail = jwtUtil.getEmail(accessToken);
            String role = jwtUtil.getRole(accessToken);
            Integer memNo = jwtUtil.getMemNo(accessToken);

            // User 객체 생성 및 CustomUserDetails 설정
            Authentication authentication = getAuthentication(userEmail, role, memNo);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (ExpiredJwtException e) {
            // Access 토큰 만료 시 Refresh 토큰을 이용해 재발급 요청
            String refreshToken = getRefreshTokenFromRequest(request);
            if (refreshToken != null) {
                // 재발급 처리
                reissueService.reissueTokens(request, response);

                // 새로 발급된 Access 토큰을 헤더에 추가
                String newAccessToken = response.getHeader("Authorization");
                if (newAccessToken != null && newAccessToken.startsWith("Bearer ")) {
                    newAccessToken = newAccessToken.substring(7); // "Bearer " 부분을 제거하여 토큰만 추출
                }

                // 새로 발급된 Access 토큰으로 인증
                try {
                    jwtUtil.isExpired(newAccessToken);  // 새 토큰의 유효성 검사
                    String category = jwtUtil.getCategory(newAccessToken);
                    if ("access".equals(category)) {
                        String userEmail = jwtUtil.getEmail(newAccessToken);
                        String role = jwtUtil.getRole(newAccessToken);
                        Integer memNo = jwtUtil.getMemNo(newAccessToken);
                        Authentication authentication = getAuthentication(userEmail, role, memNo);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (ExpiredJwtException ex) {
                    sendErrorResponse(response, "Failed to renew token", HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                // 원래 요청한 URI로 리다이렉트
                String redirectUrl = (String) request.getAttribute("originalUrl");
                if (redirectUrl != null) {
                    response.sendRedirect(redirectUrl); // 재발급 후 원래 URL로 리다이렉트
                    return;
                }
            } else {
                sendErrorResponse(response, "Refresh token missing", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } catch (Exception e) {
            sendErrorResponse(response, "Invalid token", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private static Authentication getAuthentication(String userEmail, String role, Integer memNo) {
        User user = new User();
        user.setEmail(userEmail);
        user.setRole(role);
        user.setMemNo(memNo);
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return new UsernamePasswordAuthenticationToken(
                customUserDetails,
                null,
                customUserDetails.getAuthorities()
        );
    }


    private String getRefreshTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 에러 응답을 JSON 형태로 반환
     */
    private void sendErrorResponse(HttpServletResponse response, String message, int status) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + message + "\"}");
        response.getWriter().flush();
        response.getWriter().close();
    }
}
