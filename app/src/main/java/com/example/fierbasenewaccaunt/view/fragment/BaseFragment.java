package com.example.fierbasenewaccaunt.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fierbasenewaccaunt.viewmodel.UserViewModel;

public abstract class BaseFragment extends Fragment {
    private UserViewModel userViewModel;
    private NavController navController;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        progressDialog = new ProgressDialog(getContext());
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public NavController getNavController() {
        return navController;
    }
}
