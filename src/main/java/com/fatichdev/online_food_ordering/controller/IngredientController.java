package com.fatichdev.online_food_ordering.controller;

import com.fatichdev.online_food_ordering.model.IngredientCategory;
import com.fatichdev.online_food_ordering.model.IngredientsItem;
import com.fatichdev.online_food_ordering.request.IngredientCategoryRequest;
import com.fatichdev.online_food_ordering.request.IngredientItemRequest;
import com.fatichdev.online_food_ordering.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest request
    ) throws Exception {
        IngredientCategory ingredientCategory = ingredientsService.createIngredientCategory(request.getName(), request.getRestaurantId());

        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientItemRequest request
    ) throws Exception {
        IngredientsItem ingredientsItem = ingredientsService.createIngredientsItem(request.getRestaurantId(),request.getName(), request.getIngredientCategoryId());

        return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
    }

     @PostMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long id
    ) throws Exception {
        IngredientsItem ingredientsItem = ingredientsService.updateStock(id);

        return new ResponseEntity<>(ingredientsItem, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(
            @PathVariable Long id
    ) {
        List<IngredientsItem> ingredientsItems = ingredientsService.findRestaurantIngredients(id);

        return new ResponseEntity<>(ingredientsItems, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientCategory> ingredientCategories = ingredientsService.findIngredientCategoryByRestaurantId(id);

        return new ResponseEntity<>(ingredientCategories, HttpStatus.OK);
    }

}
