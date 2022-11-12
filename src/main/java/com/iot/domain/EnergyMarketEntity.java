package com.iot.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "energy_market", schema = "starynchuk", catalog = "")
public class EnergyMarketEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "price")
    private String price;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "time")
    private Time time;
    @ManyToOne
    @JoinColumn(name = "energy_id", referencedColumnName = "id", nullable = false)
    private EnergyEntity energy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnergyMarketEntity that = (EnergyMarketEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(price, that.price) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, date, time);
    }

    public EnergyEntity getEnergy() {
        return energy;
    }

    public void setEnergy(EnergyEntity energy) {
        this.energy = energy;
    }
}
