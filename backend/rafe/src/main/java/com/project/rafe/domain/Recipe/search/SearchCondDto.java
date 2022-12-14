package com.project.rafe.domain.Recipe.search;

import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchCondDto {

    private Long userId;
    private Long categoryId;
    private String keyword;
    /*private Long lactose;
    private Long caffeine;*/
    private List<Long> ingredientId;

    private List<Long> exceptIgId;

    /*public SearchCondDto(String keyword, Integer lactose, Integer caffeine, List<Long> igIdList) {
        this(10L, keyword, lactose, caffeine, igIdList);
    }*/
    @Builder
    public SearchCondDto(Long userId, Long categoryId, String keyword, List<Long> ingredientId, List<Long> exceptIgId) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.keyword = keyword;
        this.ingredientId = ingredientId;
        this.exceptIgId = exceptIgId;
    }
    //알러지 재료 + 제외재료 중복 제거 세팅
    public void updateAllergyList(List<Long> allergyList){
        if (this.exceptIgId.isEmpty()) {
            this.exceptIgId.addAll(allergyList);
        } else{
            Set<Long> set = new HashSet<>(this.exceptIgId);
            set.addAll(allergyList);
            this.exceptIgId = new ArrayList<>(set);
        }
    }
}
