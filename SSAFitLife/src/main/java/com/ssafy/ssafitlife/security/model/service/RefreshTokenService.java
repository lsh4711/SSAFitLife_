package com.ssafy.ssafitlife.security.model.service;

import com.ssafy.ssafitlife.security.model.dto.RefreshToken;

public interface RefreshTokenService {
    boolean isRefreshTokenExists(String refreshToken);
    void removeRefreshTokenByMemNo(int memNo);
    void removeRefreshTokenByRefresh(String refresh);
    void saveToken(RefreshToken refreshToken);
    Integer findMemberNoByRefresh(String refreshToken);
}
