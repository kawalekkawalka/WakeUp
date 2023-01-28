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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiresApi(api = Build.VERSION_CODES.O)
@Database(entities = {Task.class, Alarm.class}, version = 2,autoMigrations = {@AutoMigration(from = 1, to = 2)} )
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase databaseInstance;
    public static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract TaskDao taskDao();
    public abstract AlarmDao alarmDao();

    public static AppDatabase getInstance(final Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
        }
        return databaseInstance;
    }
}
