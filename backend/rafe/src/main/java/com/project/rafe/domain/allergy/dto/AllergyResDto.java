package com.project.rafe.domain.allergy.dto;

import com.project.rafe.domain.allergy.Allergy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AllergyResDto {

    private Long allergyId;

    private Long igId;

    private String igName;

    @Builder
    public AllergyResDto(Allergy allergy) {
        this.allergyId = allergy.getId();
        this.igId = allergy.getIngredient().getIgId();
        this.igName = allergy.getIngredient().getIgName();
    }
}
