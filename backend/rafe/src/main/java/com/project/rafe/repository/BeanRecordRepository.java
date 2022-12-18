package com.project.rafe.repository;

import com.project.rafe.domain.beanRecord.BeanRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeanRecordRepository extends JpaRepository<BeanRecord, Long> {

    List<BeanRecord> findAllByUserId(Long userId);

    Optional<BeanRecord> findBeanRecordById(Long id);
}
