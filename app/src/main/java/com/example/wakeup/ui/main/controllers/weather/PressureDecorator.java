package com.example.wakeup.ui.main.controllers.weather;

import android.content.res.Resources;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.utils.App;

public class PressureDecorator extends WeatherDecorator{
    private String day_pressure;
    private String night_pressure;

    public PressureDecorator(Weather weather, String day_pressure, String night_pressure) {
        super(weather);
        this.day_pressure = day_pressure;
        this.night_pressure = night_pressure;
    }

    public String printWeather(){
        return super.printWeather() + day_pressure + App.getRes().getString(R.string.pressure_during_day)+
                night_pressure +App.getRes().getString(R.string.pressure_during_night);
    }
}
