package com.iot.lab4.domain;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
