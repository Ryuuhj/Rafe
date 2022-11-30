package com.project.rafe.repository;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.RecipeIngredient.RecipeIngredient;
import com.project.rafe.domain.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

    //@Query(value = "SELECT Recipe FROM RecipeIngredient r WHERE r.ingredient = :#{#ingredient}") @Param("ingredient")
    List<RecipeIngredient> findAllByIngredient(Ingredient ingredient);
}
