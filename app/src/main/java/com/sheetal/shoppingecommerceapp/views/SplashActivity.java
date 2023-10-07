package com.sheetal.shoppingecommerceapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sheetal.shoppingecommerceapp.R;
import com.sheetal.shoppingecommerceapp.repositories.SharedPrefManager;

public class SplashActivity extends AppCompatActivity
{
    private static final String TAG = "SplashActivity";
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    navController= Navigation.findNavController(this,R.id.nav_host_login_fragment);
   // NavigationUI.setupActionBarWithNavController(this,navController);

    }


}