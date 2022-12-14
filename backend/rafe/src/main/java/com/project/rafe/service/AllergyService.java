package com.project.rafe.service;

import com.project.rafe.domain.allergy.Allergy;
import com.project.rafe.domain.allergy.dto.AllergyResDto;
import com.project.rafe.domain.ingredient.Ingredient;
import com.project.rafe.domain.storage.dto.AddStorageReqDto;
import com.project.rafe.repository.AllergyRepository;
import com.project.rafe.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AllergyService extends MessageSetting{

    public static final Logger logger = LoggerFactory.getLogger(AllergyService.class);
    private final IngredientRepository ingredientRepo;
    private final AllergyRepository allergyRepository;

    //1. 알러지 재료 추가
    @Transactional
    public Boolean addItem(AddStorageReqDto request) {
        //1. userId 꺼내
        Long userId = request.getUserId();
        List<Long> idList = request.getIgIdList();
        Boolean result = false;
        //2. id로 재료 찾고 allergy에 매핑해 save
        try {
            for (Long id : idList) {
                Ingredient ingredient = ingredientRepo.findByIgId(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당 재료가 없습니다 id=" + id));
                if (allergyRepository.existsByUserIdAndIngredient(userId, ingredient)) {
                    continue;
                } else {
                    allergyRepository.save(new Allergy(userId, ingredient));
                }
            }
            result = true;
        } catch (NullPointerException e) {
            logger.error("save error>>>" + e.getMessage());
        }
        return result;
    }

    //2. 알러지 목록 조회
    public ResponseEntity<Map<String,Object>> getAllList(Long userId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Allergy> allergyList = allergyRepository.findAllByUserId(userId);
        if(allergyList.isEmpty()){
            resultMap.put("data", allergyList);
            resultMap.put(MESSAGE, "NO DATA");
        }else {
            List<AllergyResDto> result = new ArrayList<>();
            for (Allergy a : allergyList) {
                result.add(new AllergyResDto(a));
            }
            resultMap.put("data", result);
            resultMap.put(MESSAGE, SUCCESS);
        }
        return ResponseEntity.ok(resultMap);
    }

    //3. 알러지 재료 삭제
    @Transactional
    public Boolean deleteItem(Long userId, Long igId) {
        //1. idId로 재료 get
        Ingredient deletIg = ingredientRepo.findByIgId(igId)
                .orElseThrow(() -> new IllegalArgumentException("해당 재료가 존재하지 않음. id= " + igId));
        //2. 알러지 목록에 해당 재료 있는지 확인
        Allergy deleteAll = allergyRepository.findByUserIdAndIngredient(userId, deletIg)
                .orElseThrow(() -> new IllegalArgumentException("해당 저장 정보가 존재하지 않음. igredient_id= " + igId));
        //3. 존재한다면 레포에서 삭제
        try {
            allergyRepository.delete(deleteAll);
        } catch (Exception e) {
            logger.error("error on delete>>> " + e.getMessage());
            return false;
        }

        return true;
    }



}
