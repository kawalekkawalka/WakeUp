package com.example.wakeup.ui.main.models;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.room.Entity;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.database.viewmodels.TaskViewModel;

import java.util.Calendar;

@Entity
public class TaskInProgress extends TaskState {

    public TaskInProgress(){
        super();
    }

    public TaskInProgress(Task task){
        super(task);
    }

    @Override
    public void edit(TaskViewModel taskViewModel, Calendar calendar, Context context,Task task) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_task);
        final EditText title = dialog.findViewById(R.id.new_task_title);
        final EditText details = dialog.findViewById(R.id.new_task_details);
        final TextView dueDateText = dialog.findViewById(R.id.due_date_text);
        final CheckBox hasReminder = dialog.findViewById(R.id.has_reminder);
        final TextView dueHourText = dialog.findViewById(R.id.due_hour_text);
        final TextView dialogTitle = dialog.findViewById(R.id.dialog_title);
        final TextView titleLabel = dialog.findViewById(R.id.title_label);
        final TextView dueHourLabel = dialog.findViewById(R.id.due_hour_label);
        final TextView dueDateLabel = dialog.findViewById(R.id.due_date_label);
        dueHourLabel.setVisibility(View.GONE);
        dueDateLabel.setVisibility(View.GONE);
        titleLabel.setVisibility(View.GONE);
        dialogTitle.setText(R.string.edit_task);
        Button addTaskBtn = dialog.findViewById(R.id.btn_add_task);
        addTaskBtn.setText(R.string.edit);
        title.setVisibility(View.GONE);
        details.setText(task.getDetails());
        dueDateText.setVisibility(View.GONE);
        hasReminder.setVisibility(View.GONE);
        dueHourText.setVisibility(View.GONE);

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setDetails(details.getText().toString());
                task.setState(new TaskOpen(task));
                taskViewModel.update(task);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
