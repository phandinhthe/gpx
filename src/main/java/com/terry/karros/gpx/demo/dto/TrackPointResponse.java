package com.terry.karros.gpx.demo.dto;

public class TrackPointResponse extends WayPointResponse {
    private double elevation;
    private String dateTime;

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
