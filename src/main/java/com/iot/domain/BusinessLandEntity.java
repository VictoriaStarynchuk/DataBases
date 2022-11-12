package com.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "business_land", schema = "starynchuk", catalog = "")
public class BusinessLandEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "quantity_station")
    private Integer quantityStation;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private CityEntity city;
    @OneToMany(mappedBy = "businessLand")
    private List<StationEntity> stations;
    @ManyToMany
    @JoinTable(name = "owner_businessland", catalog = "", schema = "starynchuk", joinColumns = @JoinColumn(name = "business_land_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false))
    private Set<OwnerEntity> owners;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getQuantityStation() {
        return quantityStation;
    }

    public void setQuantityStation(Integer quantityStation) {
        this.quantityStation = quantityStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessLandEntity that = (BusinessLandEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(quantityStation, that.quantityStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, quantityStation);
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public List<StationEntity> getStations() {
        return stations;
    }

    public void setStations(List<StationEntity> stations) {
        this.stations = stations;
    }

    public Set<OwnerEntity> getOwners() {
        return owners;
    }

    public void setOwners(Set<OwnerEntity> owners) {
        this.owners = owners;
    }
}
