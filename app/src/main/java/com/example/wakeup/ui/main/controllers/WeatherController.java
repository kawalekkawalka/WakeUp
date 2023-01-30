package com.example.wakeup.ui.main.controllers;

import android.util.Log;

import com.example.wakeup.ui.main.controllers.weather.BasicWeather;
import com.example.wakeup.ui.main.controllers.weather.DayTemperatureDecorator;
import com.example.wakeup.ui.main.controllers.weather.DayWeathercodeDecorator;
import com.example.wakeup.ui.main.controllers.weather.HumidityDecorator;
import com.example.wakeup.ui.main.controllers.weather.NightTemperatureDecorator;
import com.example.wakeup.ui.main.controllers.weather.NightWeathercodeDecorator;
import com.example.wakeup.ui.main.controllers.weather.PressureDecorator;

import java.io.IOException;
import java.util.HashMap;


public class WeatherController {
    public String getNormalWeather(HashMap<String, String> conditions){
        BasicWeather basicWeather = new BasicWeather();
        DayTemperatureDecorator dayTemperatureDecorator = new DayTemperatureDecorator(basicWeather, conditions.get("temperature_2m_day"));
        DayWeathercodeDecorator dayWeathercodeDecorator = new DayWeathercodeDecorator(dayTemperatureDecorator, conditions.get("weathercode_day"));
        return dayWeathercodeDecorator.printWeather();
    }

    public String getExtendedWeather(HashMap<String, String> conditions){
        BasicWeather basicWeather = new BasicWeather();
        DayTemperatureDecorator dayTemperatureDecorator = new DayTemperatureDecorator(basicWeather, conditions.get("temperature_2m_day"));
        NightTemperatureDecorator nightTemperatureDecorator = new NightTemperatureDecorator(dayTemperatureDecorator, conditions.get("temperature_2m_day"));
        DayWeathercodeDecorator dayWeathercodeDecorator = new DayWeathercodeDecorator(nightTemperatureDecorator, conditions.get("weathercode_day"));
        NightWeathercodeDecorator nightWeathercodeDecorator = new NightWeathercodeDecorator(dayWeathercodeDecorator, conditions.get("weathercode_night"));
        PressureDecorator pressureDecorator = new PressureDecorator(nightWeathercodeDecorator, conditions.get("surface_pressure_day"), conditions.get("surface_pressure_night"));
        HumidityDecorator humidityDecorator = new HumidityDecorator(pressureDecorator, conditions.get("relativehumidity_2m_day"), conditions.get("relativehumidity_2m_night"));
        return humidityDecorator.printWeather();
    }
}
