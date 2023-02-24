package com.project.rafe.controller;

import com.project.rafe.domain.storage.dto.AddStorageReqDto;
import com.project.rafe.service.AllergyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class AllergyController {

    private static final Logger logger = LoggerFactory.getLogger(AllergyController.class);
    private final AllergyService allergyService;

    //0. 알러지 추가할 재료 조회 -> storage service에 구현
    //1. 알러지 재료 추가
    @PostMapping("/allergy/insert")
    public ResponseEntity<?> addAllergy(@RequestBody @Valid AddStorageReqDto request) {
        if (allergyService.addItem(request)) {
            return allergyService.getAllList(request.getUserId());
        }
        return ResponseEntity.badRequest().body("add fail");
    }

    //2. 알러지 목록 조회
    @GetMapping("/allergy/{user-id}")
    public ResponseEntity<Map<String, Object>> showAllergyList(@PathVariable("user-id") Long userId) {
        return allergyService.getAllList(userId);
    }
    //3. 알러지 삭제
    @DeleteMapping("/allergy/{user-id}/{ig-id}")
    public ResponseEntity<?> deleteAllergy(@PathVariable("user-id") Long userId, @PathVariable("ig-id") Long igId) {
        if (allergyService.deleteItem(userId, igId)) {
            return allergyService.getAllList(userId);
        }
        return ResponseEntity.badRequest().body("delete fail");
    }

    //
}
