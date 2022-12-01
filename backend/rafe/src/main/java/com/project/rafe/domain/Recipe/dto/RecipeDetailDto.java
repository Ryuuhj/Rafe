package com.project.rafe.domain.Recipe.dto;

import com.project.rafe.domain.Recipe.Recipe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecipeDetailDto {

    private Long recipeId;
    private String recipeTitle;
    private String recipeMainImg;
    private List<String> recipeStep;
    private List<String> recipeStepImg;
    private Integer recipeLike;

    @Builder
    public RecipeDetailDto(Recipe recipe, Integer recipeLike) {
        this.recipeId = recipe.getRecipeId();
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeStep = recipe.getRecipeStep();
        this.recipeStepImg = recipe.getRecipeStepImg();
        this.recipeLike = recipeLike;
    }



}
