package com.project.rafe.repository;

import com.project.rafe.domain.Recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findRecipeByRecipeTitle(String recipeTitle);

    List<Recipe> findAllByRecipeId(Long recipeId);

    Optional<Recipe> findRecipeByRecipeId(Long recipeId);
}
