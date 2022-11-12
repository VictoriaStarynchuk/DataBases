package com.iot.repository;

import com.iot.domain.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationEntityRepository extends JpaRepository<StationEntity, Integer> {
}