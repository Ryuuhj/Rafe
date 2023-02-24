package com.project.rafe.domain.RecipeIngredient;

import com.project.rafe.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientFullDto {

    private Long igId;
    private String igName;
    private String igCount;
    private Integer storage;
    private Integer cart;

    @Builder
    public IngredientFullDto(Ingredient ingredient, String igCount, Integer storageCheck,Integer cartCheck) {
        this.igId = ingredient.getIgId();
        this.igName = ingredient.getIgName();
        this.igCount = igCount;
        this.storage = storageCheck;
        this.cart = cartCheck;
    }
}
