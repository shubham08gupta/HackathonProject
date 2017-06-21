package com.hackathonproject.models;

/**
 * Created by shubhamgupta on 21/06/2017.
 */

public class User {


    private String email, name, phone, url, tag;
    private Double lat, lon;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String email, String phone, String url, String tag, Double lat, Double lon) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.tag = tag;
        this.url = url;
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getTag() {
        return tag;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUrl() {
        return url;
    }

}
