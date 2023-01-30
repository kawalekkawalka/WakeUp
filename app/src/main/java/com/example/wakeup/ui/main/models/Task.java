package com.example.wakeup.ui.main.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.wakeup.ui.main.database.viewmodels.utils.LocalDateConverter;
import com.example.wakeup.ui.main.database.viewmodels.utils.LocalTimeConverter;
import com.example.wakeup.ui.main.database.viewmodels.utils.StateConverter;

import java.time.LocalDate;
import java.time.LocalTime;


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

    @TypeConverters({StateConverter.class})
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
    @Ignore
    public Task() {

    }

    @Ignore
    public Task(Task task){
        this.id = task.getId();
        this.title = task.getTitle();
        this.details = task.getDetails();
        this.state = task.getState();
        this.dueDate = task.getDueDate();
        this.dueTime = task.getDueTime();
        this.hasReminder = task.getHasReminder();
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
}
