package com.project.rafe.domain.Recipe.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class RecipeStep {
    private String text;
    private String img;

    public RecipeStep(String text) {
        this(text, null);
    }
}
