package com.sheetal.shoppingecommerceapp.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.sheetal.shoppingecommerceapp.models.User;

public class SharedPrefManager
{
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Context context;

    public SharedPrefManager(Context context) {
        this.context = context;
    }
    public void saveUserId(User user)
    {
        sharedPreferences = context.getSharedPreferences("Shared code",Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
        editor.putInt("userId", user.getUserId());
        editor.apply();
    }
    public int getUserId()
    {
        sharedPreferences = context.getSharedPreferences("Shared code",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("userId",1);
    }
    @NonNull
    public boolean saveUsername(String UserName,Context context)
    {
        sharedPreferences=context.getSharedPreferences("Shared code",Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
        editor.putString("username",UserName);
        editor.apply();
        return true;
    }
    public String getUsername()
    {
        sharedPreferences = context.getSharedPreferences("Shared code",Context.MODE_PRIVATE);
        return sharedPreferences.getString("username",null);
    }
    public void logoutUser()
    {
        sharedPreferences=context.getSharedPreferences("Shared code",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
