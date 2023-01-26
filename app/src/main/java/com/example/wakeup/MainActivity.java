package com.example.wakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wakeup.ui.main.MainFragment;
import com.example.wakeup.ui.main.activities.ExtendedWeatherActivity;
import com.example.wakeup.ui.main.activities.TaskListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    public void onButtonClick(View view) {
        // open the new activity when the button is clicked
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);
    }

}