package com.sheetal.shoppingecommerceapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
 public class LoginRepo
{
    MutableLiveData<Boolean> loginSuccess= new MutableLiveData<>();
    SharedPrefManager sharedPrefManager;
    public String username,password;
    public LiveData<Boolean> getLoginSuccess()
    {
        if (loginSuccess == null){
            loginSuccess= new MutableLiveData<>();
            setLoginUser(username, password);
        }
        return loginSuccess;
    }

    public void setLoginUser(String username, String password)
    {  // Perform login logic here
        boolean isSuccess= "mor_2314".equals(username) && "83r5^_".equals(password);
        loginSuccess.setValue(isSuccess);

    }
}
