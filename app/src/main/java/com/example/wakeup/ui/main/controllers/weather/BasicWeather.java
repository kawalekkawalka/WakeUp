package com.example.wakeup.ui.main.controllers.weather;

import java.sql.Timestamp;

public class BasicWeather implements Weather{
    String location;
    Timestamp time;

    @Override
    public String printWeather() {
        return "";
    }
}
