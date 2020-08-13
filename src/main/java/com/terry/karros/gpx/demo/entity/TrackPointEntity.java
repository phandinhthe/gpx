package com.terry.karros.gpx.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "TrackPoint")
public class TrackPointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "track_point_entity_sequence")
    @SequenceGenerator(name = "track_point_entity_sequence", allocationSize = 10)
    private long id;

    private Double lat;

    private Double lon;

    private Double elevation;

    private ZonedDateTime time;

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

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }
}
