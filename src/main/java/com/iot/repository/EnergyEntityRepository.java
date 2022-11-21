package com.iot.repository;

import com.iot.domain.EnergyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyEntityRepository extends JpaRepository<EnergyEntity, Integer> {
}