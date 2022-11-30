package com.project.rafe.service;

import com.project.rafe.domain.Recipe.Recipe;
import com.project.rafe.domain.RecipeIngredient.RecipeIngredient;
import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.repository.IngredientRepository;
import com.project.rafe.repository.RecipeIngredientRepository;
import com.project.rafe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;

@RequiredArgsConstructor
@Service
public class RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepo;
    private final RecipeRepository recipeRepo;
    private final IngredientRepository ingredientRepo;

    public static final Logger logger = LoggerFactory.getLogger(RecipeIngredientService.class);





















    public void read_recipeIngredient(){
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
                //Recipe 1개 얻어옴 객체 생성 (재료 빼고 저장된 상태)
                JSONObject result = (JSONObject) recipeList.get(i);
                //recipe title-> recipe 객체 찾기
                Recipe recipe = recipeRepo.findRecipeByRecipeTitle((String) result.get("recipe_title"));
                if (recipe == null) {
                    continue;
                }
                // ingredient Name -> list니까 얘도 for문 돌려서 1개씩 찾아 저장
                JSONArray ingredientList = (JSONArray) result.get("recipe_ingredients");
                JSONArray countList = (JSONArray) result.get("recipe_count");
                for (int j = 0; j < ingredientList.size(); j++) {
                    //name으로 ingredient객체 찾기
                    Ingredient ingredient = ingredientRepo.findByIgName((String) ingredientList.get(j));
                    if (ingredient == null) {
                        System.out.println("ingredient가 왜 null이냐고ㅠㅠㅠㅠㅠ>>>>>>>"+(String) ingredientList.get(j));
                        continue;
                    }
                /*if (ingredient.size()>1) {
                    System.out.println("뭐임: " + (String) ingredient.get(1).getIgName());
                }
                if (ingredient.isEmpty()) {
                    continue;
                }*/
                    String count = (String) countList.get(j);
                    recipeIngredientRepo.save(RecipeIngredient.builder()
                            .recipe(recipe)
                            .ingredient(ingredient)
                            .igCount(count)
                            .build());
                }
            }
        } catch (Exception e) {
            logger.error("JSON File 읽어오기 실패 : {}", e);
        }
    }
}
