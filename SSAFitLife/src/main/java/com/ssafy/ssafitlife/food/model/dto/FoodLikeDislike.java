package com.ssafy.ssafitlife.food.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodLikeDislike {
    private Long likeDislikeNo;      // 좋아요/싫어요 번호
    private Long foodNo;             // 음식 번호
    private Integer memNo;              // 회원 번호
    private Integer status;             // 상태 (좋아요: 1, 싫어요: 0)
    private Date createAt;  // 생성 시각
}