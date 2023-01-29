package com.example.wakeup.ui.main.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.wakeup.ui.main.database.AppDatabase;
import com.example.wakeup.ui.main.database.dao.LocationDao;
import com.example.wakeup.ui.main.models.UserLocation;

import java.util.List;

public class LocationRepository {
    private final LocationDao locationDao;
    private final LiveData<List<UserLocation>> allLocations;

    public LocationRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        locationDao = db.locationDao();
        allLocations = locationDao.getAll();
    }

    public LiveData<List<UserLocation>> getAllLocations() {
        return allLocations;
    }

    public void insert(UserLocation location) {
        AppDatabase.databaseWriteExecutor.execute(() -> locationDao.insert(location));
    }

    public void update(UserLocation location) {
        AppDatabase.databaseWriteExecutor.execute(() -> locationDao.update(location));
    }

    public void delete(UserLocation location) {
        AppDatabase.databaseWriteExecutor.execute(() -> locationDao.delete(location));
    }

}
