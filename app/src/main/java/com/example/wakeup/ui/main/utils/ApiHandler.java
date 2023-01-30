package com.example.wakeup.ui.main.utils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandler {
    private static ApiHandler instance;
    private Map<String, Retrofit> retrofits;
    private Map<String, Object> apiServices;

    private ApiHandler() {
        retrofits = new HashMap<>();
        apiServices = new HashMap<>();
    }

    public static ApiHandler getInstance() {
        if (instance == null) {
            instance = new ApiHandler();
        }
        return instance;
    }

    public <T> T createApiService(String baseUrl, Class<T> serviceClass) {
        if (!retrofits.containsKey(baseUrl)) {
            retrofits.put(baseUrl, new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build());
        }
        if (!apiServices.containsKey(serviceClass.getName())) {
            apiServices.put(serviceClass.getName(), retrofits.get(baseUrl).create(serviceClass));
        }
        return (T) apiServices.get(serviceClass.getName());
    }
}



