package com.example.wakeup;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.wakeup.ui.main.fragments.AlarmFragment;
import com.example.wakeup.ui.main.fragments.TaskListFragment;
import com.example.wakeup.databinding.ActivityMainBinding;
import com.example.wakeup.ui.main.fragments.NewsFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //replaceFragment(new NewsFragment());

        NotificationChannel channel = new NotificationChannel(getString(R.string.channelName), "TaskNotification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.home:
                    //replaceFragment(new NewsFragment());
                    break;
                case R.id.calendar:
                    replaceFragment(new TaskListFragment());
                    break;
                case R.id.alarm:
                    replaceFragment(new AlarmFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}