package com.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "energy", schema = "starynchuk", catalog = "")
public class EnergyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "solar_amount")
    private Double solarAmount;
    @Basic
    @Column(name = "use_now")
    private Double useNow;
    @Basic
    @Column(name = "exporting")
    private Double exporting;
    @OneToMany(mappedBy = "energy")
    private List<EnergyMarketEntity> energyMarkets;
    @OneToMany(mappedBy = "energy")
    private List<StationEntity> stations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSolarAmount() {
        return solarAmount;
    }

    public void setSolarAmount(Double solarAmount) {
        this.solarAmount = solarAmount;
    }

    public Double getUseNow() {
        return useNow;
    }

    public void setUseNow(Double useNow) {
        this.useNow = useNow;
    }

    public Double getExporting() {
        return exporting;
    }

    public void setExporting(Double exporting) {
        this.exporting = exporting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnergyEntity that = (EnergyEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(solarAmount, that.solarAmount) && Objects.equals(useNow, that.useNow) && Objects.equals(exporting, that.exporting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, solarAmount, useNow, exporting);
    }

    public List<EnergyMarketEntity> getEnergyMarkets() {
        return energyMarkets;
    }

    public void setEnergyMarkets(List<EnergyMarketEntity> energyMarkets) {
        this.energyMarkets = energyMarkets;
    }

    public List<StationEntity> getStations() {
        return stations;
    }

    public void setStations(List<StationEntity> stations) {
        this.stations = stations;
    }
}
