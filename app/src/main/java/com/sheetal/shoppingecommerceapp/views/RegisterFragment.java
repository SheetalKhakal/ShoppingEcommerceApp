package com.sheetal.shoppingecommerceapp.views;

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
import com.sheetal.shoppingecommerceapp.databinding.FragmentRegisterBinding;
import com.sheetal.shoppingecommerceapp.viewmodels.ShopViewModel;


public class RegisterFragment extends Fragment
{
    private static final String TAG = "RegisterFragment";
    public ShopViewModel shopViewModel;
    FragmentRegisterBinding fragmentRegisterBinding;
    public NavController navController;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_register, container, false);
        fragmentRegisterBinding=FragmentRegisterBinding.inflate(inflater,container,false);
        return fragmentRegisterBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopViewModel= new ViewModelProvider(this).get(ShopViewModel.class);

        // Set up UI components for registration (EditText, Button, etc.)
        EditText userNameEt = view.findViewById(R.id.etUname);
        EditText userEmailEt= view.findViewById(R.id.etEmail);
        EditText passwordEt= view.findViewById(R.id.etPassword);
        Button registerBtn= view.findViewById(R.id.btnRegister);
        TextView loginLink=view.findViewById(R.id.loginLink);

        // Handle the registration button click
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String username=userNameEt.getText().toString();
//                String uEmail= userEmailEt.getText().toString();
//                String password=passwordEt.getText().toString();
//                shopViewModel.registerUser(username,uEmail,password);
//                Log.d(TAG,"Username= " + username +"and Password= " + password + "email=" + uEmail);

                Toast.makeText(getActivity(), "Due to API endpoints please refer Login credentials from Readme.md file", Toast.LENGTH_SHORT).show();
            }
        });
        // Observe registration success state
        shopViewModel.getRegistrationSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess)
            {
                if (isSuccess){
                    // Navigate to the login screen or another appropriate screen upon successful registration
                    // Example using Navigation Component:
                    Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
                    Toast.makeText(getActivity(), "Due to API endpoints please refer Login credentials from Readme.md file", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "Wrong credentials.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController=Navigation.findNavController(getActivity(),R.id.nav_host_login_fragment);
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });
    }
}