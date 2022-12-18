package com.project.rafe.domain.beanRecord.dto;

import com.project.rafe.domain.beanRecord.BeanRecord;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BeanRequestDto {

    private Long userId;
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
    public BeanRequestDto(Long userId, Long exId, String pickDate, String bean, String loasting, Long exAmount,
                          String exG, Long wtTemp, Long wtAmount, Long exMin, Long exSec, String filter, String comment, Long star) {
        this.userId = userId;
        this.exId = exId;
        this.pickDate = pickDate;
        this.bean = bean;
        this.loasting = loasting;
        this.exAmount = exAmount;
        this.exG = exG;
        this.wtTemp = wtTemp;
        this.wtAmount = wtAmount;
        this.exMin = exMin;
        this.exSec = exSec;
        this.filter = filter;
        this.comment = comment;
        this.star = star;
    }

    public BeanRecord toEntity() {
        return BeanRecord.builder()
                .userId(userId)
                .exId(exId)
                .pickDate(pickDate)
                .bean(bean)
                .loasting(loasting)
                .exAmount(exAmount)
                .exG(exG)
                .wtTemp(wtTemp)
                .wtAmount(wtAmount)
                .exMin(exMin)
                .exSec(exSec)
                .filter(filter)
                .comment(comment)
                .star(star)
                .build();
    }

}
