package com.project.rafe.domain.Recipe.search;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchCondDto {

    private Long categoryId;
    private String keyword;
    private Long lactose;
    private Long caffeine;
    private List<Long> ingredientId;

    /*public SearchCondDto(String keyword, Integer lactose, Integer caffeine, List<Long> igIdList) {
        this(10L, keyword, lactose, caffeine, igIdList);
    }*/
    @Builder
    public SearchCondDto(Long categoryId, String keyword, Long lactose, Long caffeine, List<Long> ingredientId) {
        this.categoryId = categoryId;
        this.keyword = keyword;
        this.lactose = lactose;
        this.caffeine = caffeine;
        this.ingredientId = ingredientId;
    }
}
