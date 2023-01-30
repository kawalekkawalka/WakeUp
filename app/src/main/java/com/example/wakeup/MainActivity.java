package com.example.wakeup;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.wakeup.ui.main.fragments.AlarmFragment;
import com.example.wakeup.ui.main.fragments.TaskListFragment;
import com.example.wakeup.databinding.ActivityMainBinding;
import com.example.wakeup.ui.main.activities.ExtendedWeatherActivity;
import com.example.wakeup.ui.main.fragments.NewsFragment;
import com.example.wakeup.ui.main.fragments.TaskListFragment;
import com.example.wakeup.ui.main.fragments.WeatherFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_LOCATION_PERMISSION = 100;
    ActivityMainBinding binding;
    Location lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        replaceFragment(new NewsFragment());

        NotificationChannel channel = new NotificationChannel(getString(R.string.channelName), "TaskNotification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.home:
                    replaceFragment(new NewsFragment());
//                    Intent switchActivityIntent = new Intent(this, ExtendedWeatherActivity.class);
//                    startActivity(switchActivityIntent);
                    break;
                case R.id.calendar:
                    replaceFragment(new TaskListFragment());
                    break;
                case R.id.alarm:
                    replaceFragment(new AlarmFragment());
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