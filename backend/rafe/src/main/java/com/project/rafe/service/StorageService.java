package com.project.rafe.service;

import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.domain.storage.Storage;
import com.project.rafe.domain.storage.dto.AddStorageReqDto;
import com.project.rafe.domain.storage.dto.ChangeFastDto;
import com.project.rafe.domain.storage.dto.GetResponseDto;
import com.project.rafe.domain.storage.dto.SearchIngredientDto;
import com.project.rafe.repository.IngredientRepository;
import com.project.rafe.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageService extends MessageSetting{

    private final StorageRepository storageRepo;
    private final IngredientRepository ingredientRepo;

    public static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    //내 창고에 재료 추가 -> DB 업뎃
    @Transactional
    public String addItem(AddStorageReqDto addStorageReqDto) {
        //1. userId 꺼내
        Long userId = addStorageReqDto.getUserId();
        List<Long> idList = addStorageReqDto.getIgIdList();
        String result;
        //2. id로 재료 찾고 storage에 매핑해 save
        //List<String> notSave = new ArrayList<>();
        try {
            for (Long id : idList) {
                Ingredient ingredient = ingredientRepo.findByIgId(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 재료가 없습니다 id=" + id));
                if (storageRepo.existsByUserIdAndIngredient(userId, ingredient)) {
                    //notSave.add(ingredient.getIgName());
                    continue;
                } else {
                    storageRepo.save(new Storage(userId, ingredient));
                    //count++;
                }
            }
            result = "success";
        } catch (NullPointerException e) {
            logger.error("save error>>>" + e.getMessage());
            result = "fail";
        }
        return result;
    }

    //창고 조회
    @Transactional
    public ResponseEntity<Map<String,Object>> showStorage(Long userId) {
        HttpStatus status = null;
        Map<String, Object> resultMap = new HashMap<>();
        //1. id로 Storage에서 igredient 배열 가져오기
        List<Storage> storageList = storageRepo.findAllByUserId(userId);
        if (storageList.isEmpty()) {
            //빈리스트 전송
            resultMap.put("data", storageList);
            resultMap.put(MESSAGE, "NO DATA");
            status = HttpStatus.OK;

        } else {
            List<GetResponseDto> result = new ArrayList<>();
            for (Storage s : storageList) {
                result.add(new GetResponseDto(s));
            }
            resultMap.put("data", result);
            resultMap.put(MESSAGE, SUCCESS);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(resultMap, status);
    }
    //3. 재료 삭제
    @Transactional
    public String deleteItem(Long userId, Long igId){
        String delete_result;
        //1. 요청받은 userId, igId를 통해 storage 엔티티 존재하는지 확인
        Ingredient ingredient = ingredientRepo.findByIgId(igId)
                .orElseThrow(() -> new IllegalArgumentException("해당 재료가 존재하지 않음. id= " + igId));
        Storage storage = storageRepo.findByUserIdAndIngredient(userId, ingredient)
                .orElseThrow(() -> new IllegalArgumentException("해당 저장 정보가 존재하지 않음. igredient_id= " + igId));
        //2. repo에서 delete 메소드 수행
        try {
            storageRepo.delete(storage);
            delete_result = "success";
        } catch (Exception e) {
            logger.error("error on delete>>> " + e.getMessage());
            delete_result = "fail";
        }
        return delete_result;
    }

    //4. 재료 상태 변경
    @Transactional
    public String changeStatus(ChangeFastDto changeFastDto) {
        //1. userId, igId 얻어오기
        String result;
        Long userId = changeFastDto.getUserId();
        Ingredient ingredient = ingredientRepo.findByIgId(changeFastDto.getIgId())
                .orElseThrow(()-> new NoSuchElementException("해당 재료가 존재하지 않음"));
        //2. 변경할 storage 객체 찾아서, Fastuse 속성 변경
        Storage target = storageRepo.findByUserIdAndIngredient(userId, ingredient)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 항목"));
        try{
            if (target.getFastUse() == 0){
                target.updateFastUse(1);
                result = "Upadte : 1";}
            else {
                target.updateFastUse(0);
                result = "Update : 0";}
            }
        catch (Exception ignored){
            throw new IllegalArgumentException("FAILED_TO_CHANGE_STATE");
        }
        return result;
    }

    //5. 레시피 검색 시 내 창고 반영 재료 범위 전송
    @Transactional
    public List<SearchIngredientDto> userIgForSearch(Long userId) {
        List<Storage> storageList = storageRepo.findAllByUserId(userId);
        List<SearchIngredientDto> result = new ArrayList<>();
        if (storageList.isEmpty()) {
            return result;
        }
        result = storageList.stream()
                .map(SearchIngredientDto::new)
                .collect(Collectors.toList());

        return result;
    }


}
