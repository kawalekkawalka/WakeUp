package com.example.wakeup.ui.main.models;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Entity;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.database.viewmodels.TaskViewModel;

import java.util.Calendar;

@Entity
public class TaskFinished extends TaskState {

    public TaskFinished(){
        super();
    }
    public TaskFinished(Task task){
        super(task);
    }

    @Override
    public void edit(TaskViewModel taskViewModel, Calendar calendar, Context context,Task task) {
        Toast.makeText(context, R.string.editingFinishedTask, Toast.LENGTH_SHORT).show();
    }
}
