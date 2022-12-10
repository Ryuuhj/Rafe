package com.project.rafe.domain.ingredient.dto.naver;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Getter
@NoArgsConstructor
public class ItemSearchResDto {

    private String title;
    private String link;
    private String image;
    private Integer lprice;

    @Builder
    public ItemSearchResDto(JSONObject itemJson) {
        this.title = (String) itemJson.get("title");
        this.link = (String) itemJson.get("link");
        this.image = (String) itemJson.get("image");
        this.lprice = Integer.valueOf((String) itemJson.get("lprice"));
    }
}
