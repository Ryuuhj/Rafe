package com.project.rafe.repository;

import com.project.rafe.domain.Recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findRecipeByRecipeTitle(String recipeTitle);

    Optional<Recipe> findRecipeByRecipeId(Long recipeId);
}
