package com.iot.service;

import com.iot.domain.ContinentEntity;
import com.iot.dto.ContinentEntityDto;

public interface ContinentService extends GeneralService<ContinentEntity, Integer> {
    ContinentEntityDto insertNewValues(ContinentEntityDto continentEntityDto);
    void insertRows();
    void createTablesWithCursor();
}
