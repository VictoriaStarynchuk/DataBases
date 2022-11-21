package com.iot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnergyMarket {
    private Integer id;
    private String price;
    private Date date;
    private Time time;
    private Integer energyId;
}
