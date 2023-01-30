package com.example.wakeup.ui.main.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.wakeup.ui.main.models.Task;
import com.example.wakeup.ui.main.database.viewmodels.utils.LocalDateConverter;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM task ORDER BY id")
    LiveData<List<Task>> getAll();

    @Query("SELECT * FROM task WHERE id = :taskId")
    LiveData<Task> getTaskById(int taskId);

    @TypeConverters(LocalDateConverter.class)
    @Query("SELECT * FROM task WHERE dueDate = :date")
    LiveData<List<Task>> getTasksForDate(LocalDate date);
}
