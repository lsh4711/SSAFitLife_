package com.ssafy.ssafitlife.food.model.dao;

import com.ssafy.ssafitlife.food.model.dto.FoodLikeDislike;

import java.util.List;

public interface FoodLikeDislikeDao {
    int selectByFoodNo(Long foodNo);
    boolean existsByFoodNoMemNo(Long foodNo, Integer memNo);
    void insertLikeDislike(FoodLikeDislike likeDislike);
    void updateLikeDislike(FoodLikeDislike likeDislike);
}
