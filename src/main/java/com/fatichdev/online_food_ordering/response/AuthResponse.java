package com.fatichdev.online_food_ordering.response;

import com.fatichdev.online_food_ordering.model.USER_ROLE;
import lombok.Data;

// With @Data annotation, we don't need to write getter and setter methods. Provided by Lombok.
@Data
public class AuthResponse {

    private String jwt;
    private String message;

    private USER_ROLE role;

}
