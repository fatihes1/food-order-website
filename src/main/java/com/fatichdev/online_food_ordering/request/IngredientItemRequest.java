package com.fatichdev.online_food_ordering.request;

import lombok.Data;

@Data
public class IngredientItemRequest {

    private String name;
    private Long ingredientCategoryId;
    private Long restaurantId;
}
