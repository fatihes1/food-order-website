package com.fatichdev.online_food_ordering.repository;

import com.fatichdev.online_food_ordering.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
