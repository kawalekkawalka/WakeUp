package com.example.wakeup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeup.ui.main.fragments.TaskListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,
                            new TaskListFragment()).commit();
        }
    }


}