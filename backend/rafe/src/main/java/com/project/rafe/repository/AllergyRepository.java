package com.project.rafe.repository;

import com.project.rafe.domain.allergy.Allergy;
import com.project.rafe.domain.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    List<Allergy> findAllByUserId(Long userId);

    @Query("select a.ingredient.igId from Allergy as a where a.userId=:uid")
    List<Long> findIgIdByUserId(@Param("uid") Long userId);
    //네이티브쿼리는 개쓰레기다

    Optional<Allergy> findByUserIdAndIngredient(Long userId, Ingredient ingredient);

    Boolean existsByUserIdAndIngredient(Long userId, Ingredient ingredient);

}
