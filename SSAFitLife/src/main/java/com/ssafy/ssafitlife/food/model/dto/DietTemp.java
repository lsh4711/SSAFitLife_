package com.ssafy.ssafitlife.food.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietTemp {
    private Long dietTempNo;     // 식단 템플릿 번호
    private Integer memNo;          // 회원 번호
    private String dietTempName; // 템플릿 이름
    private Boolean isShared;    // 공유 여부
}