package com.example.fierbasenewaccaunt.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.fierbasenewaccaunt.R;
import com.example.fierbasenewaccaunt.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        if (userViewModel.getCurrentUserId().getValue() != null) {
            adjustBackStack();
        }
    }

    private void adjustBackStack() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        NavGraph navGraph = navController.getGraph();
        navGraph.setStartDestination(R.id.profileFragment);
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.signInFragment, true)
                .build();
        if (!(navController.getCurrentDestination().getId() == R.id.profileFragment)) {
            navController.navigate(R.id.action_signInFragment_to_profileFragment, null, navOptions);
        }
    }
}