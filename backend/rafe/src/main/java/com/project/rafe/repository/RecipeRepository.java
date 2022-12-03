package com.project.rafe.repository;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.Recipe.dto.SimpleRecipeDto;
import com.project.rafe.repository.query.RecipeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    //JpaRepository를 상속하는 RecipeRepository가 RecipeRepositoryCustom 인터페이스 상속하게 함
    //RecipeRepositoryCustomImpl에 작성된 QueryDSL 코드를 RecipeRepository가 자동으로 사용 가능

    Recipe findRecipeByRecipeTitle(String recipeTitle);

    List<Recipe> findAllByRecipeId(Long recipeId);

    List<Recipe> findAllByRecipeCategory(Long categoryId);
    List<Recipe> findAll();

    Optional<Recipe> findRecipeByRecipeId(Long recipeId);
}
