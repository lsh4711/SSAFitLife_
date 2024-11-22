package com.ssafy.ssafitlife.food.controller;

import com.ssafy.ssafitlife.food.model.dto.Food;
import com.ssafy.ssafitlife.food.model.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/food")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{foodNo}")
    public Food getFoodByNo(@PathVariable Long foodNo) {
        return foodService.getFoodByNo(foodNo);
    }

    @PostMapping
    public void addFood(@RequestBody Food food) {
        foodService.addFood(food);
    }

    @PutMapping("/{foodNo}")
    public void updateFood(@RequestBody Food food, @PathVariable Long foodNo) {
        food.setFoodNo(foodNo);
        foodService.updateFood(food);
    }

    @DeleteMapping("/{foodNo}")
    public void deleteFood(@PathVariable Long foodNo) {
        foodService.removeFood(foodNo);
    }
}

