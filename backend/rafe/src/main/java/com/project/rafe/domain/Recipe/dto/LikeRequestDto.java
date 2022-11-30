package com.project.rafe.domain.Recipe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.ListResourceBundle;

@Getter
@NoArgsConstructor
public class LikeRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long recipeId;

    @Builder
    public LikeRequestDto(Long userId, Long recipeId) {
        this.userId = userId;
        this.recipeId = recipeId;
    }
}
