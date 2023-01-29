package com.example.wakeup.ui.main.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.controllers.WeatherController;
import com.example.wakeup.ui.main.database.viewmodels.utils.ApiProxy;
import com.example.wakeup.ui.main.database.viewmodels.utils.command.NetUtils;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class WeatherFragment extends Fragment {

    private static final String ARG_TYPE = "type";
    private static final String ARG_LATITUDE = "latitude";
    private static final String ARG_LONGTITUDE = "longtitude";

    TextView conditionsContainer;
    String weatherType;
    double latitude;
    double longtitude;

    public static WeatherFragment newInstance(String param1, double param2, double param3) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, param1);
        args.putDouble(ARG_LATITUDE, param2);
        args.putDouble(ARG_LONGTITUDE, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weatherType = getArguments().getString(ARG_TYPE);
            latitude = getArguments().getDouble(ARG_LATITUDE);
            longtitude = getArguments().getDouble(ARG_LONGTITUDE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        conditionsContainer = rootView.findViewById(R.id.conditions_container);
        ApiProxy proxy = new ApiProxy(getContext());
        Log.d("testy", weatherType);
        JsonObject json = null;
        try {
            json = proxy.getWeatherData(latitude, longtitude);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HashMap<String,String> conditions = new HashMap<>();
        conditions = NetUtils.getConditionsFromJson(json,conditions);
        WeatherController weatherController = new WeatherController();
        if (weatherType == "extended"){
            conditionsContainer.setText(weatherController.getExtendedWeather(conditions));
        }
        else{
            conditionsContainer.setText(weatherController.getNormalWeather(conditions));
        }
        return rootView;
    }

}
