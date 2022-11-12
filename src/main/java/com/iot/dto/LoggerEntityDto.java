package com.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "log", collectionRelation = "logs")
public class LoggerEntityDto extends RepresentationModel<LoggerEntityDto> {
    private final Integer id;
    private final String action;
    private final String businessLand;
    private final String owner;
    private final Date timeStamp;
    private final String user;
}