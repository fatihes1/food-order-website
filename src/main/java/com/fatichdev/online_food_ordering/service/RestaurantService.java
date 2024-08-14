package com.fatichdev.online_food_ordering.service;

import com.fatichdev.online_food_ordering.dto.RestaurantDto;
import com.fatichdev.online_food_ordering.model.Restaurant;
import com.fatichdev.online_food_ordering.model.User;
import com.fatichdev.online_food_ordering.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest request, User user);
    public Restaurant updateRestaurant(CreateRestaurantRequest request, Long restaurantId) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurants(String keyword);

    public Restaurant findRestaurantById(Long restaurantId) throws Exception;

    public Restaurant findRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorite(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}
