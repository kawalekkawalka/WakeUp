package com.example.wakeup.ui.main.controllers;

import static com.example.wakeup.ui.main.utils.Utils.getWeatherFromApi;


import android.util.Log;

import java.io.IOException;
import java.util.List;


public class WeatherController {
    public static String getWeather(String type, String Location) throws IOException {
        Log.d("Retrofit", "Weszlo w controller");
        System.out.println(getWeatherFromApi());
        System.out.println("HHHHHHHHHHHHHHHHHHHHHH");
        return "";
    }
}
