package com.example.wakeup.ui.main.controllers.weather;

import android.content.res.Resources;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.utils.App;

public class NightWeathercodeDecorator extends WeatherDecorator{
    private String weathercode;

    public NightWeathercodeDecorator(Weather weather, String condition) {
        super(weather);
        this.weathercode = condition;
    }

    public String printWeather(){
        String weathercodeName;
        switch (weathercode) {
            case "0":
                weathercodeName = App.getRes().getString(R.string.clear_sky);
                break;
            case "1":
                weathercodeName = App.getRes().getString(R.string.mainly_clear_sky);
                break;
            case "2":
                weathercodeName = App.getRes().getString(R.string.sky_partially_cloudy);
                break;
            case "3":
                weathercodeName = App.getRes().getString(R.string.cloudy);
                break;
            case "45":
                weathercodeName = App.getRes().getString(R.string.fog);
                break;
            case "48":
                weathercodeName = App.getRes().getString(R.string.freezing_fog);
                break;
            case "51":
                weathercodeName = App.getRes().getString(R.string.light_drizzle);
                break;
            case "53":
                weathercodeName = App.getRes().getString(R.string.moderate_drizzle);
                break;
            case "55":
                weathercodeName = App.getRes().getString(R.string.heavy_drizzle);
                break;
            case "56":
                weathercodeName = App.getRes().getString(R.string.light_freezing_drizzle);
                break;
            case "57":
                weathercodeName = App.getRes().getString(R.string.heavy_freezing_drizzle);
                break;
            case "61":
                weathercodeName = App.getRes().getString(R.string.slight_rain);
                break;
            case "63":
                weathercodeName = App.getRes().getString(R.string.moderate_rain);
                break;
            case "65":
                weathercodeName = App.getRes().getString(R.string.heavy_rain);
                break;
            case "66":
                weathercodeName = App.getRes().getString(R.string.slight_freezing_rain);
                break;
            case "67":
                weathercodeName = App.getRes().getString(R.string.intense_freezing_rain);
                break;
            case "71":
                weathercodeName = App.getRes().getString(R.string.slight_snow_fall);
                break;
            case "73":
                weathercodeName = App.getRes().getString(R.string.moderate_snow_fall);
                break;
            case "75":
                weathercodeName = App.getRes().getString(R.string.heavy_snow_fall);
                break;
            case "77":
                weathercodeName = App.getRes().getString(R.string.blizzard);
                break;
            case "80":
                weathercodeName = App.getRes().getString(R.string.slight_rain_shower);
                break;
            case "81":
                weathercodeName = App.getRes().getString(R.string.moderate_rain_shower);
                break;
            case "82":
                weathercodeName = App.getRes().getString(R.string.violent_rain_shower);
                break;
            case "85":
                weathercodeName = App.getRes().getString(R.string.slight_snow_shower);
                break;
            case "86":
                weathercodeName = App.getRes().getString(R.string.heavy_snow_shower);
                break;
            case "95":
                weathercodeName = App.getRes().getString(R.string.slight_thunderstorm);
                break;
            case "96":
                weathercodeName = App.getRes().getString(R.string.slight_thunderstorm_hail);
                break;
            case "99":
                weathercodeName = App.getRes().getString(R.string.heavy_thunderstorm_hail);
                break;
            default:
                weathercodeName = App.getRes().getString(R.string.missing_weather_information);
        }


        return super.printWeather() + weathercodeName + " " + App.getRes().getString(R.string.night_weathercode_decorator);
    }
}
