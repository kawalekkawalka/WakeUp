package com.example.wakeup.ui.main.database.repositories;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.wakeup.ui.main.database.AppDatabase;
import com.example.wakeup.ui.main.database.dao.TaskDao;
import com.example.wakeup.ui.main.models.Task;

import java.time.LocalDate;
import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;
    private LiveData<List<Task>> tasksForDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAll();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insert(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.insert(task));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void update(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.update(task));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void delete(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.delete(task));
    }

    public LiveData<List<Task>> getTaskForDate(LocalDate date) {
        tasksForDate = taskDao.getTasksForDate(date);
        return tasksForDate;
    }
}
