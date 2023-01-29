package com.example.wakeup.ui.main.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.alarms.AlarmReceiver;
import com.example.wakeup.ui.main.database.viewmodels.AlarmViewModel;
import com.example.wakeup.ui.main.models.Alarm;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment {
    private RecyclerView recyclerView;
    private AlarmAdapter adapter;
    private AlarmViewModel alarmViewModel;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_list, container, false);

        recyclerView = view.findViewById(R.id.alarm_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new AlarmAdapter();
        recyclerView.setAdapter(adapter);

        alarmViewModel = new ViewModelProvider(this).get(AlarmViewModel.class);
        alarmViewModel.getAllAlarms().observe(getViewLifecycleOwner(), alarms -> adapter.setAlarms(alarms));

        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        return view;
    }

    private class AlarmHolder extends RecyclerView.ViewHolder {
        private TextView dueDateTextView;
        private TextView dueTimeTextView;
        private SwitchCompat activeSwitch;
        private LocalTime alarmTime;

        public AlarmHolder(View itemView) {
            super(itemView);
            dueDateTextView = itemView.findViewById(R.id.alarm_due_date);
            dueTimeTextView = itemView.findViewById(R.id.alarm_due_time);
            activeSwitch = itemView.findViewById(R.id.alarm_active_switch);

            activeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(getContext(), "ALARM ON", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext().getApplicationContext(), AlarmReceiver.class);
                        alarmIntent = PendingIntent.getBroadcast(getContext().getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getLong(ChronoField.MILLI_OF_SECOND), 60000, alarmIntent);
                    } else {
                        if (alarmManager != null) {
                            alarmManager.cancel(alarmIntent);
                        }
                        Toast.makeText(getContext(), "ALARM OFF", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        public void bind(Alarm alarm) {
            dueDateTextView.setText(alarm.getDueDate().toString());
            dueTimeTextView.setText(alarm.getDueTime().toString());
            activeSwitch.setChecked(alarm.isActive());
            alarmTime = alarm.getDueTime();
        }
    }

    private class AlarmAdapter extends RecyclerView.Adapter<AlarmHolder> {
        private List<Alarm> alarms = new ArrayList<>();

        public void setAlarms(List<Alarm> alarms) {
            this.alarms = alarms;
            notifyDataSetChanged();
        }

        @Override
        public AlarmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_alarm, parent, false);
            return new AlarmHolder(view);
        }

        @Override
        public void onBindViewHolder(AlarmHolder holder, int position) {
            Alarm alarm = alarms.get(position);
            holder.bind(alarm);
        }

        @Override
        public int getItemCount() {
            return alarms.size();
        }
    }
}

