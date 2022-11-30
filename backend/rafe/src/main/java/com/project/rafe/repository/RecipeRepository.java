package com.project.rafe.repository;

import com.project.rafe.domain.Recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findRecipeByRecipeTitle(String recipeTitle);
}
