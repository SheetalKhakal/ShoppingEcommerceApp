package com.sheetal.shoppingecommerceapp.views;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sheetal.shoppingecommerceapp.R;
import com.sheetal.shoppingecommerceapp.repositories.SharedPrefManager;


public class WelcomeFragment extends Fragment
{
     private NavController navController;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_welcome, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              navController =Navigation.findNavController(requireActivity(),R.id.nav_host_login_fragment);
              navController.navigate(R.id.action_welcomeFragment_to_loginFragment);

            }
        }, 3000);

       return view;

    }
}