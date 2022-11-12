package com.iot.controller;

import com.iot.domain.LoggerEntity;
import com.iot.dto.LoggerEntityDto;
import com.iot.dto.assembler.LoggerDtoAssembler;
import com.iot.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/logs")
public class LoggerController {
    @Autowired
    private LoggerService loggerService;
    @Autowired
    private LoggerDtoAssembler loggerDtoAssembler;

    @GetMapping(value = "/{logId}")
    public ResponseEntity<LoggerEntityDto> getLog(@PathVariable Integer logId) {
        LoggerEntity logger = loggerService.findById(logId);
        LoggerEntityDto loggerDto = loggerDtoAssembler.toModel(logger);
        return new ResponseEntity<>(loggerDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<LoggerEntityDto>> getAllLogs() {
        List<LoggerEntity> loggers = loggerService.findAll();
        CollectionModel<LoggerEntityDto> loggerDtos = loggerDtoAssembler.toCollectionModel(loggers);
        return new ResponseEntity<>(loggerDtos, HttpStatus.OK);
    }
}
