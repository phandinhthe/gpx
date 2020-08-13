package com.terry.karros.gpx.demo.mapper.response;

import com.terry.karros.gpx.demo.dto.detail.GPXDetailResponse;
import com.terry.karros.gpx.demo.dto.recent.GPXRecentListResponse;
import com.terry.karros.gpx.demo.entity.GPXEntity;
import com.terry.karros.gpx.demo.mapper.response.detail.DetailResponseMapper;
import com.terry.karros.gpx.demo.mapper.response.recent.RecentResponseMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class GPXResponseMapper {
    private final DetailResponseMapper detailResponseMapper;
    private final RecentResponseMapper recentResponseMapper;

    public GPXResponseMapper(DetailResponseMapper detailResponseMapper, RecentResponseMapper recentResponseMapper) {
        this.detailResponseMapper = detailResponseMapper;
        this.recentResponseMapper = recentResponseMapper;
    }

    public GPXDetailResponse toDetail(GPXEntity gpxEntity) {
        if (Objects.isNull(gpxEntity)) {
            return new GPXDetailResponse();
        }
        return detailResponseMapper.from(gpxEntity);
    }

    public GPXRecentListResponse toRecentList(List<GPXEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return new GPXRecentListResponse();
        }
        return recentResponseMapper.from(entities);
    }
}
