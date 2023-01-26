package com.example.wakeup.ui.main.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.wakeup.ui.main.database.AppDatabase;
import com.example.wakeup.ui.main.database.dao.TaskDao;
import com.example.wakeup.ui.main.models.Task;

import java.util.Date;
import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;
    private LiveData<List<Task>> tasksForDate;

    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAll();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.insert(task));
    }

    public void update(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.update(task));
    }

    public void delete(Task task) {
        AppDatabase.databaseWriteExecutor.execute(() -> taskDao.delete(task));
    }

    public LiveData<List<Task>> getTaskForDate(Date date) {
        tasksForDate = taskDao.getTasksForDate(date);
        return tasksForDate;
    }
}
