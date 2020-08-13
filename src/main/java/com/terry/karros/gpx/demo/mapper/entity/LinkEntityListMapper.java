package com.terry.karros.gpx.demo.mapper.entity;

import com.terry.karros.gpx.demo.entity.LinkEntity;
import io.jenetics.jpx.Link;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class LinkEntityListMapper {
    public List<LinkEntity> from(List<Link> links) {
        if (CollectionUtils.isEmpty(links)) {
            return new ArrayList<>();
        }
        List<LinkEntity> linkEntities = new ArrayList<>();
        for (Link link : links) {
            String url = Objects.isNull(link.getHref()) ? null : link.getHref().toString();
            String text = link.getText().orElse(null);
            linkEntities.add(buildLinkEntity(url, text));
        }

        return linkEntities;
    }

    private LinkEntity buildLinkEntity(String href, String text) {
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setText(text);
        linkEntity.setHref(href);
        return linkEntity;
    }
}
