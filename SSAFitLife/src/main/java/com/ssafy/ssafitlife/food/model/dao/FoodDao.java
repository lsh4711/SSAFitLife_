package com.ssafy.ssafitlife.food.model.dao;

import com.ssafy.ssafitlife.food.model.dto.Food;

import java.util.List;

public interface FoodDao {
    List<Food> selectAll();
    Food selectByFoodNo(Long foodNo);
    void insertFood(Food food);
    void updateFood(Food food);
    void deleteFood(Long foodNo);
}
