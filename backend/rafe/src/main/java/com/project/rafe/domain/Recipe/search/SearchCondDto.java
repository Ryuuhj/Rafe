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

    private List<Long> ingredientId;

    private List<Long> exceptId;

    /*public SearchCondDto(String keyword, Integer lactose, Integer caffeine, List<Long> igIdList) {
        this(10L, keyword, lactose, caffeine, igIdList);
    }*/
    @Builder
    public SearchCondDto(Long userId, Long categoryId, String keyword, List<Long> ingredientId, List<Long> exceptId) {
        this.userId = userId;
        this.categoryId = categoryId;
        if (keyword.equals(null)||keyword.equals(" ")) {
            this.keyword = "";
        }else {
            this.keyword = keyword;
        }

        this.ingredientId = ingredientId;
        this.exceptId = exceptId;
    }
    //알러지 재료 + 제외재료 중복 제거 세팅
    public void updateAllergyList(List<Long> allergyList){
        if (this.exceptId.isEmpty()) {
            this.exceptId.addAll(allergyList);
        } else{
            Set<Long> set = new HashSet<>(this.exceptId);
            set.addAll(allergyList);
            this.exceptId = new ArrayList<>(set);
        }
    }
}
