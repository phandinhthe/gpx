package com.terry.karros.gpx.demo.mapper.entity;

import com.terry.karros.gpx.demo.entity.GPXEntity;
import com.terry.karros.gpx.demo.entity.MetadataEntity;
import com.terry.karros.gpx.demo.entity.TrackEntity;
import com.terry.karros.gpx.demo.entity.WayPointEntity;
import io.jenetics.jpx.GPX;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class GPXEntityMapper {

    private final MetadataMapper metadataMapper;
    private final WayPointEntityListMapper wayPointEntityListMapper;
    private final TrackEntityMapper trackEntityMapper;

    public GPXEntityMapper(MetadataMapper metadataMapper, WayPointEntityListMapper wayPointEntityListMapper
            , TrackEntityMapper trackEntityMapper) {
        this.metadataMapper = metadataMapper;
        this.wayPointEntityListMapper = wayPointEntityListMapper;
        this.trackEntityMapper = trackEntityMapper;
    }

    public GPXEntity from(GPX gpx) {
        GPXEntity entity = new GPXEntity();
        if (Objects.isNull(gpx)) {
            return entity;
        }

        if (gpx.getMetadata().isPresent()) {
            entity.setMetadata(getMetadataEntity(gpx));
        }

        if (!CollectionUtils.isEmpty(gpx.getWayPoints())) {
            entity.setWaypoints(getWayPointEntityList(gpx));
        }

        if (!CollectionUtils.isEmpty(gpx.getTracks())) {
            entity.setTracks(getTrackEntityList(gpx));
        }

        return entity;
    }

    private List<TrackEntity> getTrackEntityList(GPX gpx) {
        return trackEntityMapper.from(gpx.getTracks());
    }

    private List<WayPointEntity> getWayPointEntityList(GPX gpx) {
        return wayPointEntityListMapper.from(gpx.getWayPoints());
    }

    private MetadataEntity getMetadataEntity(GPX gpx) {
        return metadataMapper.from(gpx.getMetadata().get()).orElse(null);
    }
}
