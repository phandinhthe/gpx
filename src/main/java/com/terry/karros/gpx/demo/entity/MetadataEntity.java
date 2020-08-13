package com.terry.karros.gpx.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "Metadata")
public class MetadataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metadata_entity_sequence")
    @SequenceGenerator(name = "metadata_entity_sequence", allocationSize = 10)
    private long id;

    private String name;

    @Column(columnDefinition = "NTEXT")
    private String description;

    private ZonedDateTime time;

    private String author;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LinkEntity> links;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<LinkEntity> getLinks() {
        return links;
    }

    public void setLinks(List<LinkEntity> links) {
        this.links = links;
    }
}
