package com.project.rafe.controller;

import com.project.rafe.service.IngredientService;
import com.project.rafe.service.RecipeIngredientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeIngredientController {

    private final RecipeIngredientService recipeIngredientService;
    public static final Logger logger = LoggerFactory.getLogger(RecipeIngredientController.class);

    @GetMapping("/recipe/ingredient/read")
    public void readRI() {
        try {
            recipeIngredientService.read_recipeIngredient();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
