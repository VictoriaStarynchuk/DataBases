package com.iot.repository;

import com.iot.domain.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerEntityRepository extends JpaRepository<LoggerEntity, Integer> {
}