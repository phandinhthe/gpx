package com.terry.karros.gpx.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TrackSegment")
public class TrackSegmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TrackPointEntity> trackPointEntities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TrackPointEntity> getTrackPointEntities() {
        return trackPointEntities;
    }

    public void setTrackPointEntities(List<TrackPointEntity> trackPointEntities) {
        this.trackPointEntities = trackPointEntities;
    }
}
