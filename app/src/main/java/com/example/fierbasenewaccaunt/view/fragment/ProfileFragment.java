package com.example.fierbasenewaccaunt.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.fierbasenewaccaunt.R;
import com.example.fierbasenewaccaunt.databinding.FragmentProfileBinding;
import com.example.fierbasenewaccaunt.model.User;
import com.example.fierbasenewaccaunt.util.SharedPreferencesManager;


public class ProfileFragment extends BaseFragment {
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.signOutButton.setOnClickListener(v -> {
            getUserViewModel().signOutUser();
            getNavController().navigate(R.id.action_profileFragment_to_signInFragment);

        });
//        getUserViewModel().findUser(SharedPreferencesManager.CURRENT_USER_ID_KEY);
//
//        getUserViewModel().getUserLiveData().observe(getViewLifecycleOwner(), resource -> {
//            switch (resource.status) {
//                case ERROR:
//                    getProgressDialog().dismiss();
//                    Toast.makeText(getContext(), resource.getMessage(getContext()), Toast.LENGTH_SHORT).show();
//                    break;
//                case LOADING:
//                    getProgressDialog().setMessage(resource.getMessage(getContext()));
//                    getProgressDialog().show();
//                    break;
//                case SUCCESS:
//                    getProgressDialog().dismiss();
//                    updateUI(resource.data);
//                    break;
//            }
//        });
    }

    private void updateUI(User user) {
        Log.i("dev", "updateUI " + user.toString());
        Glide.with(requireActivity()).load(user.getImgUri()).into(binding.imgViewProfile);
        binding.userNameTextView.setText(user.getName());
    }


}