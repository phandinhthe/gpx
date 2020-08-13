package com.terry.karros.gpx.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "WayPoint")
public class WayPointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;


    private Double lat;

    private Double lon;

    private String name;

    private String sym;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }
}
