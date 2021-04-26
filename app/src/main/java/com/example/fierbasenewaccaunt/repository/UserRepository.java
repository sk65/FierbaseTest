package com.example.fierbasenewaccaunt.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fierbasenewaccaunt.model.User;
import com.example.fierbasenewaccaunt.util.Resource;
import com.example.fierbasenewaccaunt.util.SharedPreferencesManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserRepository {
    private final FirebaseAuth auth;
    private final DatabaseReference myRef;
    private final MutableLiveData<Resource<FirebaseUser>> firebaseUserLiveData;
    private final MutableLiveData<Resource<User>> userLiveData;

    public UserRepository() {
        this.myRef = FirebaseDatabase.getInstance().getReference("user");
        this.firebaseUserLiveData = new MutableLiveData<>();
        this.userLiveData = new MutableLiveData<>();
        this.auth = FirebaseAuth.getInstance();

    }

    public void signIn(String email, String password) {
        isEmailRegistered(email, isRegistered -> {
            firebaseUserLiveData.setValue(Resource.loading("Loading...", null));
            if (isRegistered) {
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                firebaseUserLiveData.setValue(Resource.success(auth.getCurrentUser()));
                                SharedPreferencesManager.getInstance().putCurrentUserId(auth.getCurrentUser().getUid());
                            } else {
                                firebaseUserLiveData.setValue(Resource.error(task.getException().getMessage(), null));
                            }
                        });
            } else {
                firebaseUserLiveData.setValue(Resource.error("User doesn't exists", null));
            }
        });
    }

    public void findUser(String userId) {
        userLiveData.setValue(Resource.loading("Loading...", null));
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("user").child(userId).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                userLiveData.setValue(Resource.error(task.getException().getLocalizedMessage(), null));
            } else {
                HashMap<String, String> user = (HashMap<String, String>) task.getResult().getValue();
                System.out.println("*****************************************************");
                System.out.println("*****************************************************");
                System.out.println("*****************************************************");
                for (String key : user.keySet()) {
                    System.out.println(key + " " + user.get(key));
                }
                System.out.println("*****************************************************");
                System.out.println("*****************************************************");
                System.out.println("*****************************************************");
                // userLiveData.setValue(Resource.success(user));
            }
        });
    }

    public void signUp(User user, String password) {
        isEmailRegistered(user.getEmail(), isRegistered -> {
            firebaseUserLiveData.setValue(Resource.loading("Loading...", null));
            if (isRegistered) {
                firebaseUserLiveData.setValue(Resource.error("Such email exists", null));
            } else {
                auth.createUserWithEmailAndPassword(user.getEmail(), password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                FirebaseDatabase
                                        .getInstance()
                                        .getReference("user")
                                        .child(auth.getCurrentUser().getUid())
                                        .setValue(user);
                                SharedPreferencesManager.getInstance().putCurrentUserId(auth.getCurrentUser().getUid());
                            } else {
                                firebaseUserLiveData.setValue(Resource.error(task.getException().getLocalizedMessage(), null));
                            }
                        }).addOnFailureListener(e -> firebaseUserLiveData.setValue(Resource.error(e.getLocalizedMessage(), null)));
            }
        });
    }

    public void signOutUser() {
        if (auth.getCurrentUser() != null) {
            auth.signOut();
            SharedPreferencesManager.getInstance().putCurrentUserId(null);
        }
    }

    public LiveData<Resource<User>> getUserLiveData() {
        return userLiveData;
    }

    private void isEmailRegistered(String email, OnEmailCheckListener listener) {
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(task ->
                listener.onResult(!task.getResult().getSignInMethods().isEmpty()));
    }

    public LiveData<Resource<FirebaseUser>> getFirebaseUserLiveData() {
        return firebaseUserLiveData;
    }

    private interface OnEmailCheckListener {
        void onResult(boolean isRegistered);
    }
}
