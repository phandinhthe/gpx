package com.terry.karros.gpx.demo.dto.recent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.terry.karros.gpx.demo.dto.MetadataResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GPXRecentListResponse {
    private List<GPXResponse> gpxResponses;

    public List<GPXResponse> getGpxResponses() {
        return gpxResponses;
    }

    public void setGpxResponses(List<GPXResponse> gpxResponses) {
        this.gpxResponses = gpxResponses;
    }

    public static class GPXResponse {
        private Long id;
        private MetadataResponse metadata;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public MetadataResponse getMetadata() {
            return metadata;
        }

        public void setMetadata(MetadataResponse metadata) {
            this.metadata = metadata;
        }

        public static GPXResponse from(Long id, MetadataResponse metadata) {
            GPXResponse response = new GPXResponse();
            response.setId(id);
            response.setMetadata(metadata);
            return response;
        }
    }
}


