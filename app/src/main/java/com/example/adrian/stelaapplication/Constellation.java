package com.example.adrian.stelaapplication;

import android.support.annotation.NonNull;

/**
 * Created by adrian on 2/19/18.
 */

public class Constellation implements Comparable<Constellation> {

    double[] coordinates;
    String name;
    String description;
    double distance;

    // Constructor
    public Constellation() {
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(@NonNull Constellation constellation) {
        return name.compareTo(constellation.name);
    }
}
