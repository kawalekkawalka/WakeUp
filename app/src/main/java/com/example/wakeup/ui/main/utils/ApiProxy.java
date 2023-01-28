package com.example.wakeup.ui.main.utils;
import android.util.Log;

import com.example.wakeup.ui.main.utils.ApiHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiProxy {
    private ApiHandler apiHandler;
    private Map<String, Object> apiServices;

    public ApiProxy() {
        apiHandler = ApiHandler.getInstance();
        apiServices = new HashMap<>();
    }

    private <T> T createApiService(String baseUrl, Class<T> serviceClass) {
        if (!apiServices.containsKey(serviceClass.getName())) {
            T service = apiHandler.createApiService(baseUrl, serviceClass);
            apiServices.put(serviceClass.getName(), service);
        }
        return (T) apiServices.get(serviceClass.getName());
    }

    public List getWeatherData(){
        WeatherApi apiService = this.createApiService("https://api.open-meteo.com/v1/",WeatherApi.class);

        List<String> conditions = new ArrayList<>();
        List<String> hourly = Arrays.asList("temperature_2m", "relativehumidity_2m", "weathercode", "surface_pressure");
        Call<JsonObject> call = apiService.getWeatherData(53.13, 23.16, hourly);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    // Parse the JSON response and extract the temperature and humidity values
                    Log.d("Retrofit", "Polaczylo z api");
                    JsonObject json = response.body();
                    String weatherCode = json.get("timezone").getAsString();
                    Log.d("Retrofit", weatherCode);
//                    String temperature = json.get("temperature_2m").getAsString();
//                    String humidity = json.get("relativehumidity_2m").getAsString();
//                    String surfacePressure = json.get("surface_pressure").getAsString();
//                    Collections.addAll(conditions,temperature, humidity, weatherCode, surfacePressure);
                }
                else {
                    try {
                        FileReader reader = new FileReader("java/com/example/wakeup/ui/main/data/weatherjson");
                        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                        String weatherCode = json.get("timezone").getAsString();
                        Log.d("Retrofit", weatherCode);
//                    String temperature = json.get("temperature_2m").getAsString();
//                    String humidity = json.get("relativehumidity_2m").getAsString();
//                    String surfacePressure = json.get("surface_pressure").getAsString();
//                    Collections.addAll(conditions,temperature, humidity, weatherCode, surfacePressure);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                try {
                    FileReader reader = new FileReader("java/com/example/wakeup/ui/main/data/weatherjson");
                    JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                    String weatherCode = json.get("timezone").getAsString();
                    Log.d("Retrofit", weatherCode);
//                    String temperature = json.get("temperature_2m").getAsString();
//                    String humidity = json.get("relativehumidity_2m").getAsString();
//                    String surfacePressure = json.get("surface_pressure").getAsString();
//                    Collections.addAll(conditions,temperature, humidity, weatherCode, surfacePressure);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        return conditions;
    }
}