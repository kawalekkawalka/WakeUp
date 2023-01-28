package com.example.wakeup.ui.main.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.utils.ApiProxy;

public class WeatherFragment extends Fragment {

    private static final String ARG_PARAM1 = "";

    TextView conditionsContainer;
    String weatherConditions;

    public static WeatherFragment newInstance(String param1) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weatherConditions = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        conditionsContainer = rootView.findViewById(R.id.conditonsContainer);
        ApiProxy proxy = new ApiProxy();
        String weatherType = getArguments().getString(ARG_PARAM1);
        Log.d("testy", weatherType);
        proxy.getWeatherData(conditionsContainer, weatherType);

        conditionsContainer.setText(weatherConditions);
        return rootView;
    }





}
