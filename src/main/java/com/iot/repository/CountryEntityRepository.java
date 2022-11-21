package com.iot.repository;

import com.iot.domain.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryEntityRepository extends JpaRepository<CountryEntity, Integer> {
}