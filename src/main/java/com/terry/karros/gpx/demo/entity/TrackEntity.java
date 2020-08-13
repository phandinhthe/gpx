package com.terry.karros.gpx.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Track")
public class TrackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "track_entity_sequence")
    @SequenceGenerator(name = "track_entity_sequence", allocationSize = 10)
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TrackSegmentEntity> trackSegmentEntities;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TrackSegmentEntity> getTrackSegmentEntities() {
        return trackSegmentEntities;
    }

    public void setTrackSegmentEntities(List<TrackSegmentEntity> trackSegmentEntities) {
        this.trackSegmentEntities = trackSegmentEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
