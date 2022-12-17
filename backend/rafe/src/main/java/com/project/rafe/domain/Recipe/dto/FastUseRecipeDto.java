package com.project.rafe.domain.Recipe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FastUseRecipeDto {

    private Long recipeId;
    private String recipeTitle;
    private String recipeImg;
    private Long match;

    @Builder
    public FastUseRecipeDto(Long recipeId, String recipeTitle, String recipeImg, Long match) {
        this.recipeId = recipeId;
        this.recipeTitle = recipeTitle;
        this.recipeImg = recipeImg;
        this.match = match;
    }
}
