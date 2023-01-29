package com.example.wakeup.ui.main.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.wakeup.ui.main.database.viewmodels.TaskViewModel;

import java.util.Calendar;

@Entity
public abstract class TaskState {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @Ignore
    private Task task;

    protected TaskState(Task task){
        this.task=task;
    }

    protected TaskState(){
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public abstract void edit(TaskViewModel taskViewModel, Calendar calendar, Context context,Task task);
}
