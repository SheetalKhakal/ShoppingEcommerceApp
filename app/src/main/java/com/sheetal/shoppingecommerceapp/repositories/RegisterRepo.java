package com.sheetal.shoppingecommerceapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RegisterRepo
{
    private MutableLiveData<Boolean> registrationSuccess = new MutableLiveData<>();
    public String uname,password, uemail;
    public LiveData<Boolean> getRegistrationSuccess()
    {
        if (registrationSuccess == null){
            registrationSuccess=new MutableLiveData<>();
            registerUser(uname,uemail,password);
        }
        return registrationSuccess;
    }

    public void registerUser(String username, String email,String password)
    {
        // Implement user registration logic here
        // Check if the registration was successful, and set registrationSuccess accordingly.
        boolean isSuccess = "mor_2314".equals(username) && "morrison@gmail.com".equals(email) && "83r5^_".equals(password);
        registrationSuccess.setValue(isSuccess);
    }
}
