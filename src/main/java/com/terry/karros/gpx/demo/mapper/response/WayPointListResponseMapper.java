package com.terry.karros.gpx.demo.mapper.response;

import com.terry.karros.gpx.demo.dto.WayPointResponse;
import com.terry.karros.gpx.demo.entity.WayPointEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class WayPointListResponseMapper {
    public List<WayPointResponse> from(List<WayPointEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }
        List<WayPointResponse> responses = buildWayPointListResponses(entities);
        return responses;
    }

    private List<WayPointResponse> buildWayPointListResponses(List<WayPointEntity> entities) {
        List<WayPointResponse> responses = new ArrayList<>(entities.size());
        for (WayPointEntity entity : entities) {
            responses.add(buildWaypointResponse(entity));
        }
        return responses;
    }

    private WayPointResponse buildWaypointResponse(WayPointEntity entity) {
        long id = entity.getId();
        double lat = entity.getLat();
        double lot = entity.getLon();

        WayPointResponse response = new WayPointResponse();
        response.setId(id);
        response.setLat(lat);
        response.setLon(lot);
        return response;
    }
}
