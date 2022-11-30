package com.project.rafe.domain.RecipeIngredient;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "ingredient_count")
    private String igCount;

    @Builder
    public RecipeIngredient(Recipe recipe, Ingredient ingredient, String igCount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.igCount = igCount;
    }

}
