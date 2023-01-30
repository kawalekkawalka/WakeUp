package com.example.wakeup.ui.main.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.wakeup.ui.main.utils.LocalDateConverter;
import com.example.wakeup.ui.main.utils.LocalTimeConverter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(tableName = "alarm", foreignKeys = {@ForeignKey(entity = Task.class, parentColumns = "id", childColumns = "taskId", onDelete = ForeignKey.CASCADE)} )
public class Alarm {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @TypeConverters({LocalDateConverter.class})
    private LocalDate dueDate;

    @TypeConverters({LocalTimeConverter.class})
    private LocalTime dueTime;
    private boolean isActive;

    @Ignore
    private Task asignedTask;

    private int taskId;

    public Task getAsignedTask() {
        return asignedTask;
    }

    public void setAsignedTask(Task asignedTask) {
        this.asignedTask = asignedTask;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalTime dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
