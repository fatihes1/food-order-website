package com.fatichdev.online_food_ordering.repository;

import com.fatichdev.online_food_ordering.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Restaurant findByOwnerId(Long userId);

    @Query("SELECT r FROM Restaurant r where lower(r.name) like lower(concat('%', :query, '%')) or lower(r.cuisineType) like lower(concat('%', :query, '%'))")
    List<Restaurant> findBySearchQuery(String query);
}
