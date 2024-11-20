package com.ssafy.ssafitlife.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {
    private int tokenNo;
    private String refreshToken;
    private Date expiration;
    private Date createdAt;
    private Date updatedAt;
    private int memNo;
}

