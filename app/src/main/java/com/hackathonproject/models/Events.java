package com.hackathonproject.models;

/**
 * Created by shubhamgupta on 21/06/2017.
 */

public class Events {

    private String name, description;
    private Double lat, lon;

    public Events() {

    }

    public Events(String name, String description, Double lat, Double lon) {
        this.name = name;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }
}
