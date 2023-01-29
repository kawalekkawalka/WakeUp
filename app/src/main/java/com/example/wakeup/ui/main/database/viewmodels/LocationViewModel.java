package com.example.wakeup.ui.main.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wakeup.ui.main.database.repositories.LocationRepository;
import com.example.wakeup.ui.main.models.UserLocation;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {
    private final LocationRepository locationRepository;
    private final LiveData<List<UserLocation>> locations;

    public LocationViewModel(@NonNull Application application) {
        super(application);
        locationRepository = new LocationRepository(application);
        locations = locationRepository.getAllLocations();
    }

    public LiveData<List<UserLocation>> getAllLocations() {
        return locations;
    }

    public void insert(UserLocation location) {
        locationRepository.insert(location);
    }

    public void update(UserLocation location) {
        locationRepository.update(location);
    }

    public void delete(UserLocation location) {
        locationRepository.delete(location);
    }
}
