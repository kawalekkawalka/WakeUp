package com.example.wakeup;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wakeup.databinding.ActivityMainBinding;
import com.example.wakeup.ui.main.activities.ExtendedWeatherActivity;
import com.example.wakeup.ui.main.fragments.NewsFragment;
import com.example.wakeup.ui.main.fragments.WeatherFragment;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(WeatherFragment.newInstance("normal"));

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.home:
                    //replaceFragment(new NewsFragment());
                    Intent switchActivityIntent = new Intent(this, ExtendedWeatherActivity.class);
                    startActivity(switchActivityIntent);
                    break;
                case R.id.calendar:
                    //replaceFragment(new CalendarFragment());
                    break;
                case R.id.alarm:
                    //replaceFragment(new AlarmFragment());
                    break;
            }

            return true;
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}