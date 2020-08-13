package com.terry.karros.gpx.demo.dto;

import java.util.List;

public class MetadataResponse {
    private String name;
    private String description;
    private List<LinkResponse> linkResponses;
    private String author;

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

    public List<LinkResponse> getLinkResponses() {
        return linkResponses;
    }

    public void setLinkResponses(List<LinkResponse> linkResponses) {
        this.linkResponses = linkResponses;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
