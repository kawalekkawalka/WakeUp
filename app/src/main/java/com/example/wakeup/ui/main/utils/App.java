package com.example.wakeup.ui.main.utils;

import android.app.Application;
import android.content.res.Resources;

public class App extends Application {
    private static App mInstance;
    private static Resources res;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        res = getResources();
    }

    public static App getInstance() {
        return mInstance;
    }

    public static Resources getRes() {
        return res;
    }

}