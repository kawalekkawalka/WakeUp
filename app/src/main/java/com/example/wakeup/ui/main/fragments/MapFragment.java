package com.example.wakeup.ui.main.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.activities.ExtendedWeatherActivity;
import com.example.wakeup.ui.main.database.viewmodels.LocationViewModel;
import com.example.wakeup.ui.main.models.UserLocation;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapFragment extends Fragment {

    private LocationViewModel locationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map_view);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {

                        Dialog dialog = new Dialog(getActivity());
                        UserLocation newLocation = new UserLocation();
                        dialog.setContentView(R.layout.dialog_add_location);
                        final EditText name = dialog.findViewById(R.id.new_task_name);
                        final EditText latitude = dialog.findViewById(R.id.new_task_latitude);
                        latitude.setText(String.valueOf(latLng.latitude));
                        final EditText longtitude = dialog.findViewById(R.id.new_task_longtitude);
                        longtitude.setText(String.valueOf(latLng.longitude));
                        Button addLocationBtn = dialog.findViewById(R.id.btn_add_location);
                        addLocationBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                newLocation.setName(name.getText().toString());
                                newLocation.setLatitude(Double.valueOf(String.format(Locale.US,"%.2f", Double.valueOf(latitude.getText().toString()))));
                                newLocation.setLongtitude(Double.valueOf(String.format(Locale.US,"%.2f", Double.valueOf(longtitude.getText().toString()))));
                                locationViewModel = new ViewModelProvider(getActivity()).get(LocationViewModel.class);
                                locationViewModel.insert(newLocation);
                                dialog.dismiss();
                                Intent switchActivityIntent = new Intent(getActivity(), ExtendedWeatherActivity.class);
                                startActivity(switchActivityIntent);
                            }
                        });
                        dialog.show();

                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                        googleMap.clear();
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });
        return view;
    }
}