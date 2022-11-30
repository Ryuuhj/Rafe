package com.project.rafe.domain.ingredient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DetailRequsetDto {

    private Long userId;
    private Long ingredientId;

    @Builder
    public DetailRequsetDto(Long userId, Long ingredientId) {
        this.userId = userId;
        this.ingredientId = ingredientId;
    }
}
