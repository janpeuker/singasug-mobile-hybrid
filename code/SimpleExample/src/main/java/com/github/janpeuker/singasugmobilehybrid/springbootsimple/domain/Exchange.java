package com.github.janpeuker.singasugmobilehybrid.springbootsimple.domain;

/**
 * Created by janpeuker on 30/10/14.
 */
public class Exchange {
    private String name;
    private String location;

    public Exchange(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
