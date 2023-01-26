package com.example.wakeup.ui.main.utils;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface WeatherApi {
    @GET("forecast")
    Call<JsonObject> getWeatherData(@Query("latitude") double latitude, @Query("longitude") double longitude, @Query("hourly") List<String> hourly);
}

