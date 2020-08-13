package com.terry.karros.gpx.demo.mapper.response.detail;

import com.terry.karros.gpx.demo.dto.detail.GPXDetailResponse;
import com.terry.karros.gpx.demo.entity.GPXEntity;
import com.terry.karros.gpx.demo.mapper.response.MetadataResponseMapper;
import com.terry.karros.gpx.demo.mapper.response.TrackResponseListMapper;
import com.terry.karros.gpx.demo.mapper.response.WayPointListResponseMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DetailResponseMapper {
    private final MetadataResponseMapper metadataResponseMapper;
    private final WayPointListResponseMapper wayPointListResponseMapper;
    private final TrackResponseListMapper trackResponseListMapper;

    public DetailResponseMapper(MetadataResponseMapper metadataResponseMapper
            , WayPointListResponseMapper wayPointListResponseMapper
            , TrackResponseListMapper trackResponseListMapper) {
        this.metadataResponseMapper = metadataResponseMapper;
        this.wayPointListResponseMapper = wayPointListResponseMapper;
        this.trackResponseListMapper = trackResponseListMapper;
    }

    public GPXDetailResponse from(GPXEntity gpxEntity) {
        if (Objects.isNull(gpxEntity)) {
            return new GPXDetailResponse();
        }

        GPXDetailResponse detailResponse = new GPXDetailResponse();
        detailResponse.setId(gpxEntity.getId());
        detailResponse.setMetadata(metadataResponseMapper.from(gpxEntity.getMetadataEntity()));
        detailResponse.setWayPoints(wayPointListResponseMapper.from(gpxEntity.getWaypoints()));
        detailResponse.setTracks(trackResponseListMapper.from(gpxEntity.getTracks()));
        return detailResponse;
    }
}
