package com.project.rafe.domain.cart.dto;

import com.project.rafe.domain.cart.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartResponseDto {

    private Long igId;
    private String igName;

    @Builder
    public CartResponseDto(Cart cart) {
        this.igId = cart.getIngredient().getIgId();
        this.igName = cart.getIngredient().getIgName();
    }
}
