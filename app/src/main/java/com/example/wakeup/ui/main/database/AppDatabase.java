package com.example.wakeup.ui.main.database;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.wakeup.ui.main.database.dao.AlarmDao;
import com.example.wakeup.ui.main.database.dao.TaskDao;
import com.example.wakeup.ui.main.models.Alarm;
import com.example.wakeup.ui.main.models.Task;
import com.example.wakeup.ui.main.models.TaskFinished;
import com.example.wakeup.ui.main.models.TaskInProgress;
import com.example.wakeup.ui.main.models.TaskOpen;
import com.example.wakeup.ui.main.models.TaskState;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class, TaskState.class, TaskOpen.class, TaskInProgress.class, TaskFinished.class, Alarm.class}, version = 6,autoMigrations = {@AutoMigration(from = 5, to = 6)} )
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase databaseInstance;
    public static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract TaskDao taskDao();
    public abstract AlarmDao alarmDao();

    public static AppDatabase getInstance(final Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database").allowMainThreadQueries()
                            .build();
        }
        return databaseInstance;
    }
}
