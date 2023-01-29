package com.example.wakeup.ui.main.database.viewmodels.utils.command;

import com.example.wakeup.ui.main.database.viewmodels.TaskViewModel;
import com.example.wakeup.ui.main.models.Task;

public class AddCommand extends Command{
    public AddCommand(Task task){
        super(task);
    }

    @Override
    public void undo(TaskViewModel taskViewModel) {
        taskViewModel.delete(task);
    }
}
