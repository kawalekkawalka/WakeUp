package com.example.wakeup.ui.main.controllers.weather;

public class PressureDecorator extends WeatherDecorator{
    private String day_pressure;
    private String night_pressure;

    public PressureDecorator(Weather weather, String day_pressure, String night_pressure) {
        super(weather);
        this.day_pressure = day_pressure;
        this.night_pressure = night_pressure;
    }

    public String printWeather(){
        return super.printWeather() + "W dzień " + day_pressure +" hPa\n"+
                "W nocy " + night_pressure +" hPa\n";
    }
}
