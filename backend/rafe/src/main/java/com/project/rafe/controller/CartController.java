package com.project.rafe.controller;

import com.project.rafe.domain.cart.dto.CartRequestDto;
import com.project.rafe.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    //1. 카트 조회
    @GetMapping("/cart/{user-id}")
    public ResponseEntity<?> showCart(@PathVariable("user-id") Long userId) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", cartService.getCartList(userId));
        response.put("message", "success");
        return ResponseEntity.ok().body(response);
    }

    //2. 카트 담기
    @PostMapping("/cart")
    public ResponseEntity<?> addCart (@RequestBody CartRequestDto req) {
        Map<String, Object> result = new HashMap<>();

        if(cartService.existItem(req.getUserId(), req.getIgId()).isEmpty()){
            if(cartService.addCart(req).equals("success")){
                result.put("cart", 1);
            }
            else {
                result.put("cart", null); //fail인 경우
            }
        } else {
            result.put("cart", 0);}
        return ResponseEntity.ok().body(result);
    }

    //3. 카트 삭제
    @DeleteMapping("/cart/{user-id}/{ig-id}")
    public ResponseEntity<?> deleteCart(@PathVariable("user-id") Long userId,
                                        @PathVariable("ig-id") Long igId) {
        Map<String, Object> response = new HashMap<>();
        //해당 카트 삭제
        cartService.deleteCart(userId, igId);
        //갱신 목록 반환
        response.put("data", cartService.getCartList(userId));
        response.put("message", "success");
        return ResponseEntity.ok().body(response);
    }
}
