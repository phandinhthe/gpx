package com.terry.karros.gpx.demo.mapper.response;

import com.terry.karros.gpx.demo.dto.SegmentResponse;
import com.terry.karros.gpx.demo.entity.TrackSegmentEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class SegmentListResponseMapper {
    private final TrackPointListResponseMapper trackPointListResponseMapper;

    public SegmentListResponseMapper(TrackPointListResponseMapper trackPointListResponseMapper) {
        this.trackPointListResponseMapper = trackPointListResponseMapper;
    }

    public List<SegmentResponse> from(List<TrackSegmentEntity> trackSegmentEntities) {
        if (CollectionUtils.isEmpty(trackSegmentEntities)) {
            return new ArrayList<>();
        }
        List<SegmentResponse> segmentResponses = buildSegmentResponses(trackSegmentEntities);
        return segmentResponses;
    }

    private List<SegmentResponse> buildSegmentResponses(List<TrackSegmentEntity> trackSegmentEntities) {
        List<SegmentResponse> segmentResponses = new ArrayList<>(trackSegmentEntities.size());
        for (TrackSegmentEntity trackSegmentEntity : trackSegmentEntities) {
            SegmentResponse segmentResponse = new SegmentResponse();
            segmentResponse.setTrackPoints(trackPointListResponseMapper.from(trackSegmentEntity.getTrackPointEntities()));
            segmentResponses.add(segmentResponse);
        }
        return segmentResponses;
    }
}
