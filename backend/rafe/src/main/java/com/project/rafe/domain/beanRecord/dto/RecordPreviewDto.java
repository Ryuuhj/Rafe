package com.project.rafe.domain.beanRecord.dto;

import com.project.rafe.domain.beanRecord.BeanRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecordPreviewDto {

    private Long beanId;
    private Long exId;
    private String pickDate;
    private String bean;
    private Long star;

    @Builder
    public RecordPreviewDto(BeanRecord beanRecord) {
        this.beanId = beanRecord.getId();
        this.exId = beanRecord.getExId();
        this.pickDate = beanRecord.getPickDate();
        this.bean = beanRecord.getBean();
        this.star = beanRecord.getStar();
    }

}
