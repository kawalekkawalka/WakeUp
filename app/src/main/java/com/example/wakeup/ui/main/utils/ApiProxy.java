package com.example.wakeup.ui.main.utils;

import android.util.Log;
import android.widget.TextView;

import com.example.wakeup.ui.main.controllers.WeatherController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public void getWeatherData(TextView conditionsContainer, String weatherType){
        WeatherApi apiService = this.createApiService("https://api.open-meteo.com/v1/",WeatherApi.class);
        final HashMap<String, String>[] conditions = new HashMap[]{new HashMap<String, String>()};
        List<String> hourly = Arrays.asList("temperature_2m", "relativehumidity_2m", "weathercode", "surface_pressure");
        Call<JsonObject> call = apiService.getWeatherData(53.13, 23.16, hourly);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Log.d("Retrofit", "Polaczylo z api");
                    JsonObject json = response.body();
                    conditions[0] = getConditionsFromJson(json);
                    WeatherController weatherController = new WeatherController();
                    if (weatherType == "extended"){
                        conditionsContainer.setText(weatherController.getExtendedWeather(conditions[0]));
                    }
                    else{
                        conditionsContainer.setText(weatherController.getNormalWeather(conditions[0]));
                    }
                }
                else {
                    try {
                        FileReader reader = new FileReader("java/com/example/wakeup/ui/main/data/weatherjson");
                        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
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
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private HashMap getConditionsFromJson(JsonObject json){
        HashMap<String, String> conditions = new HashMap<String, String>();
        JsonObject hourlyData = json.get("hourly").getAsJsonObject();
        JsonArray temporaryArray = hourlyData.get("weathercode").getAsJsonArray();
        conditions.put("weathercode_day", temporaryArray.get(13).getAsString());
        conditions.put("weathercode_night", temporaryArray.get(25).getAsString());
        temporaryArray = hourlyData.get("temperature_2m").getAsJsonArray();
        conditions.put("temperature_2m_day", temporaryArray.get(13).getAsString());
        conditions.put("temperature_2m_night", temporaryArray.get(25).getAsString());
        temporaryArray = hourlyData.get("relativehumidity_2m").getAsJsonArray();
        conditions.put("relativehumidity_2m_day", temporaryArray.get(13).getAsString());
        conditions.put("relativehumidity_2m_night", temporaryArray.get(25).getAsString());
        temporaryArray = hourlyData.get("surface_pressure").getAsJsonArray();
        conditions.put("surface_pressure_day", temporaryArray.get(13).getAsString());
        conditions.put("surface_pressure_night", temporaryArray.get(25).getAsString());
        return conditions;
    }
}