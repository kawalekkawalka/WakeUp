package com.example.wakeup.ui.main.controllers;

import static com.example.wakeup.ui.main.utils.Utils.getWeatherFromApi;


import android.util.Log;

import com.example.wakeup.ui.main.controllers.weather.BasicWeather;
import com.example.wakeup.ui.main.controllers.weather.DayTemperatureDecorator;
import com.example.wakeup.ui.main.controllers.weather.DayWeathercodeDecorator;

import java.io.IOException;
import java.util.HashMap;


public class WeatherController {
    public String getNormalWeather(HashMap<String, String> conditions){
        Log.d("Retrofit", "Weszlo w controller");
        BasicWeather basicWeather = new BasicWeather();
        DayWeathercodeDecorator dayWeathercodeDecorator = new DayWeathercodeDecorator(
                new DayTemperatureDecorator(basicWeather, conditions.get("temperature_2m_day")), conditions.get("weathercode_day")
        );
        return dayWeathercodeDecorator.printWeather();
    }

    public String getExtendedWeather(HashMap<String, String> conditions){
        //use more decorators than normal weather
        return "";
    }
}
