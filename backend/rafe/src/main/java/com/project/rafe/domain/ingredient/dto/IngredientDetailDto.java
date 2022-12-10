package com.project.rafe.domain.ingredient.dto;

import com.project.rafe.domain.Recipe.dto.SimpleRecipeDto;
import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.domain.ingredient.dto.naver.ItemSearchResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class IngredientDetailDto {

    private Long igId;
    private String igName;
    private Integer cart;
    private List<SimpleRecipeDto> recipes;

    private List<ItemSearchResDto> items;

    @Builder
    public IngredientDetailDto(Ingredient ingredient, Integer cart, List<SimpleRecipeDto> recipeList, List<ItemSearchResDto> items) {
        this.igId = ingredient.getIgId();
        this.igName = ingredient.getIgName();
        this.cart = cart;
        this.recipes = recipeList;
        this.items = items;
    }


}
