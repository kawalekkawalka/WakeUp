package com.example.wakeup.ui.main.utils.command;

import com.example.wakeup.ui.main.database.viewmodels.TaskViewModel;
import com.example.wakeup.ui.main.models.Task;

public class EditCommand extends Command{
    public EditCommand(Task task){
        super(task);
    }

    @Override
    public void undo(TaskViewModel taskViewModel) {
        taskViewModel.update(task);
    }

}
