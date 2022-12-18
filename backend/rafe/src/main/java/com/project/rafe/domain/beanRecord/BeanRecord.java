package com.project.rafe.domain.beanRecord;

import com.project.rafe.domain.beanRecord.dto.BeanRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BeanRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private Long exId;

    @Column
    private String pickDate;

    @Column
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
    public BeanRecord(Long userId, Long exId, String pickDate, String bean, String loasting, Long exAmount,
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

    public void update(BeanRequestDto dto) {
        this.userId = dto.getUserId();
        this.exId = dto.getExId();
        this.pickDate = dto.getPickDate();
        this.bean = dto.getBean();
        this.loasting = dto.getLoasting();
        this.exAmount = dto.getExAmount();
        this.exG = dto.getExG();
        this.wtTemp = dto.getWtTemp();
        this.wtAmount = dto.getWtAmount();
        this.exMin = dto.getExMin();
        this.exSec = dto.getExSec();
        this.filter = dto.getFilter();
        this.comment = dto.getComment();
        this.star = dto.getStar();
    }
}
