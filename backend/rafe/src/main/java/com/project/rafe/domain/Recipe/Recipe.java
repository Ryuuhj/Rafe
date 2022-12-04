package com.project.rafe.domain.Recipe;

import com.project.rafe.domain.RecipeIngredient.RecipeIngredient;
import com.project.rafe.domain.StringListConverter;
import com.project.rafe.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "recipe_title", nullable = false)
    private String recipeTitle;

    @Column(name = "recipe_mimg", nullable = false)
    private String recipeMainImg;


    @Column(name = "recipe_category", nullable = false)
    private Long recipeCategory;
    //recipe name
    //recipe count --> recipe_ingredient table에 매핑
    @Column(name = "lactose")
    private Long lactose;

    @Column(name = "caffeine")
    private Long caffeine;

    @Lob
    @Column(length = 10000)
    @Convert(converter = StringListConverter.class)
    private List<String> recipeStep;

    @Lob
    @Column(length = 10000)
    @Convert(converter = StringListConverter.class)
    private List<String> recipeStepImg;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<RecipeIngredient> igList = new ArrayList<>();

    @Builder
    public Recipe(String recipeTitle, String recipeMainImg, Long recipeCategory,
                  Long lactose, Long caffeine, List<String> recipeStep, List<String> recipeStepImg) {
        this.recipeTitle = recipeTitle;
        this.recipeMainImg = recipeMainImg;
        this.recipeCategory = recipeCategory;
        this.lactose = lactose;
        this.caffeine = caffeine;
        this.recipeStep = recipeStep;
        this.recipeStepImg = recipeStepImg;
    }

}
