package com.iot.repository;

import com.iot.domain.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityEntityRepository extends JpaRepository<CityEntity, Integer> {
}