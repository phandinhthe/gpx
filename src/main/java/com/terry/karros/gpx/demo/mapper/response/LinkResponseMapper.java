package com.terry.karros.gpx.demo.mapper.response;

import com.terry.karros.gpx.demo.dto.LinkResponse;
import com.terry.karros.gpx.demo.entity.LinkEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinkResponseMapper {
    public List<LinkResponse> from(List<LinkEntity> linkEntities) {
        if (CollectionUtils.isEmpty(linkEntities)) {
            return new ArrayList<>();
        }

        List<LinkResponse> responses = new ArrayList<>();
        for (LinkEntity entity : linkEntities) {
            responses.add(from(entity));
        }

        return responses;
    }

    public LinkResponse from(LinkEntity linkEntity) {
        LinkResponse response = new LinkResponse();
        response.setHref(linkEntity.getHref());
        response.setText(linkEntity.getText());
        return response;
    }
}
