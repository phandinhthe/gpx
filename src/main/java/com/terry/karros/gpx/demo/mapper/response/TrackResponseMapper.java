package com.terry.karros.gpx.demo.mapper.response;

import com.terry.karros.gpx.demo.dto.SegmentResponse;
import com.terry.karros.gpx.demo.dto.TrackResponse;
import com.terry.karros.gpx.demo.entity.TrackEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TrackResponseMapper {
    private final SegmentListResponseMapper segmentListResponseMapper;

    public TrackResponseMapper(SegmentListResponseMapper segmentListResponseMapper) {
        this.segmentListResponseMapper = segmentListResponseMapper;
    }

    public TrackResponse from(TrackEntity entity) {
        if (Objects.isNull(entity)) {
            return new TrackResponse();
        }

        return buildTrackResponse(entity);
    }

    private TrackResponse buildTrackResponse(TrackEntity trackEntity) {
        TrackResponse trackResponse = new TrackResponse();
        List<SegmentResponse> segmentResponseList =
                segmentListResponseMapper.from(trackEntity.getTrackSegmentEntities());
        trackResponse.setSegments(segmentResponseList);
        return trackResponse;
    }
}
