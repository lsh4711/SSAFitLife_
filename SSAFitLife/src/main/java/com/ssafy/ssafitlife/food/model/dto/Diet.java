package com.ssafy.ssafitlife.food.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diet {
    private Long dietNo;        // 식단 번호
    private Integer memNo;         // 회원 번호
    private Long foodNo;        // 음식 번호
    private Long dietTempNo;    // 식단 템플릿 번호
    private Integer dietType;   // 식단 종류 (0: 아침, 1: 점심, 2: 저녁, 3: 간식 등)
    private Float foodQuantity; // 음식의 양
}