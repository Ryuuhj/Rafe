package com.project.rafe.repository;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.Recipe.RecipeLike;
import com.project.rafe.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeLikeRepository extends JpaRepository<RecipeLike, Long> {

    RecipeLike findRecipeLikeByUserAndRecipe(Users user, Recipe recipe);

    List<RecipeLike> findAllByUser(Users user);

    Boolean existsByUserAndRecipe(Users user, Recipe recipe);


}
