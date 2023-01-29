package com.example.wakeup.ui.main.database.viewmodels.utils.command;

import com.example.wakeup.ui.main.database.viewmodels.TaskViewModel;
import com.example.wakeup.ui.main.models.Task;

public abstract class Command {
    protected Task task;

    protected Command(Task task){
        this.task=task;
    }

    public abstract void undo(TaskViewModel taskViewModel);

}
