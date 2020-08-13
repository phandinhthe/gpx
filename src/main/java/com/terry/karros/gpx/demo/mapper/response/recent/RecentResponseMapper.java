package com.terry.karros.gpx.demo.mapper.response.recent;

import com.terry.karros.gpx.demo.dto.MetadataResponse;
import com.terry.karros.gpx.demo.dto.recent.GPXRecentListResponse;
import com.terry.karros.gpx.demo.entity.GPXEntity;
import com.terry.karros.gpx.demo.mapper.response.MetadataResponseMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecentResponseMapper {
    private final MetadataResponseMapper metadataResponseMapper;

    public RecentResponseMapper(MetadataResponseMapper metadataResponseMapper) {
        this.metadataResponseMapper = metadataResponseMapper;
    }

    public GPXRecentListResponse from(List<GPXEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return new GPXRecentListResponse();
        }
        GPXRecentListResponse response = new GPXRecentListResponse();
        List<GPXRecentListResponse.GPXResponse> detailResponses = buildGPXRecentListResponse(entities);
        response.setGpxResponses(detailResponses);
        return response;
    }

    private List<GPXRecentListResponse.GPXResponse> buildGPXRecentListResponse(List<GPXEntity> entities) {
        List<GPXRecentListResponse.GPXResponse> responses = new ArrayList<>(entities.size());
        for (GPXEntity entity : entities) {
            responses.add(buildGPXResponse(entity));
        }
        return responses;
    }

    private GPXRecentListResponse.GPXResponse buildGPXResponse(GPXEntity entity) {
        return GPXRecentListResponse.GPXResponse.from(getId(entity), buildMetadataResponse(entity));
    }


    private MetadataResponse buildMetadataResponse(GPXEntity entity) {
        return metadataResponseMapper.from(entity.getMetadataEntity());
    }

    private long getId(GPXEntity entity) {
        return entity.getId();
    }
}
