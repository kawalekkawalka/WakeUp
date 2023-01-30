package com.example.wakeup.ui.main.database;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.AutoMigration;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RenameColumn;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

import com.example.wakeup.ui.main.database.dao.AlarmDao;
import com.example.wakeup.ui.main.database.dao.LocationDao;
import com.example.wakeup.ui.main.database.dao.TaskDao;
import com.example.wakeup.ui.main.models.Alarm;
import com.example.wakeup.ui.main.models.UserLocation;
import com.example.wakeup.ui.main.models.Task;
import com.example.wakeup.ui.main.models.TaskFinished;
import com.example.wakeup.ui.main.models.TaskInProgress;
import com.example.wakeup.ui.main.models.TaskOpen;
import com.example.wakeup.ui.main.models.TaskState;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Alarm.class, Task.class, TaskState.class, TaskOpen.class, TaskInProgress.class, TaskFinished.class, UserLocation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase databaseInstance;
    public static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract TaskDao taskDao();

    public abstract AlarmDao alarmDao();

    public abstract LocationDao locationDao();

    public static AppDatabase getInstance(final Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return databaseInstance;
    }
}
