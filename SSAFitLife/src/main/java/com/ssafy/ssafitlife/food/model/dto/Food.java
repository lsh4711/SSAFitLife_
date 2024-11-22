package com.ssafy.ssafitlife.food.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private Long foodNo;           // 음식 번호
    private String foodName;       // 음식 이름
    private Float foodAmt;         // 음식 양 (g)
    private Float foodCarb;        // 탄수화물 함량 (g)
    private Float foodProtein;     // 단백질 함량 (g)
    private Float foodFat;         // 지방 함량 (g)
    private Float foodCalorie;     // 칼로리 (kcal)
    private Integer memNo;            // 작성자 회원 번호
    private Boolean isShared;      // 공유 여부
    private Long sumLikeDislike; // 좋아요 합계
}
