package com.ssafy.ssafitlife.food.model.service;

import com.ssafy.ssafitlife.food.model.dao.FoodDao;
import com.ssafy.ssafitlife.food.model.dto.Food;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodDao foodDao;
    public FoodServiceImpl(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public void addFood(Food food) {
        foodDao.insertFood(food);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodDao.selectAll();
    }

    @Override
    public Food getFoodByNo(Long foodNo) {
        return foodDao.selectByFoodNo(foodNo);
    }

    @Override
    public void updateFood(Food food) {
        foodDao.updateFood(food);
    }

    @Override
    public void removeFood(Long foodNo) {
        foodDao.deleteFood(foodNo);
    }
}
