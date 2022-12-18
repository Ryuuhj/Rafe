package com.project.rafe.controller;

import com.project.rafe.domain.beanRecord.BeanRecord;
import com.project.rafe.domain.beanRecord.dto.BeanRequestDto;
import com.project.rafe.domain.beanRecord.dto.RecordPreviewDto;
import com.project.rafe.service.BeanRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BeanRecordController {

    private final BeanRecordService beanRecordService;

    //1.기록 추가
    @PostMapping("/bean/create")
    public ResponseEntity<Map<String, String>> saveRecord(@RequestBody BeanRequestDto requestDto) {
        Map<String, String> result = new HashMap<>();
        String saveResult = beanRecordService.saveRecord(requestDto);
        result.put("message", saveResult);
        return ResponseEntity.ok().body(result);
    }

    //2.일기 미리보기
    @GetMapping("/bean/{userId}")
    public List<RecordPreviewDto> preview(@PathVariable("userId") Long userId) {
        return beanRecordService.getPreview(userId);
    }

    //3.일기 상세보기
    @GetMapping("/bean/detail/{beanId}")
    public ResponseEntity<BeanRecord> detail(@PathVariable("beanId") Long id) {
        BeanRecord record = beanRecordService.getRecord(id);
        return ResponseEntity.ok().body(record);
    }

    //4. 일기 삭제
    @DeleteMapping("/bean/delete/{beanId}")
    public void delete(@PathVariable("beanId") Long id) {
        beanRecordService.deleteRecord(id);
    }

    //5. 일기 수정
    @PostMapping("/bean/{beanId}")
    public ResponseEntity<Map<String, String>> updateRecord(@PathVariable("beanId") Long id, @RequestBody BeanRequestDto updateDto) {
        Map<String, String> response = new HashMap<>();
        String result = beanRecordService.updateRecord(id, updateDto);
        response.put("message", result);
        return ResponseEntity.ok().body(response);
    }
}
