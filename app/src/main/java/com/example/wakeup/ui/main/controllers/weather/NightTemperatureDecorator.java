package com.example.wakeup.ui.main.controllers.weather;

public class NightTemperatureDecorator extends WeatherDecorator{
    private String temperature;

    public NightTemperatureDecorator(Weather weather, String condition) {
        super(weather);
        this.temperature = condition;
    }

    public String printWeather(){
        return super.printWeather() + temperature + " stopni Celsjusza w nocy\n";
    }
}
