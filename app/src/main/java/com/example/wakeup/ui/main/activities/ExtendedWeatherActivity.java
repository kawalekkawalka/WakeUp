package com.example.wakeup.ui.main.activities;

import static com.example.wakeup.ui.main.controllers.WeatherController.getWeather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.fragments.WeatherFragment;

import java.io.IOException;

public class ExtendedWeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_weather);
        try {
            getWeather("","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        createFragment();

    }

    protected void createFragment(){
        WeatherFragment fragment = WeatherFragment.newInstance("POGODA DLA DANEGO MIEJCA");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}