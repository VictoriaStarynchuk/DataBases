package com.iot.repository;

import com.iot.domain.PanelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PanelEntityRepository extends JpaRepository<PanelEntity, Integer> {
    @Query(value = "Call countAvgPower();", nativeQuery = true)
    BigDecimal getAvgPower();
}