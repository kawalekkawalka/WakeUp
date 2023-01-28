package com.example.wakeup.ui.main.database.viewmodels;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wakeup.ui.main.database.repositories.TaskRepository;
import com.example.wakeup.ui.main.models.Task;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private final TaskRepository taskRepository;
    private final LiveData<List<Task>> tasks;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        tasks = taskRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return tasks;
    }

    public LiveData<List<Task>> getTaskForDate(LocalDate date) {
        return taskRepository.getTaskForDate(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insert(Task task) {
        taskRepository.insert(task);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void update(Task task) {
        taskRepository.update(task);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
