package com.terry.karros.gpx.demo.service;

import com.terry.karros.gpx.demo.dto.detail.GPXDetailResponse;
import com.terry.karros.gpx.demo.dto.recent.GPXRecentListResponse;
import com.terry.karros.gpx.demo.entity.GPXEntity;
import com.terry.karros.gpx.demo.exception.GPXExceptionHelper;
import com.terry.karros.gpx.demo.mapper.response.GPXResponseMapper;
import com.terry.karros.gpx.demo.repository.GPXRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class GPXService {
    private static final Logger log = LoggerFactory.getLogger(GPXService.class);
    public static final String CREATED_TIME = "createdTime";

    private final GPXRepository repository;
    private final GPXResponseMapper gpxResponseMapper;

    public GPXService(GPXRepository repository, GPXResponseMapper gpxResponseMapper) {
        this.repository = repository;
        this.gpxResponseMapper = gpxResponseMapper;
    }

    public GPXRecentListResponse list(int limit, int page) {
        Pageable sortByDate =
                PageRequest.of(page, limit, Sort.by(CREATED_TIME).descending());
        return gpxResponseMapper.toRecentList(repository.findAll(sortByDate).getContent());
    }

    public GPXDetailResponse get(long gpxId) {
        GPXEntity entity = repository.findById(gpxId).orElseThrow(
                () -> GPXExceptionHelper.getInstance().notFoundGPX(new NullPointerException(), String.valueOf(gpxId)));
        return gpxResponseMapper.toDetail(entity);
    }
}
