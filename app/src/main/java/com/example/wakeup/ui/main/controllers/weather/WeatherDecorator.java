package com.example.wakeup.ui.main.controllers.weather;

public abstract class WeatherDecorator implements Weather{
    private Weather wrapee;

    public WeatherDecorator(Weather weather){
        this.wrapee = weather;
    }

    @Override
    public String printWeather(){
        return wrapee.printWeather();
    }
}
