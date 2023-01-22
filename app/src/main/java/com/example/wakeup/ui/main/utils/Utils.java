package com.example.wakeup.ui.main.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    private static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }


    public static List getWeatherFromApi() throws IOException {
        List conditions = new ArrayList<String>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.open-meteo.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        List<String> hourly = Arrays.asList("temperature_2m", "relativehumidity_2m", "weathercode", "surface_pressure");
        Call<JsonObject> call = weatherApi.getWeatherData(53.13, 23.16, hourly);
        Log.d("Retrofit", call.request().url().toString());
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
                    Log.d("Retrofit", "Nie polaczylo z api :(");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("Retrofit", "Error connecting to API", t);
            }
        });
        return conditions;
    }


}
