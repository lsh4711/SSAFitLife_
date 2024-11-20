package com.ssafy.ssafitlife.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int memNo;
    private String email;
    private String password;
    private String name;
    private float height;
    private int gender;
    private Date birthday;
    private String phoneNumber;
    private String nickname;
    private String role;
    private Date lastActDate;
    private boolean isActive;
    private Date joinDate;
}
