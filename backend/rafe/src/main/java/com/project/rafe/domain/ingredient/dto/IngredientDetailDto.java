package com.project.rafe.domain.ingredient.dto;

import com.project.rafe.domain.Recipe.dto.SimpleRecipeDto;
import com.project.rafe.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class IngredientDetailDto {

    private Long ingredientId;
    private String ingredientName;
    private Integer cart;

    private List<SimpleRecipeDto> recipes;

    @Builder
    public IngredientDetailDto(Ingredient ingredient, Integer cart, List<SimpleRecipeDto> recipeList) {
        this.ingredientId = ingredient.getIgId();
        this.ingredientName = ingredient.getIgName();
        this.cart = cart;
        this.recipes = recipeList;
    }


}
