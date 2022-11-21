package com.iot.repository;

import com.iot.domain.PanelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanelEntityRepository extends JpaRepository<PanelEntity, Integer> {
}