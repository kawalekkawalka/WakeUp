package com.example.wakeup.ui.main.controllers.weather;

import android.content.res.Resources;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.utils.App;

public class NightTemperatureDecorator extends WeatherDecorator{
    private String temperature;

    public NightTemperatureDecorator(Weather weather, String condition) {
        super(weather);
        this.temperature = condition;
    }

    public String printWeather(){
        return super.printWeather() + temperature + App.getRes().getString(R.string.temperature_during_night);
    }
}
