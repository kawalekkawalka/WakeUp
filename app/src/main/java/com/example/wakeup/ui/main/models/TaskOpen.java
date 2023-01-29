package com.example.wakeup.ui.main.models;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.room.Entity;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.database.viewmodels.TaskViewModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

@Entity
public class TaskOpen extends TaskState{

    public TaskOpen(){
        super();
    }

    public TaskOpen(Task task){
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
        dialogTitle.setText(R.string.edit_task);
        Button addTaskBtn = dialog.findViewById(R.id.btn_add_task);
        addTaskBtn.setText(R.string.edit);
        title.setText(task.getTitle());
        details.setText(task.getDetails());
        dueDateText.setText(task.getDueDate().toString());
        hasReminder.setChecked(task.getHasReminder());
        dueHourText.setText(task.getDueTime().toString());
        DatePickerDialog.OnDateSetListener date = (view1, year, month, day) -> {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            dueDateText.setText(LocalDate.of(year,month+1,day).toString());
            task.setDueDate(LocalDate.of(year,month+1,day));
        };
        dueHourText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        dueHourText.setText(String.format("%02d:%02d", hourOfDay, minutes));
                        task.setDueTime(LocalTime.parse(String.format("%02d:%02d", hourOfDay, minutes)));
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
                        .show();
            }
        });

        dueDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setTitle(title.getText().toString());
                task.setDetails(details.getText().toString());
                task.setHasReminder(hasReminder.isChecked());
                task.setState(new TaskOpen(task));
                taskViewModel.update(task);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
