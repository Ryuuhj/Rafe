package com.project.rafe.controller;

import com.project.rafe.domain.ingredient.dto.DetailRequsetDto;
import com.project.rafe.domain.ingredient.dto.IngredientDetailDto;
import com.project.rafe.domain.storage.Storage;
import com.project.rafe.repository.IngredientRepository;
import com.project.rafe.repository.StorageRepository;
import com.project.rafe.service.IngredientService;
import com.project.rafe.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class IngredientController {
    private static final Logger logger = LoggerFactory.getLogger(IngredientController.class);
    private final IngredientService ingredientService;
    private final StorageService storageService;

    //0. ingredient Json 파일 저장
    @GetMapping("/read")
    public void readIngredientJson() {
        ingredientService.read();
    }

    //1. 재료 검색 반환
    @GetMapping("/ingredient")
    public ResponseEntity<?> searchIngredient(@RequestParam("id") Long userId,
                                              @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
        return ingredientService.searchIngredient(userId, keyword);
    }

    //2. 재료 상세페이지
    @PostMapping("/ingredient/detail")
    public ResponseEntity<IngredientDetailDto> showDetail(@RequestBody DetailRequsetDto detailtDto) {
        IngredientDetailDto result = ingredientService.showDetail(detailtDto.getUserId(), detailtDto.getIngredientId());
        return ResponseEntity.ok().body(result);
    }
}
