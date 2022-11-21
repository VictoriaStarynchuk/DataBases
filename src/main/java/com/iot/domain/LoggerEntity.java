package com.iot.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "logger", schema = "starynchuk", catalog = "")
public class LoggerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "action")
    private String action;
    @Basic
    @Column(name = "businessLand")
    private String businessLand;
    @Basic
    @Column(name = "owner")
    private String owner;
    @Basic
    @Column(name = "time_stamp")
    private Timestamp timeStamp;
    @Basic
    @Column(name = "user")
    private String user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBusinessLand() {
        return businessLand;
    }

    public void setBusinessLand(String businessLand) {
        this.businessLand = businessLand;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoggerEntity that = (LoggerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(action, that.action) && Objects.equals(businessLand, that.businessLand) && Objects.equals(owner, that.owner) && Objects.equals(timeStamp, that.timeStamp) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action, businessLand, owner, timeStamp, user);
    }
}
