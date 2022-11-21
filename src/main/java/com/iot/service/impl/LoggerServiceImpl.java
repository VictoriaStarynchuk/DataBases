package com.iot.service.impl;


import com.iot.domain.LoggerEntity;
import com.iot.exception.LogNotFoundException;
import com.iot.repository.LoggerEntityRepository;
import com.iot.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerServiceImpl implements LoggerService {
    @Autowired
    LoggerEntityRepository loggerEntityRepository;

    public LoggerEntity findById(Integer id) {
        return loggerEntityRepository.findById(id)
                .orElseThrow(() -> new LogNotFoundException(id));
    }

    public List<LoggerEntity> findAll() {
        return loggerEntityRepository.findAll();
    }
}
