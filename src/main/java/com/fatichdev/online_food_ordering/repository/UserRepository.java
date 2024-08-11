package com.fatichdev.online_food_ordering.repository;

import com.fatichdev.online_food_ordering.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
