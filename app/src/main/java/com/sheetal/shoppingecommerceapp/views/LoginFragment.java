package com.sheetal.shoppingecommerceapp.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sheetal.shoppingecommerceapp.R;
import com.sheetal.shoppingecommerceapp.databinding.FragmentLoginBinding;
import com.sheetal.shoppingecommerceapp.repositories.LoginRepo;
import com.sheetal.shoppingecommerceapp.repositories.SharedPrefManager;
import com.sheetal.shoppingecommerceapp.viewmodels.ShopViewModel;


public class LoginFragment extends Fragment
{
    private static final String TAG = "LoginFragment";
    SharedPrefManager sharedPrefManager;
    FragmentLoginBinding fragmentLoginBinding;
    private ShopViewModel shopViewModel;
    public NavController navController;


    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false);
        fragmentLoginBinding= FragmentLoginBinding.inflate(inflater,container,false);
        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize ViewModel
        shopViewModel= new ViewModelProvider(this).get(ShopViewModel.class);

        // Set up UI components (EditText, Button, etc.)
        EditText usernameEdit= view.findViewById(R.id.etUsername);
        EditText passwordEdit= view.findViewById(R.id.etPassword);
        Button loginButton= view.findViewById(R.id.btnLogin);
        TextView registerLink=view.findViewById(R.id.registerLink);

        // Handle the login button click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String username= usernameEdit.getText().toString();
                String password= passwordEdit.getText().toString();

                if (username.isEmpty()){
                    usernameEdit.requestFocus();
                    usernameEdit.setError("Please enter user name.");
                    return;
                }
                if (password.isEmpty()){
                    passwordEdit.requestFocus();
                    passwordEdit.setError("Please enter password.");
                }
                if (password.length()< 4){
                    passwordEdit.requestFocus();
                    passwordEdit.setError("Enter 4 digit password.");
                }

                 shopViewModel.setLoginUser(username,password);
                Log.d(TAG,"Username= " + username +"and Password= " + password);
             }
        });

        // Observe login success state
        shopViewModel.getLoginSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess) {
                if (isSuccess){
                    // Navigate to the next screen upon successful login
                    // You can use Navigation Component or Intent to navigate.

                    Toast.makeText(getContext(), "Login successfully.", Toast.LENGTH_SHORT).show();
                    navController= Navigation.findNavController(requireActivity(),R.id.nav_host_login_fragment);
                    navController.navigate(R.id.action_loginFragment_to_mainActivity);
                    Log.d(TAG,"Login success.");
                }else {
                    Toast.makeText(getContext(), "You have entered wrong details.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"Login unsuccessful" );
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController= Navigation.findNavController(requireActivity(),R.id.nav_host_login_fragment);
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });



    }
}