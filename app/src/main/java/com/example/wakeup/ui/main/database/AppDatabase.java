package com.example.wakeup.ui.main.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.wakeup.ui.main.database.dao.TaskDao;
import com.example.wakeup.ui.main.models.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiresApi(api = Build.VERSION_CODES.O)
@Database(entities = {Task.class}, version = 2,autoMigrations = {@AutoMigration(from = 1, to = 2)} )
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase databaseInstance;
    public static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public abstract TaskDao taskDao();

    public static AppDatabase getInstance(final Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .build();
        }
        return databaseInstance;
    }
}
