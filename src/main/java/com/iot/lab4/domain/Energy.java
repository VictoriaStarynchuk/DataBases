package com.iot.lab4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Energy {
    private Integer id;
    private Double solarAmount;
    private Double useNow;
    private Double exporting;
}
