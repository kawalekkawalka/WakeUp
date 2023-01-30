package com.example.wakeup.ui.main.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_location")
public class UserLocation {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private double latitude;

    private double longtitude;

    public UserLocation(int id, String name, double latitude, double longtitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    @Ignore
    public UserLocation() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
