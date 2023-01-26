package com.example.wakeup.ui.main.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String subtitle;

    private Date dueDate;

    private TaskState state;

    private Boolean hasReminder;
}
