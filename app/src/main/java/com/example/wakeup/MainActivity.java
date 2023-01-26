package com.example.wakeup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wakeup.ui.main.activities.ExtendedWeatherActivity;
import com.example.wakeup.ui.main.fragments.NewsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new NewsFragment()).commit();
        }

    }

    public void onButtonClick(View view) {
        // open the new activity when the button is clicked
        Intent intent = new Intent(this, ExtendedWeatherActivity.class);
        startActivity(intent);
    }

}