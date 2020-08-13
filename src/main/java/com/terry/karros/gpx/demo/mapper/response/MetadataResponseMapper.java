package com.terry.karros.gpx.demo.mapper.response;

import com.terry.karros.gpx.demo.dto.MetadataResponse;
import com.terry.karros.gpx.demo.entity.MetadataEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MetadataResponseMapper {
    private final LinkResponseMapper linkResponseMapper;

    public MetadataResponseMapper(LinkResponseMapper linkResponseMapper) {
        this.linkResponseMapper = linkResponseMapper;
    }

    public MetadataResponse from(MetadataEntity entity) {
        if (Objects.isNull(entity)) {
            return new MetadataResponse();
        }

        MetadataResponse response = new MetadataResponse();
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setLinkResponses(linkResponseMapper.from(entity.getLinks()));
        response.setAuthor(entity.getAuthor());
        return response;
    }
}
