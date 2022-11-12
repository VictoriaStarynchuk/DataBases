package com.iot.repository;

import com.iot.domain.EnergyMarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyMarketEntityRepository extends JpaRepository<EnergyMarketEntity, Integer> {
}