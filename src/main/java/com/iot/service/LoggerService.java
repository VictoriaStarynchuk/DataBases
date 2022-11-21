package com.iot.service;


import com.iot.domain.LoggerEntity;

import java.util.List;

public interface LoggerService {
    List<LoggerEntity> findAll();
    LoggerEntity findById(Integer id);
}
