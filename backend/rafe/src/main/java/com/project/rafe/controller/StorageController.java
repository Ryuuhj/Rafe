package com.project.rafe.controller;

import com.project.rafe.domain.storage.dto.AddStorageReqDto;
import com.project.rafe.domain.storage.dto.ChangeFastDto;
import com.project.rafe.service.IngredientService;
import com.project.rafe.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StorageController {

    private static final Logger logger = LoggerFactory.getLogger(IngredientController.class);
    private final IngredientService ingredientService;
    private final StorageService storageService;

    //1. 내 창고에 재료 추가
    @PostMapping("/storage/insert")
    public ResponseEntity<?> addStorageItem(@RequestBody @Valid AddStorageReqDto addStorageReqDto) {
        return storageService.addItem(addStorageReqDto);
    }
    //2. 창고 조회
    @GetMapping("/storage/{user-id}")
    public ResponseEntity<?> showStorageItem(@PathVariable("user-id")Long userId) {
        return storageService.showStorage(userId);
    }
    //3. 재료 삭제
    @DeleteMapping("/storage/{user-id}/{ig-id}")
    public ResponseEntity<Long> deleteStorageItem(@PathVariable("user-id") Long userId ,@PathVariable("ig-id") Long igId){
        return storageService.deleteItem(userId, igId);
    }

    @PatchMapping("/storage/fast")
    public ResponseEntity<?> changeIngredientStatus(@RequestBody @Valid ChangeFastDto changeFastDto) {
        String result = storageService.changeStatus(changeFastDto);
        return storageService.showStorage(changeFastDto.getUserId());
        //return "/storage/" + changeFastDto.getUserId();
    }
}