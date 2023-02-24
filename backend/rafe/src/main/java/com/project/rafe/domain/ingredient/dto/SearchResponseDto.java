package com.project.rafe.domain.ingredient.dto;

import com.project.rafe.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchResponseDto {

    private Long igId;

    private String igName;

    private Boolean exists;

    @Builder
    public SearchResponseDto(Ingredient ingredient, Boolean exists) {
        this.igId = ingredient.getIgId();
        this.igName = ingredient.getIgName();
        this.exists = exists;
    }

    //이후 exists 값 세팅 해 response
    public void setExists(Boolean exists) {
        this.exists = exists;
    }
}
