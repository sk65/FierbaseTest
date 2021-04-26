package com.example.fierbasenewaccaunt;

import android.app.Application;

import com.example.fierbasenewaccaunt.util.SharedPreferencesManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesManager.init(getApplicationContext());
    }
}
