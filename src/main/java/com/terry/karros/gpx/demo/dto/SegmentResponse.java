package com.terry.karros.gpx.demo.dto;

import java.util.List;

public class SegmentResponse {
    private List<TrackPointResponse> trackPoints;

    public List<TrackPointResponse> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<TrackPointResponse> trackPoints) {
        this.trackPoints = trackPoints;
    }
}
