package com.ssafy.ssafitlife.food.model.service;

import com.ssafy.ssafitlife.food.model.dao.FoodLikeDislikeDao;
import com.ssafy.ssafitlife.food.model.dto.FoodLikeDislike;
import org.springframework.stereotype.Service;

@Service
public class FoodLikeDislikeServiceImpl implements FoodLikeDislikeService {

    private final FoodLikeDislikeDao foodLikeDislikeDao;

    FoodLikeDislikeServiceImpl(FoodLikeDislikeDao foodLikeDislikeDao) {
        this.foodLikeDislikeDao = foodLikeDislikeDao;
    }

    @Override
    public int getLikeDislikeCountByFoodNo(Long foodNo) {
        return foodLikeDislikeDao.selectByFoodNo(foodNo);
    }

    @Override
    public boolean existsByFoodNoMemNo(Long foodNo, Integer memNo) {
        return foodLikeDislikeDao.existsByFoodNoMemNo(foodNo, memNo);
    }

    @Override
    public void addLikeDislike(FoodLikeDislike foodLikeDislike) {
        foodLikeDislikeDao.insertLikeDislike(foodLikeDislike);
    }

    @Override
    public void updateLikeDislike(FoodLikeDislike foodLikeDislike) {
        foodLikeDislikeDao.updateLikeDislike(foodLikeDislike);
    }
}
