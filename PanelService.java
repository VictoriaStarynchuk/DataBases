package com.iot.service;

import com.iot.domain.PanelEntity;

import java.math.BigDecimal;

public interface PanelService extends GeneralService<PanelEntity, Integer>{
    BigDecimal getAvgPower();
}
