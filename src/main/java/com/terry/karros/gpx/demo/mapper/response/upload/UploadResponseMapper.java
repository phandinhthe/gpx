package com.terry.karros.gpx.demo.mapper.response.upload;

import com.terry.karros.gpx.demo.dto.upload.GPXUploadResponse;
import com.terry.karros.gpx.demo.entity.GPXEntity;
import org.springframework.stereotype.Component;

@Component
public class UploadResponseMapper {
    public GPXUploadResponse from(GPXEntity entity) {
        GPXUploadResponse response = new GPXUploadResponse();
        response.setId(entity.getId());
        return response;
    }
}
