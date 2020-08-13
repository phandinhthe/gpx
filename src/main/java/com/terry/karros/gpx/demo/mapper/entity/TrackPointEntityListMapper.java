package com.terry.karros.gpx.demo.mapper.entity;

import com.terry.karros.gpx.demo.entity.TrackPointEntity;
import com.terry.karros.gpx.demo.entity.TrackSegmentEntity;
import io.jenetics.jpx.Length;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrackPointEntityListMapper {
    public List<TrackPointEntity> from(List<WayPoint> wayPoints) {
        if (CollectionUtils.isEmpty(wayPoints)) {
            return new ArrayList<>();
        }

        List<TrackPointEntity> results = new ArrayList<>(wayPoints.size());
        for (WayPoint wp : wayPoints) {
            results.add(this.mapToWaypointEntity(wp));
        }
        return results;
    }

    private TrackPointEntity mapToWaypointEntity(WayPoint wayPoint) {
        TrackPointEntity entity = new TrackPointEntity();

        if (wayPoint.getElevation().isPresent()) {
            entity.setElevation(wayPoint.getElevation().get().doubleValue());
        }

        entity.setLat(wayPoint.getLatitude().doubleValue());
        entity.setLon(wayPoint.getLongitude().doubleValue());
        entity.setTime(wayPoint.getTime().orElse(null));
        return entity;
    }
}
