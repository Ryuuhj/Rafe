package com.project.rafe.controller;

import com.project.rafe.domain.Recipe.dto.*;
import com.project.rafe.domain.Recipe.search.SearchCondDto;
import com.project.rafe.service.IngredientService;
import com.project.rafe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    //레시피 좋아요 버튼
    @PostMapping("/recipe/like")
    public ResponseEntity<Map<String ,Object>> press_like(@Valid @RequestBody LikeRequestDto likeRequestDto) {
        //Map<String, Object> result;
        //request로 유효성 검사 했으니 바로 검사
        Long userId = likeRequestDto.getUserId();
        Long recipeId = likeRequestDto.getRecipeId();
        return ResponseEntity.ok().body(recipeService.pressLike(userId, recipeId));
    }
    //유저가 누른 좋아요 목록 조회
    @GetMapping("/recipe/like/{user-id}")
    public ResponseEntity<?> showRecipeLike(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok().body(recipeService.showRecipeLike(userId));
    }

    //레시피 세부사항
    @PostMapping("/recipe/detail")
    public ResponseEntity<?> showRecipeDetail(@RequestBody RecipeDetailReqDto request) {
        RecipeDetailDto result = recipeService.showRecipeDetail(request.getUserId(), request.getRecipeId());
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(result);
    }

    //카테고리별 조회 (전체 조회 = 10)
    @GetMapping("/recipe/{category-id}")
    public ResponseEntity<?> showRecipeList(@PathVariable("category-id") Long categoryId) {
        List<SimpleRecipeDto> resultList = recipeService.searchRpByCategory(categoryId);
        if(resultList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(resultList);
    }

    //레시피 검색 처리 및 반환
    @PostMapping("/recipe/result")
    public List<SimpleRecipeDto> searchByCond(@RequestBody SearchCondDto condDto){
        logger.error("keyword?"+condDto.getKeyword()+";;");
        return recipeService.searchByCond(condDto);
    }

    //메인화면 레시피 인기순 출력
    @GetMapping("/main/like")
    public List<HotRecipeDto> showHotList(){
        return recipeService.showHotList();
    }

    //메인화면: 소비 우선 중심 추천
    @GetMapping("/main/fastuse/{user-id}")
    public List<FastUseRecipeDto> showFastUseList(@PathVariable("user-id") Long userId) {
        return recipeService.showFastUseList(userId);
    }


    //레시피 json파일 저장
    @GetMapping("/recipe/read")
    public void readRecipeJson (){
        recipeService.read_recipe();
    }
}
