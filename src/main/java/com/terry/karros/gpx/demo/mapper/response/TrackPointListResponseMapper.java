package com.terry.karros.gpx.demo.mapper.response;

import com.terry.karros.gpx.demo.dto.TrackPointResponse;
import com.terry.karros.gpx.demo.entity.TrackPointEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrackPointListResponseMapper {
    public List<TrackPointResponse> from(List<TrackPointEntity> trackPointEntities) {
        if (CollectionUtils.isEmpty(trackPointEntities)) {
            return new ArrayList<>();
        }

        return buildTrackResponses(trackPointEntities);
    }

    private List<TrackPointResponse> buildTrackResponses(List<TrackPointEntity> trackPointEntities) {
        List<TrackPointResponse> trackPointResponses = new ArrayList<>();
        for (TrackPointEntity trackPointEntity : trackPointEntities) {
            trackPointResponses.add(buildTrackPointResponse(trackPointEntity));
        }
        return trackPointResponses;
    }

    private TrackPointResponse buildTrackPointResponse(TrackPointEntity trackPointEntity) {
        TrackPointResponse trackPointResponse = new TrackPointResponse();
        trackPointResponse.setId(trackPointEntity.getId());
        trackPointResponse.setLat(trackPointEntity.getLat());
        trackPointResponse.setLon(trackPointEntity.getLon());
        trackPointResponse.setElevation(trackPointEntity.getElevation());
        trackPointResponse.setDateTime(trackPointEntity.getTime().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        return trackPointResponse;
    }
}
