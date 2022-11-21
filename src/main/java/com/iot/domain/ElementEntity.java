package com.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "element", schema = "starynchuk", catalog = "")
public class ElementEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "panel_quantity")
    private Integer panelQuantity;
    @Basic
    @Column(name = "battery_quantity")
    private Integer batteryQuantity;
    @ManyToOne
    @JoinColumn(name = "panel_id", referencedColumnName = "id", nullable = false)
    private PanelEntity panel;
    @ManyToOne
    @JoinColumn(name = "battery_id", referencedColumnName = "id", nullable = false)
    private BatteryEntity battery;
    @OneToMany(mappedBy = "element")
    private List<StationEntity> stations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPanelQuantity() {
        return panelQuantity;
    }

    public void setPanelQuantity(Integer panelQuantity) {
        this.panelQuantity = panelQuantity;
    }

    public Integer getBatteryQuantity() {
        return batteryQuantity;
    }

    public void setBatteryQuantity(Integer batteryQuantity) {
        this.batteryQuantity = batteryQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementEntity that = (ElementEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(panelQuantity, that.panelQuantity) && Objects.equals(batteryQuantity, that.batteryQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, panelQuantity, batteryQuantity);
    }

    public PanelEntity getPanel() {
        return panel;
    }

    public void setPanel(PanelEntity panel) {
        this.panel = panel;
    }

    public BatteryEntity getBattery() {
        return battery;
    }

    public void setBattery(BatteryEntity battery) {
        this.battery = battery;
    }

    public List<StationEntity> getStations() {
        return stations;
    }

    public void setStations(List<StationEntity> stations) {
        this.stations = stations;
    }
}
