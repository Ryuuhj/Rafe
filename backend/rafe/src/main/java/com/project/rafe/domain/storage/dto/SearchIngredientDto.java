package com.project.rafe.domain.storage.dto;

import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.domain.storage.Storage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class SearchIngredientDto {
    private Long igId;
    private String igName;

    @Builder
    public SearchIngredientDto(Storage storage) {
        this.igId = storage.getIngredient().getIgId();
        this.igName = storage.getIngredient().getIgName();
    }

    /*public List<SearchIngredientDto> ListEntityToDto(List<Storage> storage) {
        return storage.stream()
                .map(SearchIngredientDto::new)
                .collect(Collectors.toList());
    }*/
}
