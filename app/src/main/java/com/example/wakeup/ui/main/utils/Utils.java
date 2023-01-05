package com.example.wakeup.ui.main.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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


    public static String[] getWeatherFromApi() throws IOException {
        String API_URL = "https://api.open-meteo.com/v1/forecast?latitude=53.13&longitude=23.16&hourly=temperature_2m,relativehumidity_2m,weathercode,surface_pressure";
        String temperature;
        String humidity;
        String weatherCode;
        String surfacePressure;

        URL url = new URL(API_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());

//            // convert the input stream to a string
//            String responseString = readStream(in);
//            // parse the string as a JSON object
//            Gson gson = new Gson();
//            JsonObject json = gson.fromJson(responseString, JsonObject.class);
//            // access the data in the JSON object
//            temperature = json.get("temperature_2m").getAsString();
//            humidity = json.get("relativehumidity_2m").getAsString();
//            weatherCode = json.get("weathercode").getAsString();
//            surfacePressure = json.get("surface_pressure").getAsString();

        urlConnection.disconnect();
        String[] conditions = {"temperature, humidity, weatherCode, surfacePressure"};
        return conditions;
    }
}
