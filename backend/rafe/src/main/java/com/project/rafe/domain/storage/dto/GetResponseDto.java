package com.project.rafe.domain.storage.dto;

import com.project.rafe.domain.storage.Storage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetResponseDto {

    private Long storeId;
    private Long igId;
    private String igName;
    private Integer fastUse;

    @Builder
    public GetResponseDto(Storage storage) {
        this.storeId = storage.getStorageId();
        this.igId = storage.getIngredient().getIgId();
        this.igName = storage.getIngredient().getIgName();
        this.fastUse = storage.getFastUse();
    }


}
