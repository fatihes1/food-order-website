package com.fatichdev.online_food_ordering.service;

import com.fatichdev.online_food_ordering.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User findUserByJwtToken(String jwtToken) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
