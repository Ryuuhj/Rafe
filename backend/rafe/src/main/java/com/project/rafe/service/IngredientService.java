package com.project.rafe.service;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.Recipe.dto.SimpleRecipeDto;
import com.project.rafe.domain.RecipeIngredient.RecipeIngredient;
import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.domain.ingredient.dto.IngredientDetailDto;
import com.project.rafe.domain.ingredient.dto.SearchResponseDto;
import com.project.rafe.repository.IngredientRepository;
import com.project.rafe.repository.RecipeIngredientRepository;
import com.project.rafe.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class IngredientService {
    private final IngredientRepository ingredientRepo;
    private final RecipeIngredientRepository recipeIngredientRepo;

    private final StorageRepository storageRepo;

    public static final Logger logger = LoggerFactory.getLogger(IngredientService.class);
    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String CAUSE = "cause";

    //내 창고 - 재료 추가 검색
    @Transactional
    public ResponseEntity<?> searchIngredient(Long userId, String keyword) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        //1. Ingredient에서 keyword로 검색 -> List<Ingredient>로 반환
        List<Ingredient> ingredientList = ingredientRepo.searchByNameLike(keyword);
        if(!ingredientList.isEmpty()){
            //2. list 돌면서 igid, igName으로 객체 생성 +
            // Store에 userid, igid로 exists 확인 -> 있으면 true 입력
            List<SearchResponseDto> resultList = new ArrayList<>();
            for (Ingredient i:ingredientList) {
                Boolean is_exist = storageRepo.existsByUserIdAndIngredient(userId, i);
                resultList.add(new SearchResponseDto(i, is_exist));
                }
            resultMap.put(MESSAGE, SUCCESS);
            resultMap.put("search_result", resultList);
            status = HttpStatus.OK;

        }else {//반환 값 없을 경우
            resultMap.put(MESSAGE, FAIL);
            resultMap.put(CAUSE, "NO RESULT");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    //2. 재료 상세페이지
    @Transactional
    public IngredientDetailDto showDetail(Long userId, Long igId) {
        //1. igredient repo에서 igId로 검색 후 optional 반환
        Ingredient ingredient = ingredientRepo.findByIgId(igId)
                .orElseThrow(() -> new IllegalArgumentException("없는 재료 요청"));
        //2.관련 레시피 object 최대 3개 뽑아오기 by ingredient
        List<RecipeIngredient> recipeList = recipeIngredientRepo.findAllByIngredient(ingredient);
        //뽑아서 Dto에 매핑시켜 저장할 리스트 생성(최종 반환본)
        List<SimpleRecipeDto> simpelList = new ArrayList<>();
        Integer i=0;
        //recipeList.stream().forEach(recipe -> simpelList.add(new SimpleRecipeDto(recipe)));
        try {
            for (RecipeIngredient r : recipeList) {
                if (i >= 2) {
                    break;
                }
                simpelList.add(new SimpleRecipeDto(r.getRecipe()));
                i++;
            }
        } catch (Exception e) {
            logger.error("RecipeList error >>>" + e.getMessage());
        }
        return IngredientDetailDto.builder()
                .ingredient(ingredient)
                .cart(0)
                .recipeList(simpelList)
                .build();
    }



    public void read() {
        JSONParser parser = new JSONParser();
        try {
            // JSON 파일 이름
            String fileName = "recipe_ingredient_sample.json";
            // JSON 파일 주소 => 각 Local PC마다 경로 다름
            String fileLoc = "C:\\git\\Rafe\\backend\\rafe\\src\\main\\resources\\json\\" + fileName;
            //String fileLoc = "C:\\Users\\82104\\Desktop\\Rafe\\backend\\rafe\\src\\main\\resources\\json\\" + fileName;
            //String fileLoc = "C:\\project\\git\\Rafe\\backend\\rafe\\src\\main\\resources\\json\\" + fileName;

            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileLoc));

            // "recipe_ig_s" key 값으로 모든 ingredient 정보 가져옴
            JSONArray ingredientList = (JSONArray) jsonObject.get("recipe_ig_s");
            for (int i = 0; i < ingredientList.size(); i++) {
                // ingredient 객체 생성
                String igName = (String) ingredientList.get(i);
                Boolean isSkip = ingredientRepo.existsByIgName(igName);
                if (isSkip) {
                    continue;
                }
                ingredientRepo.save(Ingredient.builder()
                        .igName(igName)
                        .build());
            }
        } catch (Exception e) {
            logger.error("JSON File 읽어오기 실패 : {}", e);
        }
    }


}
