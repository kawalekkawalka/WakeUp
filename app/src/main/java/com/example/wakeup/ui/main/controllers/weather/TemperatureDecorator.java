package com.example.wakeup.ui.main.controllers.weather;

public class TemperatureDecorator extends WeatherDecorator{

    public TemperatureDecorator(Weather weather) {
        super(weather);
    }

    public String printWeather(){
        return super.printWeather() + "20 stopni";
    }
}
