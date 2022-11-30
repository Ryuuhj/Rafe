package com.project.rafe.domain.storage.dto;

import com.project.rafe.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ChangeFastDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long igId;

    @Builder
    public ChangeFastDto(Long userId, Long igId) {
        this.userId = userId;
        this.igId = igId;
    }
}
