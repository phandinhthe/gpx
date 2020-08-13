package com.terry.karros.gpx.demo.mapper.entity;

import com.terry.karros.gpx.demo.entity.WayPointEntity;
import io.jenetics.jpx.WayPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class WayPointEntityListMapper {
    public List<WayPointEntity> from(List<WayPoint> wayPoints) {
        if (CollectionUtils.isEmpty(wayPoints)) {
            return new ArrayList<>();
        }

        List<WayPointEntity> results = new ArrayList<>(wayPoints.size());
        for (WayPoint wp : wayPoints) {
            results.add(this.mapToWaypointEntity(wp));
        }
        return results;
    }

    private WayPointEntity mapToWaypointEntity(WayPoint wayPoint) {
        WayPointEntity entity = new WayPointEntity();
        entity.setLat(wayPoint.getLatitude().doubleValue());
        entity.setLon(wayPoint.getLongitude().doubleValue());
        return entity;
    }
}
