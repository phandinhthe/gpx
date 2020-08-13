package com.terry.karros.gpx.demo.mapper.entity;

import com.terry.karros.gpx.demo.entity.TrackEntity;
import io.jenetics.jpx.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrackEntityMapper {
    private final TrackSegmentEntityListMapper trackSegmentEntityListMapper;

    public TrackEntityMapper(TrackSegmentEntityListMapper trackSegmentEntityListMapper) {
        this.trackSegmentEntityListMapper = trackSegmentEntityListMapper;
    }

    public List<TrackEntity> from(List<Track> tracks) {
        if (CollectionUtils.isEmpty(tracks)) {
            return new ArrayList<>();
        }

        List<TrackEntity> results = new ArrayList<>(tracks.size());
        for (Track track : tracks) {
            results.add(this.mapToTrackEntity(track));
        }
        return results;
    }

    private TrackEntity mapToTrackEntity(Track track) {
        TrackEntity entity = new TrackEntity();
        entity.setName(track.getName().orElse(""));
        entity.setTrackSegmentEntities(trackSegmentEntityListMapper.from(track.getSegments()));
        return entity;
    }
}
