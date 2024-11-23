package com.ssafy.ssafitlife.food.model.service;

import com.ssafy.ssafitlife.food.model.dto.Food;

import java.util.List;

public interface FoodService {
    // 모든 음식 조회
    List<Food> getAllFoods();

    // 음식 번호로 특정 음식 조회
    Food getFoodByNo(Long foodNo);

    // 새 음식 추가
    void addFood(Food food);

    // 기존 음식 수정
    void updateFood(Food food);

    // 음식 삭제
    void removeFood(Long foodNo);
}
