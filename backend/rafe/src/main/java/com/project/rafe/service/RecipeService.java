package com.project.rafe.service;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.Recipe.RecipeLike;
import com.project.rafe.domain.Recipe.dto.RecipeDetailDto;
import com.project.rafe.domain.RecipeIngredient.IngredientFullDto;
import com.project.rafe.domain.RecipeIngredient.RecipeIngredient;
import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.domain.storage.Storage;
import com.project.rafe.domain.user.Users;
import com.project.rafe.repository.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepo;
    private final RecipeIngredientRepository recipeIngredientRepo;
    private final RecipeLikeRepository recipeLikeRepo;
    private final UserRepository userRepository;
    private final StorageRepository storageRepo;
    public static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Transactional
    public Map<String,Object> pressLike(Long userId, Long recipeId) {
        Map<String, Object> result = new HashMap<>();
        //넘겨받은 userId, recipeId로 각각 조회에 사용할 객체 찾기 + 예외처리
        Users user = userRepository.findUserByUserId(userId).orElseThrow(RuntimeException::new);
        Recipe recipe = recipeRepo.findRecipeByRecipeId(recipeId).orElseThrow(RuntimeException::new);

        //recipeLike 객체 조회해서 없으면 save -> 1 반환
        try {
            RecipeLike recipeLike = recipeLikeRepo.findRecipeLikeByUserAndRecipe(user, recipe);
            if (recipeLike == null) {
                recipeLikeRepo.save(new RecipeLike(user, recipe));
                result.put("filledHeart", 1);
            } else {  //recipeLike 객체 조회해서 있으면 delete -> 0 반환
                recipeLikeRepo.delete(recipeLike);
                result.put("filledHeart", 0);}
        } catch (Exception e) {
            logger.error("recipeLike 에러 >>> "+e.getMessage());
            result.put("error", "Like 조회 에러");
        }
        return result;
    }

    @Transactional
    public RecipeDetailDto showRecipeDetail(Long userId, Long recipeId) {
        List<IngredientFullDto> r_i_list = new ArrayList<>();
        Double count = 0.0; //일치하는 재료 개수 카운팅
        //1. 레포에서 조회용 객체 가져옴
        Users user = userRepository.findUserByUserId(userId).get();
        Recipe recipe = recipeRepo.findRecipeByRecipeId(recipeId).get();
        //2. 좋아요 여부 판별해 int로 저장
        Integer pressLike = 0;
        if (recipeLikeRepo.existsByUserAndRecipe(user, recipe)) {
            pressLike = 1;
        }
        //3. user storage list 찾아서 ingredient List로 매핑
        List<Ingredient> userIgList = storageRepo.findAllByUserId(userId).stream()
                .map(Storage::getIngredient).collect(Collectors.toList());
        //4. recipeIngredient Repo에서 recipe 객체로 RecipeIngredientList 가져오기
        List<RecipeIngredient> rpIgList = recipeIngredientRepo.findAllByRecipe(recipe);
        //5. 사용자 소지 재료에 포함된 재료면 storage 1로 세팅해 dto 생성
        try {
            for (RecipeIngredient ri : rpIgList) {
                Integer storageCheck = 0;
                if (userIgList.contains(ri.getIngredient())) {
                    storageCheck = 1;
                    count++;
                }
                r_i_list.add(IngredientFullDto.builder()
                        .ingredient(ri.getIngredient())
                        .igCount(ri.getIgCount())
                        .storageCheck(storageCheck)
                        .build());
            }
        } catch (RuntimeException e) {
            logger.error("Error >>> " + e.getMessage());
        }
        //매칭률 구하기
        Double correct = (count / rpIgList.size()) * 100;

        return RecipeDetailDto.builder()
                .recipe(recipe)
                .recipeLike(pressLike)
                .matchingRate(correct)
                .totalIgList(r_i_list)
                .build();
    }








    //Json 레시피 저장
    public void read_recipe(){
        JSONParser parser = new JSONParser();
        try {
            // JSON 파일 이름
            String fileName = "recipe_sample.json";

            // JSON 파일 주소 => 각 Local PC마다 경로 다름
            String fileLoc = "C:\\git\\Rafe\\backend\\rafe\\src\\main\\resources\\json\\" + fileName;
            //"C:\\BigData_PJT\\backend\\Project_A204\\src\\main\\resources\\json\\" + fileName;

            //json 파일 읽어와 저장
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileLoc));

            // "recipe_list" key 값으로 모든 recipe 정보 가져옴
            JSONArray recipeList = (JSONArray) jsonObject.get("recipe_list");
            for (int i = 0; i < recipeList.size(); i++) {
                // recipe 객체 생성
                JSONObject result = (JSONObject) recipeList.get(i);
                Recipe isSkip = recipeRepo.findRecipeByRecipeTitle((String) result.get("recipe_title"));
                //중복인 경우 (!null) -> 처리 X
                if ((isSkip == null)||(result.get("recipe_title").equals(null))) {
                    //System.out.println("ho");
                    recipeRepo.save(Recipe.builder()
                            .recipeTitle((String) result.get("recipe_title"))
                            .recipeMainImg((String) result.get("recipe_main_img"))
                            .recipeCategory((Long) result.get("recipe_category"))
                            .recipeStep((List<String>) result.get("recipe_step"))
                            .recipeStepImg((List<String>) result.get("recipe_step_img"))
                            .build());
                }
            }
        } catch (Exception e) {
            logger.error("JSON File 읽어오기 실패 : {}", e);
        }
    }
}
