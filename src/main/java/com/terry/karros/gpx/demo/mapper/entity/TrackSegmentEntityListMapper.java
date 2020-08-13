package com.terry.karros.gpx.demo.mapper.entity;

import com.terry.karros.gpx.demo.entity.TrackSegmentEntity;
import io.jenetics.jpx.TrackSegment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrackSegmentEntityListMapper {

    @Autowired
    private TrackPointEntityListMapper trackPointEntityListMapper;

    public List<TrackSegmentEntity> from(List<TrackSegment> trackSegments) {
        if (CollectionUtils.isEmpty(trackSegments)) {
            return new ArrayList<>();
        }

        List<TrackSegmentEntity> results = new ArrayList<>(trackSegments.size());
        for (TrackSegment trackSegment : trackSegments) {
            results.add(this.mapToTrackEntity(trackSegment));
        }
        return results;
    }

    private TrackSegmentEntity mapToTrackEntity(TrackSegment trackSegment) {
        TrackSegmentEntity entity = new TrackSegmentEntity();
        entity.setTrackPointEntities(trackPointEntityListMapper.from(trackSegment.getPoints()));
        return entity;
    }
}
