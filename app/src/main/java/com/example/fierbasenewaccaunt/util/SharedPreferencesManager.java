package com.example.fierbasenewaccaunt.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesManager {
    private static final String PACKAGE_NAME = "com.example.lesson8";
    private static final String PREF_KEY = PACKAGE_NAME + ".appSetting";


    public static final String CURRENT_USER_ID_KEY = PACKAGE_NAME + "currentUserId";


    private final SharedPreferences sharedPreferences;
    private static SharedPreferencesManager instance;
    private final SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    private SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context.getApplicationContext());
        }
    }

    public static SharedPreferencesManager getInstance() {
        return instance;
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void putCurrentUserId(String userId) {
        editor.putString(CURRENT_USER_ID_KEY, userId).apply();
    }

    public String getCurrentUserId() {
        return sharedPreferences.getString(CURRENT_USER_ID_KEY, null);
    }

}
