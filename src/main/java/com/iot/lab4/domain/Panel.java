package com.iot.lab4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Panel {
    private Integer id;
    private String type;
    private Integer power;
    private Integer durationTime;
    private String tiltAngel;
    private Integer productionPower;
}
