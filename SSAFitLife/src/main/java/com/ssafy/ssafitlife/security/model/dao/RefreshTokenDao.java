package com.ssafy.ssafitlife.security.model.dao;

import com.ssafy.ssafitlife.security.model.dto.RefreshToken;

public interface RefreshTokenDao {
    Boolean existsByRefresh(String refresh);
    void deleteRefreshTokenByMemNo(int memNo);
    void deleteRefreshTokenByRefresh(String refresh);
    void insertToken(RefreshToken refreshToken);
    Integer selectMemberNoByRefresh(String refresh);
}
