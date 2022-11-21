package com.iot.repository;

import com.iot.domain.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerEntityRepository extends JpaRepository<OwnerEntity, Integer> {
}