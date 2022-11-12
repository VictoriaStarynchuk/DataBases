package com.iot.repository;

import com.iot.domain.BatteryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryEntityRepository extends JpaRepository<BatteryEntity, Integer> {
}