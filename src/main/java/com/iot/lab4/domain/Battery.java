package com.iot.lab4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Battery {
    private Integer id;
    private String type;
    private Integer capacity;
    private Integer durationTime;
    private String chargeLevel;
    private Integer power;
}
