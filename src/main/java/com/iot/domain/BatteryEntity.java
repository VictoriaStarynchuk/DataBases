package com.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "battery", schema = "starynchuk", catalog = "")
public class BatteryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "capacity")
    private Integer capacity;
    @Basic
    @Column(name = "duration_time")
    private Integer durationTime;
    @Basic
    @Column(name = "charge_level")
    private String chargeLevel;
    @Basic
    @Column(name = "power")
    private Integer power;
    @OneToMany(mappedBy = "battery")
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }

    public String getChargeLevel() {
        return chargeLevel;
    }

    public void setChargeLevel(String chargeLevel) {
        this.chargeLevel = chargeLevel;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BatteryEntity that = (BatteryEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(capacity, that.capacity) && Objects.equals(durationTime, that.durationTime) && Objects.equals(chargeLevel, that.chargeLevel) && Objects.equals(power, that.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, capacity, durationTime, chargeLevel, power);
    }

    public List<ElementEntity> getElements() {
        return elements;
    }

    public void setElements(List<ElementEntity> elements) {
        this.elements = elements;
    }
}
