package com.hackathonproject.models;

/**
 * Created by shubhamgupta on 21/06/2017.
 */

public class User {


    private String email, name, phone, url, tag;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String email, String phone, String url, String tag) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.tag = tag;
        this.url = url;
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
