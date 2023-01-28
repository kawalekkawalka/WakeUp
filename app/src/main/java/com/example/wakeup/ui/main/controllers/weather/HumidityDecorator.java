package com.example.wakeup.ui.main.controllers.weather;

public class HumidityDecorator extends WeatherDecorator{
    private String day_humidity;
    private String night_humidity;

    public HumidityDecorator(Weather weather, String day_humidity, String night_humidity) {
        super(weather);
        this.day_humidity = day_humidity;
        this.night_humidity = night_humidity;
    }

    public String printWeather(){
        return super.printWeather() + "W dzień " + day_humidity +"% wilgotności\n"+
                "W nocy " + night_humidity +"% wilgotności\n";
    }
}
