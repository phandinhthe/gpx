package com.terry.karros.gpx.demo.service;

import com.terry.karros.gpx.demo.dto.upload.GPXUploadResponse;
import com.terry.karros.gpx.demo.entity.GPXEntity;
import com.terry.karros.gpx.demo.exception.GPXExceptionHelper;
import com.terry.karros.gpx.demo.mapper.entity.GPXEntityMapper;
import com.terry.karros.gpx.demo.mapper.response.upload.UploadResponseMapper;
import com.terry.karros.gpx.demo.repository.GPXRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;

@Component
public class GPXUploadService {
    private final GPXEntityMapper gpxEntityMapper;

    private final GPXRepository repository;

    private final UploadResponseMapper uploadResponseMapper;

    public GPXUploadService(GPXEntityMapper gpxEntityMapper, GPXRepository repository
            , UploadResponseMapper uploadResponseMapper) {
        this.gpxEntityMapper = gpxEntityMapper;
        this.repository = repository;
        this.uploadResponseMapper = uploadResponseMapper;
    }

    public GPXUploadResponse store(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw GPXExceptionHelper.getInstance().serverError(new NullPointerException());
        }
        GPXEntity entity = gpxEntityMapper.from(GPXParserUtils.parse(file));
        GPXEntity gpxEntity = repository.save(entity);
        return uploadResponseMapper.from(gpxEntity);
    }
}
