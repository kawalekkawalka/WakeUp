package com.example.wakeup.ui.main.database.repositories;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.wakeup.ui.main.database.AppDatabase;
import com.example.wakeup.ui.main.database.dao.AlarmDao;
import com.example.wakeup.ui.main.models.Alarm;

import java.util.List;

public class AlarmRepository {

    private final AlarmDao alarmDao;
    private final LiveData<List<Alarm>> allAlarms;

    public AlarmRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        alarmDao = db.alarmDao();
        allAlarms = alarmDao.getAll();
    }

    public LiveData<List<Alarm>> getAllAlarms() { return allAlarms; }

    public void insert(Alarm alarm) {
        AppDatabase.databaseWriteExecutor.execute(() -> alarmDao.insert(alarm));
    }

    public void update(Alarm alarm) {
        AppDatabase.databaseWriteExecutor.execute(() -> alarmDao.update(alarm));
    }

    public void delete(Alarm alarm) {
        AppDatabase.databaseWriteExecutor.execute(() -> alarmDao.delete(alarm));
    }
}
