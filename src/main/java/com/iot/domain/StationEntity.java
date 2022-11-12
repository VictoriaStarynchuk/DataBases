package com.iot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "station", schema = "starynchuk", catalog = "")
public class StationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "area_sq_km")
    private Double areaSqKm;

    @ManyToOne
    @JoinColumn(name = "energy_id", referencedColumnName = "id", nullable = false)
    private EnergyEntity energy;
    @ManyToOne
    @JoinColumn(name = "element_id", referencedColumnName = "id", nullable = false)
    private ElementEntity element;
    @ManyToOne
    @JoinColumn(name = "business_land_id", referencedColumnName = "id", nullable = false)
    private BusinessLandEntity businessLand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAreaSqKm() {
        return areaSqKm;
    }

    public void setAreaSqKm(Double areaSqKm) {
        this.areaSqKm = areaSqKm;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationEntity that = (StationEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(areaSqKm, that.areaSqKm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areaSqKm);
    }

    public EnergyEntity getEnergy() {
        return energy;
    }

    public void setEnergy(EnergyEntity energy) {
        this.energy = energy;
    }

    public ElementEntity getElement() {
        return element;
    }

    public void setElement(ElementEntity element) {
        this.element = element;
    }

    public BusinessLandEntity getBusinessLand() {
        return businessLand;
    }

    public void setBusinessLand(BusinessLandEntity businessLand) {
        this.businessLand = businessLand;
    }
}
