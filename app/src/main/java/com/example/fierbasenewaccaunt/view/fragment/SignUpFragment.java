package com.example.fierbasenewaccaunt.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fierbasenewaccaunt.R;
import com.example.fierbasenewaccaunt.databinding.FragmentSignUpBinding;
import com.example.fierbasenewaccaunt.model.User;


public class SignUpFragment extends BaseFragment {
    private FragmentSignUpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.signUpButton.setOnClickListener(v -> {

            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();
            String name = binding.etName.getText().toString().trim();
            if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                Toast.makeText(getContext(), "You should fill the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = new User(email, name, null);
            getUserViewModel().signUp(user, password);

        });

        getUserViewModel().getFirebaseUserLiveData().observe(getViewLifecycleOwner(), resource -> {
            switch (resource.status) {
                case ERROR:
                    getProgressDialog().dismiss();
                    Toast.makeText(getContext(), resource.getMessage(getContext()), Toast.LENGTH_SHORT).show();
                    break;
                case LOADING:
                    getProgressDialog().setMessage(resource.getMessage(getContext()));
                    getProgressDialog().show();
                    break;
                case SUCCESS:
                    getProgressDialog().dismiss();
                    getNavController().navigate(R.id.action_signUpFragment_to_profileFragment);
                    break;
            }
        });
    }


}