package com.terry.karros.gpx.demo.mapper.entity;

import com.terry.karros.gpx.demo.entity.MetadataEntity;
import io.jenetics.jpx.Metadata;
import io.jenetics.jpx.Person;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class MetadataMapper {
    private final LinkEntityListMapper linkEntityListMapper;

    public MetadataMapper(LinkEntityListMapper linkEntityListMapper) {
        this.linkEntityListMapper = linkEntityListMapper;
    }

    public Optional<MetadataEntity> from(Metadata metadata) {
        if (Objects.isNull(metadata)) {
            return Optional.empty();
        }
        MetadataEntity entity = new MetadataEntity();
        entity.setDescription(metadata.getDescription().orElse(null));
        entity.setName(metadata.getName().orElse(null));
        entity.setAuthor(getAuthor(metadata));
        entity.setLinks(linkEntityListMapper.from(metadata.getLinks()));
        return Optional.ofNullable(entity);
    }

    private String getAuthor(Metadata metadata) {
        Person person = metadata.getAuthor().orElse(Person.of());
        return person.getName().orElse(null);
    }
}
