package com.ssafy.ssafitlife.security.model.service;

import com.ssafy.ssafitlife.security.model.dto.RefreshToken;

public interface RefreshTokenService {
    boolean isRefreshTokenExists(String refreshToken);
    void removeRefreshToken(String refreshToken);
    void saveToken(RefreshToken refreshToken);
}
