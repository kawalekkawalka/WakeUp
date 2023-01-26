package com.example.wakeup.ui.main.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wakeup.R;
import com.example.wakeup.ui.main.fragments.TaskListFragment;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void createFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TaskListFragment taskListFragment = new TaskListFragment();
        fragmentTransaction.add(R.id.fragment_container, taskListFragment);
        fragmentTransaction.commit();
    }

}
