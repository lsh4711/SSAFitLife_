package com.ssafy.ssafitlife.food.model.service;

import com.ssafy.ssafitlife.food.model.dto.FoodLikeDislike;

public interface FoodLikeDislikeService {
    int getLikeDislikeCountByFoodNo(Long foodNo);
    boolean existsByFoodNoMemNo(Long foodNo, Integer memNo);
    void addLikeDislike(FoodLikeDislike likeDislike);
    void updateLikeDislike(FoodLikeDislike likeDislike);
}