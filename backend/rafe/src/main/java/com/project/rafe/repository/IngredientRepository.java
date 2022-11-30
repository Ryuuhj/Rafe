package com.project.rafe.repository;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByIgId(Long igId);

    Boolean existsByIgName(String igName);

    Ingredient findByIgName(String igName);


    @Query(value = "SELECT i FROM Ingredient i WHERE i.igName LIKE %:keyword%")
    List<Ingredient> searchByNameLike(@Param("keyword") String keyword);

}
