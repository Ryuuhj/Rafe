package com.project.rafe.domain.ingredient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DetailRequsetDto {

    private Long userId;
    private Long igId;

    @Builder
    public DetailRequsetDto(Long userId, Long igId) {
        this.userId = userId;
        this.igId = igId;
    }
}
