package com.example.wakeup.ui.main.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.wakeup.ui.main.utils.DateConverter;

import java.util.Date;

@Entity(tableName = "task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String details;

    @TypeConverters({DateConverter.class})
    private Date dueDate;

    @Ignore
    private TaskState state;

    private Boolean hasReminder;

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", dueDate=" + dueDate +
                ", state=" + state +
                ", hasReminder=" + hasReminder +
                '}';
    }

    public Task(int id, String title, String details, Date dueDate, Boolean hasReminder) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.dueDate = dueDate;
        this.hasReminder = hasReminder;
    }

    public Task() {

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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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
