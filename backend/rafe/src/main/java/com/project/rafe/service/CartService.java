package com.project.rafe.service;

import com.project.rafe.domain.cart.Cart;
import com.project.rafe.domain.cart.dto.CartRequestDto;
import com.project.rafe.domain.cart.dto.CartResponseDto;
import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.repository.CartRepository;
import com.project.rafe.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {

    public static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private final IngredientRepository ingredientRepository;
    private final CartRepository cartRepository;

    //0. 카트 존재 여부
    public Optional<Cart> existItem(Long userId, Long igId){
        Ingredient ingredient = ingredientRepository.findByIgId(igId).orElseThrow(NullPointerException::new);
        return cartRepository.findByUserIdAndIngredient(userId, ingredient);
    }
    //1. 카트 목록 조회
    public ResponseEntity<List<CartResponseDto>> getCartList(Long userId) {
        //카트 리스트 조회
        List<Cart> carts = cartRepository.findAllByUserId(userId);
        List<CartResponseDto> cartList = new ArrayList<>();
        if(!(carts.isEmpty())){
            for (Cart c : carts) {
                cartList.add(CartResponseDto.builder()
                        .cart(c).build());
            }
        }
        return ResponseEntity.ok().body(cartList);
    }

    //2. 카트 담기
    @Transactional
    public String addCart(CartRequestDto req){
        String saveResult;
        //해당 재료 찾아서
        Ingredient ingredient = ingredientRepository.findByIgId(req.getIgId()).orElseThrow(IllegalArgumentException::new);
        try {
            //req + 재료로 엔티티 save
            cartRepository.save(req.toEntity(ingredient));
            saveResult = "success";
        } catch (Exception e) {
            logger.error("cart save Error :" + e.getMessage());
            saveResult = "fail";
        }
        return saveResult;
    }

    //3. 카트 삭제
    @Transactional
    public void deleteCart(Long userId, Long igId) {
        //카트 존재 조회
        Cart cart = existItem(userId, igId).orElseThrow(NullPointerException::new);
        cartRepository.delete(cart);
    }

}
