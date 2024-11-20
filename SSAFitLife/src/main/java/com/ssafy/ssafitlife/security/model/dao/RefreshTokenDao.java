package com.ssafy.ssafitlife.security.model.dao;

import com.ssafy.ssafitlife.security.model.dto.RefreshToken;

import java.util.Date;

public interface RefreshTokenDao {
    Boolean existsByRefresh(String refresh);
    void deleteByRefresh(String refresh);
    void insertToken(RefreshToken refreshToken);
    Integer findMemberNoByRefresh(String refresh);
}
