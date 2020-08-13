package com.terry.karros.gpx.demo.dto;

import java.util.List;

public class TrackResponse {
    private List<SegmentResponse> segments;

    public List<SegmentResponse> getSegments() {
        return segments;
    }

    public void setSegments(List<SegmentResponse> segments) {
        this.segments = segments;
    }
}
