package com.terry.karros.gpx.demo.mapper.response;

import com.terry.karros.gpx.demo.dto.TrackResponse;
import com.terry.karros.gpx.demo.entity.TrackEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrackResponseListMapper {
    private final TrackResponseMapper trackResponseMapper;

    public TrackResponseListMapper(TrackResponseMapper trackResponseMapper) {
        this.trackResponseMapper = trackResponseMapper;
    }

    public List<TrackResponse> from(List<TrackEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }
        List<TrackResponse> trackResponses = buildTrackResponseList(entities);
        return trackResponses;
    }

    private List<TrackResponse> buildTrackResponseList(List<TrackEntity> entities) {
        List<TrackResponse> trackResponses = new ArrayList<TrackResponse>();
        for (TrackEntity trackEntity : entities) {
            trackResponses.add(trackResponseMapper.from(trackEntity));
        }
        return trackResponses;
    }
}
