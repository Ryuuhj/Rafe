package com.project.rafe.service;

import com.project.rafe.domain.Recipe.dto.SimpleRecipeDto;
import com.project.rafe.domain.RecipeIngredient.RecipeIngredient;
import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.domain.ingredient.dto.IngredientDetailDto;
import com.project.rafe.domain.ingredient.dto.SearchResponseDto;
import com.project.rafe.domain.ingredient.dto.naver.ItemSearchResDto;
import com.project.rafe.repository.IngredientRepository;
import com.project.rafe.repository.RecipeIngredientRepository;
import com.project.rafe.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
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
        //3, 네이버 최저가 검색
        List<ItemSearchResDto> searchList = new ArrayList<>();
        //예외처리
        try {
            searchList = naverProductList(ingredient.getIgName());
        } catch (ParseException e) {
            logger.info(e.getMessage());
        }

        return IngredientDetailDto.builder()
                .ingredient(ingredient)
                .cart(0)
                .recipeList(simpelList)
                .items(searchList)
                .build();
    }

    public List<ItemSearchResDto> naverProductList(String igName) throws ParseException {
        //1. URL 세팅해서 보내기
        String url = "https://openapi.naver.com/";
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .path("v1/search/shop.json")
                .queryParam("query", igName)
                .queryParam("display", 3)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        logger.info("uri : {}", uri);
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","_Vb4pLNYbpuZ3PuPRaPB")
                .header("X-Naver-Client-Secret","_8gfF5IPxJ")
                .build();
        //requestEntity + header 세팅해서 requestentity를 exchange에 보내기 (string으로 json 결과 받아옴)
        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        //받아온 json 형식의 string을 object array 형태로 변환
        List<ItemSearchResDto> itemSearchList = fromJSONtoItemSearch(result.getBody());


        logger.info("result ={}", itemSearchList);
        return itemSearchList;
    }

    public List<ItemSearchResDto> fromJSONtoItemSearch(String result) throws ParseException {
        // 문자열 정보를 JSONObject로 바꾸기 (파라미터가 String 불가능???)
        //json parser로 obj 전환 후 jsonobject 전환
        JSONParser parser = new JSONParser();
        Object resultObj = parser.parse(result);
        JSONObject rjson = (JSONObject) resultObj;
        //System.out.println(rjson);
        // JSONObject에서 items 배열 꺼내기
        // JSON 배열이기 때문에 보통 배열이랑 다르게 활용해야한다.
        JSONArray naverProducts = (JSONArray) rjson.get("items");
        //System.out.println(naverProducts);
        List<ItemSearchResDto> itemSearchList = new ArrayList<>();
        for (int i = 0; i < naverProducts.size(); i++) {
            JSONObject naverProductsJson = (JSONObject) naverProducts.get(i);
            ItemSearchResDto itemDto = new ItemSearchResDto(naverProductsJson);
            itemSearchList.add(itemDto);
        }
        return itemSearchList;
    }

    public void read() {
        JSONParser parser = new JSONParser();
        try {
            // JSON 파일 이름
            String fileName = "recipe_ingredient_sample.json";
            // JSON 파일 주소 => 각 Local PC마다 경로 다름
            String fileLoc = "C:\\git\\Rafe\\backend\\rafe\\src\\main\\resources\\json\\" + fileName;
            //String fileLoc = "C:\\Users\\82104\\Desktop\\Rafe\\backend\\rafe\\src\\main\\resources\\json\\" + fileName;
            //String fileLoc = "C:\\projects\\git\\Rafe\\backend\\rafe\\src\\main\\resources\\json\\" + fileName;


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
