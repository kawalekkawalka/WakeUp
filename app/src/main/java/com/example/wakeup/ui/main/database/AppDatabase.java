package com.example.wakeup.ui.main.database;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RenameColumn;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;

import com.example.wakeup.ui.main.database.dao.LocationDao;
import com.example.wakeup.ui.main.database.dao.TaskDao;
import com.example.wakeup.ui.main.models.UserLocation;
import com.example.wakeup.ui.main.models.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class, UserLocation.class}, version = 5)

public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase databaseInstance;
    public static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract TaskDao taskDao();

    public abstract LocationDao locationDao();

    public static AppDatabase getInstance(final Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database").fallbackToDestructiveMigration()
                            .build();
        }
        return databaseInstance;
    }
}
