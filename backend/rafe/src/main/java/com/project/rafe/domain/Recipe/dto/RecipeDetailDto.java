package com.project.rafe.domain.Recipe.dto;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.RecipeIngredient.IngredientFullDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDetailDto {

    private Long recipeId;
    private String recipeTitle;
    private String recipeMainImg;
    private Integer recipeLike;
    private Double matchingRate;

    private List<IngredientFullDto> totalIgList;
    private List<RecipeStep> recipeStep;
    //1이면 채운 하트 0이면 빈 하트


    @Builder
    public RecipeDetailDto(Recipe recipe, Integer recipeLike, Double matchingRate, List<IngredientFullDto> totalIgList) {
        this.recipeId = recipe.getRecipeId();
        this.recipeTitle = recipe.getRecipeTitle();
        this.recipeMainImg = recipe.getRecipeMainImg();
        this.recipeStep = getRecipeSteps(recipe);
        this.recipeLike = recipeLike;
        this.matchingRate = matchingRate;
        this.totalIgList = totalIgList;
    }

    private List<RecipeStep> getRecipeSteps(Recipe recipe) {
        List<RecipeStep> result = new ArrayList();
        List<String> recipeSteps = recipe.getRecipeStep();
        List<String> recipeStepImgs = recipe.getRecipeStepImg();
        System.out.println(recipeStepImgs.size()+"error???"+recipeSteps.size());


        if(recipeStepImgs.size()<recipeSteps.size()){
            for (int i = 0; i < recipeSteps.size(); i++) {
                result.add(new RecipeStep(recipeSteps.get(i)));
            }
        }
        else {
            for (int i = 0; i < recipeSteps.size(); i++) {
                result.add(new RecipeStep(recipeSteps.get(i), recipeStepImgs.get(i)));
            }
        }


        return result;
    }
}
