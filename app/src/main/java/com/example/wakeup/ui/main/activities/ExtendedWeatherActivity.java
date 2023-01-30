package com.example.wakeup.ui.main.activities;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.database.viewmodels.LocationViewModel;
import com.example.wakeup.ui.main.fragments.MapFragment;
import com.example.wakeup.ui.main.fragments.WeatherFragment;
import com.example.wakeup.ui.main.models.UserLocation;
import com.google.android.gms.internal.location.zzbp;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ExtendedWeatherActivity extends AppCompatActivity {
    private Location lastLocation;
    private FloatingActionButton addLocationButton;
    private LocationViewModel locationViewModel;
    private List<UserLocation> allLocations;
    private ImageView prevLocationArrow;
    private ImageView nextLocationArrow;
    private UserLocation currentLocation;
    private TextView locationTextView;
    private int currentLocationIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_weather);
        locationTextView = findViewById(R.id.location_text_view);
        addLocationButton = findViewById(R.id.add_location_button);
        prevLocationArrow = findViewById(R.id.previous_location_arrow);
        nextLocationArrow = findViewById(R.id.next_location_arrow);
        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFragment fragment = new MapFragment();
                replaceFragment(fragment);
                }
        });

        currentLocationIndex = 0;
        prevLocationArrow.setOnClickListener(v -> {
            if (1 == currentLocationIndex){
                getCurrentLocation();
                currentLocationIndex--;
            }
            if (1 < currentLocationIndex){
                currentLocationIndex--;
                currentLocation = allLocations.get(currentLocationIndex - 1);
                WeatherFragment fragment = WeatherFragment.newInstance("extended", currentLocation.getLatitude(), currentLocation.getLongtitude());
                locationTextView.setText(currentLocation.getName());
                replaceFragment(fragment);
            }
        });

        nextLocationArrow.setOnClickListener(v -> {
            if (allLocations.size() > currentLocationIndex){
                currentLocationIndex++;
                currentLocation = allLocations.get(currentLocationIndex - 1);
                WeatherFragment fragment = WeatherFragment.newInstance("extended", currentLocation.getLatitude(), currentLocation.getLongtitude());
                locationTextView.setText(currentLocation.getName());
                replaceFragment(fragment);
            }
        });
        createFragment();
    }

    private void updateList() {
        locationViewModel = new ViewModelProvider(this).get(LocationViewModel.class);
        locationViewModel.getAllLocations().observe(this, new Observer<List<UserLocation>>() {
            @Override
            public void onChanged(List<UserLocation> locations) {
                allLocations = locations;
            }
        });
    }

    public static final int REQUEST_LOCATION_PERMISSION = 100;

    public void getCurrentLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
            }
        };
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
        FusedLocationProviderClient fusedLocationClient = getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                lastLocation = location;
                WeatherFragment fragment = WeatherFragment.newInstance("extended", lastLocation.getLatitude(), lastLocation.getLongitude());
                locationTextView.setText("Your current location");
                replaceFragment(fragment);
            }
        });
    }

    protected void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    protected void createFragment(){
        getCurrentLocation();
        updateList();
    }
}