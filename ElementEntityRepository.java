package com.iot.repository;

import com.iot.domain.ElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementEntityRepository extends JpaRepository<ElementEntity, Integer> {
    @Procedure("create_panel_battery_rel")
    void manyToManyRel(String panelType,String batteryType);
}