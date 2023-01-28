package com.example.wakeup.ui.main.alarms;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.models.Alarm;

public
class AlarmViewHolder extends RecyclerView.ViewHolder {
    private TextView dueDateTextView;
    private TextView dueTimeTextView;
    private TextView taskTitle;
    private SwitchCompat activeSwitch;

    public AlarmViewHolder(View itemView) {
        super(itemView);
        dueDateTextView = itemView.findViewById(R.id.alarm_due_date);
        dueTimeTextView = itemView.findViewById(R.id.alarm_due_time);
        taskTitle = itemView.findViewById(R.id.task_title);
        activeSwitch = itemView.findViewById(R.id.alarm_active_switch);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void bind(Alarm alarm) {
        dueDateTextView.setText(alarm.getDueDate().toString());
        dueTimeTextView.setText(alarm.getDueTime().toString());
        taskTitle.setText(alarm.getAsignedTask().getTitle());
        activeSwitch.setChecked(alarm.isActive());
    }
}