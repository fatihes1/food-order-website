package com.fatichdev.online_food_ordering.service;

import com.fatichdev.online_food_ordering.model.IngredientCategory;
import com.fatichdev.online_food_ordering.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception;

    public IngredientsItem createIngredientsItem(Long restaurantId, String name, Long ingredientCategoryId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId);

    public IngredientsItem updateStock(Long ingredientItemId) throws Exception;

}
