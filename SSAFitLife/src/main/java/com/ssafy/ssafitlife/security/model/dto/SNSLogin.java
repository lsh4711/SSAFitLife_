package com.ssafy.ssafitlife.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SNSLogin {
    private int snsNo;
    private String snsType;
    private String snsId;
    private String snsEmail;
    private int memNo;
}