package com.project.rafe.domain.beanRecord.dto;

import com.project.rafe.domain.beanRecord.BeanRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BeanResponseDto {

    private Long beanId;
    private Long exId;
    private String pickDate;
    private String bean;
    private String loasting;
    private Long exAmount;
    private String exG;
    private Long wtTemp;
    private Long wtAmount;
    private Long exMin;
    private Long exSec;
    private String filter;
    private String comment;
    private Long star;

    @Builder
    public BeanResponseDto(BeanRecord record) {
        this.beanId = record.getId();
        this.exId = record.getExId();
        this.pickDate = record.getPickDate();
        this.bean = record.getBean();
        this.loasting = record.getLoasting();
        this.exAmount = record.getExAmount();
        this.exG = record.getExG();
        this.wtTemp = record.getWtTemp();
        this.wtAmount = record.getWtAmount();
        this.exMin = record.getExMin();
        this.exSec = record.getExSec();
        this.filter = record.getFilter();
        this.comment = record.getComment();
        this.star = record.getStar();
    }


}
