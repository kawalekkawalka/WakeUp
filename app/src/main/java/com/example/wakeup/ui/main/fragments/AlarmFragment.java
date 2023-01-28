package com.example.wakeup.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.alarms.AlarmAdapter;
import com.example.wakeup.ui.main.database.viewmodels.AlarmViewModel;

public class AlarmFragment extends Fragment {
    private RecyclerView recyclerView;
    private AlarmAdapter adapter;
    private AlarmViewModel alarmViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_list, container, false);

        recyclerView = view.findViewById(R.id.alarm_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new AlarmAdapter();
        recyclerView.setAdapter(adapter);

        alarmViewModel =  new ViewModelProvider(this).get(AlarmViewModel.class);
        alarmViewModel.getAllAlarms().observe(getViewLifecycleOwner(), alarms -> adapter.setAlarms(alarms));

        return view;
    }
}

