package com.project.rafe.domain.Recipe.dto;

import com.project.rafe.domain.Recipe.Recipe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HotRecipeDto {

    private Long recipeId;
    private String recipeTitle;
    private String recipeImg;
    private Long likeCount;

    @Builder
    public HotRecipeDto(Recipe recipe) {
        this.recipeId = recipe.getRecipeId();
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeImg = recipe.getRecipeMainImg();
        this.likeCount = recipe.getLikeCount();
    }

    @Builder
    public HotRecipeDto(Long recipeId, String recipeTitle, String recipeImg, Long likeCount) {
        this.recipeId = recipeId;
        this.recipeTitle = recipeTitle;
        this.recipeImg = recipeImg;
        this.likeCount = likeCount;
    }
}
