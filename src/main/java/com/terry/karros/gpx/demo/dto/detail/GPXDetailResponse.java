package com.terry.karros.gpx.demo.dto.detail;

import com.terry.karros.gpx.demo.dto.MetadataResponse;
import com.terry.karros.gpx.demo.dto.TrackResponse;
import com.terry.karros.gpx.demo.dto.WayPointResponse;

import java.util.List;

public class GPXDetailResponse {
    private long id;
    private MetadataResponse metadata;
    private List<WayPointResponse> wayPoints;
    private List<TrackResponse> tracks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MetadataResponse getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataResponse metadata) {
        this.metadata = metadata;
    }

    public List<WayPointResponse> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(List<WayPointResponse> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public List<TrackResponse> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackResponse> track) {
        this.tracks = track;
    }
}
