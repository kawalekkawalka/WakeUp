package com.example.wakeup.ui.main.controllers;

import static com.example.wakeup.ui.main.utils.Utils.getWeatherFromApi;

import java.io.IOException;


public class WeatherController {
    public static String getWeather(String type, String Location) throws IOException {
        String[] conditions= getWeatherFromApi();
        System.out.println(conditions);
        return "";
    }
}
