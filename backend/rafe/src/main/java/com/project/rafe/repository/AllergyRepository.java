package com.project.rafe.repository;

import com.project.rafe.domain.allergy.Allergy;
import com.project.rafe.domain.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    List<Allergy> findAllByUserId(Long userId);

    Optional<Allergy> findByUserIdAndIngredient(Long userId, Ingredient ingredient);

    Boolean existsByUserIdAndIngredient(Long userId, Ingredient ingredient);

}
