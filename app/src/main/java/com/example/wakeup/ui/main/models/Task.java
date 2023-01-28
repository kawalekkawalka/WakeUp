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
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
@Entity(tableName = "task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String details;

    @TypeConverters({LocalDateConverter.class})
    private LocalDate dueDate;

    @TypeConverters({LocalTimeConverter.class})
    private LocalTime dueTime;

    @Ignore
    private TaskState state;

    private Boolean hasReminder;


    public Task(int id, String title, String details, LocalDate dueDate,LocalTime dueTime, Boolean hasReminder) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.hasReminder = hasReminder;
    }

    public Task() {

    }


    public LocalTime getDueTime() {
        return dueTime;
    }

    public void setDueTime(LocalTime dueTime) {
        this.dueTime = dueTime;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public Boolean getHasReminder() {
        return hasReminder;
    }

    public void setHasReminder(Boolean hasReminder) {
        this.hasReminder = hasReminder;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", dueDate=" + dueDate +
                ", dueTime=" + dueTime +
                ", state=" + state +
                ", hasReminder=" + hasReminder +
                '}';
    }
}
