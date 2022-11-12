package com.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "panel", schema = "starynchuk", catalog = "")
public class PanelEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "power")
    private Integer power;
    @Basic
    @Column(name = "duration_time")
    private Integer durationTime;
    @Basic
    @Column(name = "tilt_angel")
    private String tiltAngel;
    @Basic
    @Column(name = "production_power")
    private Integer productionPower;
    @OneToMany(mappedBy = "panel")
    private List<ElementEntity> elements;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }

    public String getTiltAngel() {
        return tiltAngel;
    }

    public void setTiltAngel(String tiltAngel) {
        this.tiltAngel = tiltAngel;
    }

    public Integer getProductionPower() {
        return productionPower;
    }

    public void setProductionPower(Integer productionPower) {
        this.productionPower = productionPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PanelEntity that = (PanelEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(power, that.power) && Objects.equals(durationTime, that.durationTime) && Objects.equals(tiltAngel, that.tiltAngel) && Objects.equals(productionPower, that.productionPower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, power, durationTime, tiltAngel, productionPower);
    }

    public List<ElementEntity> getElements() {
        return elements;
    }

    public void setElements(List<ElementEntity> elements) {
        this.elements = elements;
    }
}
