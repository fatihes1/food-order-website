package com.fatichdev.online_food_ordering.service;

import com.fatichdev.online_food_ordering.model.IngredientCategory;
import com.fatichdev.online_food_ordering.model.IngredientsItem;
import com.fatichdev.online_food_ordering.model.Restaurant;
import com.fatichdev.online_food_ordering.repository.IngredientCategoryRepository;
import com.fatichdev.online_food_ordering.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientsService{
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);

        if (ingredientCategory.isEmpty()) {
            throw new Exception("Ingredient Category not found");
        }

        return ingredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        restaurantService.findRestaurantById(restaurantId);
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String name, Long ingredientCategoryId) throws Exception {
        IngredientCategory ingredientCategory = findIngredientCategoryById(ingredientCategoryId);
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setName(name);
        ingredientsItem.setRestaurant(restaurant);
        ingredientsItem.setCategory(ingredientCategory);

        IngredientsItem savedIngredientsItem = ingredientItemRepository.save(ingredientsItem);

        ingredientCategory.getIngredients().add(savedIngredientsItem);

        return savedIngredientsItem;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long ingredientItemId) throws Exception {
        Optional<IngredientsItem> ingredientsItem = ingredientItemRepository.findById(ingredientItemId);

        if (ingredientsItem.isEmpty()) {
            throw new Exception("Ingredient Item not found");
        }

        IngredientsItem updatedIngredientsItem = ingredientsItem.get();
        updatedIngredientsItem.setInStoke(!updatedIngredientsItem.isInStoke());

        return ingredientItemRepository.save(updatedIngredientsItem);
    }
}
