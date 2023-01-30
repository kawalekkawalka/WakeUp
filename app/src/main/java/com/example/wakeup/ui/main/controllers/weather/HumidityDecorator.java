package com.example.wakeup.ui.main.controllers.weather;

import android.content.res.Resources;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.utils.App;

public class HumidityDecorator extends WeatherDecorator{
    private String day_humidity;
    private String night_humidity;

    public HumidityDecorator(Weather weather, String day_humidity, String night_humidity) {
        super(weather);
        this.day_humidity = day_humidity;
        this.night_humidity = night_humidity;
    }

    public String printWeather(){
        return super.printWeather() + day_humidity + App.getRes().getString(R.string.humidity_during_the_day) +
                night_humidity + App.getRes().getString(R.string.humidity_during_the_night);
    }
}
