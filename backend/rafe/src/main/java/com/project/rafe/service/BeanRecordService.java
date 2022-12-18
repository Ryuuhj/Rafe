package com.project.rafe.service;

import com.project.rafe.domain.beanRecord.BeanRecord;
import com.project.rafe.domain.beanRecord.dto.BeanRequestDto;
import com.project.rafe.domain.beanRecord.dto.RecordPreviewDto;
import com.project.rafe.repository.BeanRecordRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeanRecordService {

    private final BeanRecordRepository beanRecordRepository;

    public static final Logger logger = LoggerFactory.getLogger(BeanRecordService.class);

    //1. 일기 저장
    @Transactional
    public String saveRecord(BeanRequestDto request) {
        String result = null;
        try {
            BeanRecord record = beanRecordRepository.save(request.toEntity());
            if (record == null) {
                result = "fail";
            }else {
                result = "success";
            }
        } catch (NullPointerException e ) {
            logger.error("Service: 일기 저장 오류>>>" + e.getMessage());
            result = "fail";
        }
        return result;
    }

    //2. 일기 미리보기
    public List<RecordPreviewDto> getPreview(Long userId) {
        List<RecordPreviewDto> result;
        //id로 조회
        List<BeanRecord> records = beanRecordRepository.findAllByUserId(userId);
        if (records.isEmpty()) {
            result = new ArrayList<>();
        }else {
            //entity -> dto
            result = records.stream().map(RecordPreviewDto::new).collect(Collectors.toList());
        }
        return result;
    }

    //3. 일기 조회
    public BeanRecord getRecord(Long id) {
        return beanRecordRepository.findBeanRecordById(id).orElseThrow(NullPointerException::new);
    }

    //4. 일기 삭제
    @Transactional
    public void deleteRecord(Long id) {
        BeanRecord record = getRecord(id);
        beanRecordRepository.delete(record);
    }

    //5.일기 수정(업데이트)
    @Transactional
    public String updateRecord(Long id, BeanRequestDto updateDto) {
        String result;
        //수정할 일기 찾기
        BeanRecord target = beanRecordRepository.findBeanRecordById(id).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않음"));
        try {
            target.update(updateDto);
            result = "success";
        } catch (RuntimeException e) {
            logger.error("일기 수정 오류");
            result = "fail";
        }
        return result;
    }

}
