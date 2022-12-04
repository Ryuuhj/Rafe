package com.project.rafe.domain.Recipe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDetailReqDto {

    private Long userId;
    private Long recipeId;

    @Builder
    public RecipeDetailReqDto(Long userId, Long recipeId) {
        this.userId = userId;
        this.recipeId = recipeId;
    }
}
