package com.example.wakeup.ui.main.database.viewmodels.utils;

import android.content.Context;
import android.widget.TextView;

import com.example.wakeup.ui.main.models.News;
import com.example.wakeup.ui.main.news.NewsAPI;
import com.example.wakeup.ui.main.news.NewsApiResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiProxy {
    private ApiHandler apiHandler;
    private Map<String, Object> apiServices;
    private Context context;
    private Map<String, JsonObject> weatherCache = new HashMap<>();

    public ApiProxy(Context _context) {
        apiHandler = ApiHandler.getInstance();
        apiServices = new HashMap<>();
        context = _context;
    }

    private <T> T createApiService(String baseUrl, Class<T> serviceClass) {
        if (!apiServices.containsKey(serviceClass.getName())) {
            T service = apiHandler.createApiService(baseUrl, serviceClass);
            apiServices.put(serviceClass.getName(), service);
        }
        return (T) apiServices.get(serviceClass.getName());
    }

    public JsonObject getWeatherData(double latitude, double longitude) throws InterruptedException{
        String key = latitude + "," + longitude;
        JsonObject weatherData = weatherCache.get(key);
        if (weatherData != null) {
            return weatherData;
        }
        WeatherApi apiService = this.createApiService("https://api.open-meteo.com/v1/",WeatherApi.class);
        List<String> hourly = Arrays.asList("temperature_2m", "relativehumidity_2m", "weathercode", "surface_pressure");
        Call<JsonObject> call = apiService.getWeatherData(latitude, longitude, hourly);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject weatherData = response.body();
                    weatherCache.put(key, weatherData);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }
        });
        InputStream is = null;
        try {
            is = context.getAssets().open("weatherjson.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonReader reader = new JsonReader(new InputStreamReader(is));
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        return json;
    }
}
