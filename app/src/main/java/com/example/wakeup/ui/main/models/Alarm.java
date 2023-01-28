package com.example.wakeup.ui.main.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.wakeup.ui.main.utils.LocalDateConverter;
import com.example.wakeup.ui.main.utils.LocalTimeConverter;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiresApi(api = Build.VERSION_CODES.O)
@Entity(tableName = "alarm")
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
}
