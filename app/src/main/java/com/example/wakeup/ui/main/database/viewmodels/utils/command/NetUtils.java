package com.example.wakeup.ui.main.database.viewmodels.utils.command;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class NetUtils {

    public static HashMap getConditionsFromJson(JsonObject json,HashMap<String,String> conditions){
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
