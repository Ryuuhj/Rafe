package com.project.rafe.domain.Recipe.dto;

import com.project.rafe.domain.Recipe.Recipe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimpleRecipeDto {

    private Long recipeId;
    private String recipeTitle;
    private String recipeImg;

    @Builder
    public SimpleRecipeDto(Recipe recipe) {
        this.recipeId = recipe.getRecipeId();
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeImg = recipe.getRecipeMainImg();
    }

    @Builder
    public SimpleRecipeDto(Long recipeId, String recipeTitle, String recipeImg) {
        this.recipeId = recipeId;
        this.recipeTitle = recipeTitle;
        this.recipeImg = recipeImg;
    }
}
