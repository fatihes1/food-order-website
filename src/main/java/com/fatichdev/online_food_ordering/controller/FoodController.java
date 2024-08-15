package com.fatichdev.online_food_ordering.controller;

import com.fatichdev.online_food_ordering.model.Food;
import com.fatichdev.online_food_ordering.model.Restaurant;
import com.fatichdev.online_food_ordering.model.User;
import com.fatichdev.online_food_ordering.request.CreateFoodRequest;
import com.fatichdev.online_food_ordering.service.FoodService;
import com.fatichdev.online_food_ordering.service.RestaurantService;
import com.fatichdev.online_food_ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                           @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.searchFood(keyword);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    };

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
                                                        @RequestParam boolean nonVegetarian,
                                                        @RequestParam boolean seasonal,
                                                        @RequestParam(required = false) String foodCategory,
                                                        @PathVariable Long restaurantId,
                                                        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getRestaurantsFood(restaurantId, vegetarian, nonVegetarian, seasonal, foodCategory);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    };



}
