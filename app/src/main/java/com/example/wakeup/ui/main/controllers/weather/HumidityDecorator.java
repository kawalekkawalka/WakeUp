package com.example.wakeup.ui.main.controllers.weather;

public class HumidityDecorator extends WeatherDecorator{

    public HumidityDecorator(Weather weather) {
        super(weather);
    }

    public String printWeather(){
        return super.printWeather() + "50% wilgotności";
    }
}
