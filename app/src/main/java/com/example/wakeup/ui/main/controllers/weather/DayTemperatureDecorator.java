package com.example.wakeup.ui.main.controllers.weather;

public class DayTemperatureDecorator extends WeatherDecorator{
    private String temperature;

    public DayTemperatureDecorator(Weather weather, String condition) {
        super(weather);
        this.temperature = condition;
    }

    public String printWeather(){
        return super.printWeather() + temperature + " °C during tha day\n";
    }
}
