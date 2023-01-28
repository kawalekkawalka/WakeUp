package com.example.wakeup.ui.main.controllers.weather;

public class DayWeathercodeDecorator extends WeatherDecorator{
    private String weathercode;

    public DayWeathercodeDecorator(Weather weather, String condition) {
        super(weather);
        this.weathercode = condition;
    }

    public String printWeather(){
        String weathercodeName;
        switch (weathercode){
            case "0":
                weathercodeName = "Czyste niebo";
                break;
            case "3":
                weathercodeName = "Pełnie zachmurzenie";
                break;
            default:
                weathercodeName = "Brak informacji o pogodzie";
        }

        return super.printWeather() + weathercodeName + "\n";
    }
}
