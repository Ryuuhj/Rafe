package com.project.rafe.repository;

import com.project.rafe.domain.cart.Cart;
import com.project.rafe.domain.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUserId(Long userId);

    Optional<Cart> findByUserIdAndIngredient(Long userId, Ingredient ingredient);
}
