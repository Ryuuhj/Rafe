package com.project.rafe.service;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.Recipe.RecipeLike;
import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.domain.user.Users;
import com.project.rafe.repository.RecipeLikeRepository;
import com.project.rafe.repository.RecipeRepository;
import com.project.rafe.repository.UserRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepo;
    private final RecipeLikeRepository recipeLikeRepo;
    private final UserRepository userRepository;
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
