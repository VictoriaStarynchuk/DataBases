package com.iot.repository;

import com.iot.domain.BusinessLandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessLandEntityRepository extends JpaRepository<BusinessLandEntity, Integer> {
}