package com.example.wakeup.ui.main.database.viewmodels;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wakeup.ui.main.database.repositories.AlarmRepository;
import com.example.wakeup.ui.main.database.repositories.TaskRepository;
import com.example.wakeup.ui.main.models.Alarm;
import com.example.wakeup.ui.main.models.Task;

import java.time.LocalDate;
import java.util.List;

public class AlarmViewModel extends AndroidViewModel {

    private final AlarmRepository alarmRepository;
    private final LiveData<List<Alarm>> alarms;

    public AlarmViewModel(@NonNull Application application) {
        super(application);
        alarmRepository = new AlarmRepository(application);
        alarms = alarmRepository.getAllAlarms();
    }

    public LiveData<List<Alarm>> getAllAlarms() {
        return alarms;
    }

    public void insert(Alarm alarm) {
        alarmRepository.insert(alarm);
    }

    public void update(Alarm alarm) { alarmRepository.update(alarm); }

    public void delete(Alarm alarm) {
        alarmRepository.delete(alarm);
    }
}
