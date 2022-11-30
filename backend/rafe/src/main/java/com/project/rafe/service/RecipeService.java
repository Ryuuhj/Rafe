package com.project.rafe.service;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.repository.RecipeRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepo;
    public static final Logger logger = LoggerFactory.getLogger(RecipeService.class);









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
