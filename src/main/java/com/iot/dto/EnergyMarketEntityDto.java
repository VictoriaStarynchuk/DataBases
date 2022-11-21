package com.iot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Date;
import java.sql.Time;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "energyMarket", collectionRelation = "energyMarkets")
public class EnergyMarketEntityDto extends RepresentationModel<EnergyMarketEntityDto> {
    private final Integer id;
    private final String price;
    private final Date date;
    private final Time time;
}