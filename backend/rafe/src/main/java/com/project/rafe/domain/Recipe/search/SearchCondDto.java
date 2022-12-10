package com.project.rafe.domain.Recipe.search;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SearchCondDto {

    private Long categoryId;
    private String keyword;
    /*private Long lactose;
    private Long caffeine;*/
    private List<Long> ingredientId;

    private List<Long> exceptId;

    /*public SearchCondDto(String keyword, Integer lactose, Integer caffeine, List<Long> igIdList) {
        this(10L, keyword, lactose, caffeine, igIdList);
    }*/
    @Builder
    public SearchCondDto(Long categoryId, String keyword, List<Long> ingredientId, List<Long> exceptId) {
        this.categoryId = categoryId;
        this.keyword = keyword;
       /* this.lactose = lactose;
        this.caffeine = caffeine;*/
        this.ingredientId = ingredientId;
        this.exceptId = exceptId;
    }
}
