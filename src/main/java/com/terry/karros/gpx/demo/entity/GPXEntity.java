package com.terry.karros.gpx.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "GPX")
public class GPXEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gpx_entity_sequence")
    @SequenceGenerator(name = "gpx_entity_sequence", allocationSize = 10)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private MetadataEntity metadataEntity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<WayPointEntity> waypoints;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TrackEntity> tracks;

    private LocalDateTime createdTime;

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(createdTime)) {
            createdTime = LocalDateTime.now();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MetadataEntity getMetadata() {
        return metadataEntity;
    }

    public void setMetadata(MetadataEntity metadataEntity) {
        this.metadataEntity = metadataEntity;
    }

    public List<WayPointEntity> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<WayPointEntity> waypoints) {
        this.waypoints = waypoints;
    }

    public MetadataEntity getMetadataEntity() {
        return metadataEntity;
    }

    public void setMetadataEntity(MetadataEntity metadataEntity) {
        this.metadataEntity = metadataEntity;
    }

    public List<TrackEntity> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackEntity> tracks) {
        this.tracks = tracks;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
