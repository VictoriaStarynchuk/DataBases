package com.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "battery", collectionRelation = "batteries")
public class BatteryEntityDto extends RepresentationModel<BatteryEntityDto> {
    private final Integer id;
    private final String type;
    private final Integer capacity;
    private final Integer durationTime;
    private final String chargeLevel;
    private final Integer power;
}