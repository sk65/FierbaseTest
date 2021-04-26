package com.example.fierbasenewaccaunt.viewmodel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fierbasenewaccaunt.model.User;
import com.example.fierbasenewaccaunt.repository.UserRepository;
import com.example.fierbasenewaccaunt.util.Resource;
import com.example.fierbasenewaccaunt.util.SharedPreferencesManager;
import com.google.firebase.auth.FirebaseUser;

import static com.example.fierbasenewaccaunt.util.SharedPreferencesManager.CURRENT_USER_ID_KEY;

public class UserViewModel extends ViewModel implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final UserRepository repository;
    private final SharedPreferencesManager sharedPreferencesManager;
    private final MutableLiveData<String> currentUserId;

    public UserViewModel() {
        this.sharedPreferencesManager = SharedPreferencesManager.getInstance();
        this.sharedPreferencesManager.registerOnSharedPreferenceChangeListener(this);
        this.currentUserId = new MutableLiveData<>(sharedPreferencesManager.getCurrentUserId());
        this.repository = new UserRepository();
    }

    public void findUser(String userId) {
        repository.findUser(userId);
    }

    public void signUp(User user, String password) {
        repository.signUp(user, password);
    }

    public void signIn(String email, String password) {
        repository.signIn(email, password);
    }

    public void signOutUser() {
        repository.signOutUser();
    }

    public LiveData<Resource<User>> getUserLiveData() {
        return repository.getUserLiveData();
    }

    public LiveData<Resource<FirebaseUser>> getFirebaseUserLiveData() {
        return repository.getFirebaseUserLiveData();
    }

    public LiveData<String> getCurrentUserId() {
        return currentUserId;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (CURRENT_USER_ID_KEY.equals(key)) {
            currentUserId.setValue(sharedPreferencesManager.getCurrentUserId());
        }
    }

    public void signInWithCustomToken(String userId) {
        repository.signInWithCustomToken( userId);
    }
}