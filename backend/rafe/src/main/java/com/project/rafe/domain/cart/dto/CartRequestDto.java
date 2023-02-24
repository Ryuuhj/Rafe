package com.project.rafe.domain.cart.dto;

import com.project.rafe.domain.cart.Cart;
import com.project.rafe.domain.ingredient.Ingredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartRequestDto {

    private Long userId;
    private Long igId;

    @Builder
    public CartRequestDto(Long userId, Long igId) {
        this.userId = userId;
        this.igId = igId;
    }

    public Cart toEntity(Ingredient ingredient) {
        return Cart.builder()
                .userId(userId)
                .ingredient(ingredient)
                .build();
    }

}
