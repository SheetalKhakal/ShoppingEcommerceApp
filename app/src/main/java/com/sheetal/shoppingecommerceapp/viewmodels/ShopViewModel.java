package com.sheetal.shoppingecommerceapp.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sheetal.shoppingecommerceapp.models.CartItem;
import com.sheetal.shoppingecommerceapp.models.Product;
import com.sheetal.shoppingecommerceapp.models.User;
import com.sheetal.shoppingecommerceapp.repositories.CartRepo;
import com.sheetal.shoppingecommerceapp.repositories.LoginRepo;
import com.sheetal.shoppingecommerceapp.repositories.RegisterRepo;
import com.sheetal.shoppingecommerceapp.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel
{
    ShopRepo shopRepo=new ShopRepo();
    CartRepo cartRepo= new CartRepo();
    LoginRepo loginRepo= new LoginRepo();
    RegisterRepo registerRepo = new RegisterRepo();
    Boolean isSuccess;
    MutableLiveData<Product> mutableProduct= new MutableLiveData<>();

    MutableLiveData<Boolean> loginSuccess= new MutableLiveData<>();

    private MutableLiveData<Boolean> registrationSuccess = new MutableLiveData<>();

    public LiveData<Boolean> getRegistrationSuccess() { return registerRepo.getRegistrationSuccess(); }
    public void registerUser(String username,String email,String password) { registerRepo.registerUser(username,email,password);}
    public LiveData<Boolean> getLoginSuccess(){ return loginRepo.getLoginSuccess();}

    public void setLoginUser(String username,String password) {   loginRepo.setLoginUser(username,password);}
     public LiveData<List<Product>> getProducts()
    {
        return shopRepo.getProducts();
    }

    public void setProduct(Product product) {mutableProduct.setValue(product);}

    public LiveData<Product> getProduct() {return mutableProduct;}

    public LiveData<List<CartItem>> getCart() {return cartRepo.getCart();}

    public boolean addItemToCart(Product product)
    {
        return cartRepo.addItemToCart(product);
    }

    public void removeItemFromCart(CartItem cartItem)
    {
        cartRepo.removeItemFromCart(cartItem);
    }

    public void changeQuantity(CartItem cartItem,int quantity){
        cartRepo.changeQuantity(cartItem,quantity);
    }

    public LiveData<Double> getTotalPrice()
    {
        return cartRepo.getTotalPrice();
    }

    public void resetCart()
    {
        cartRepo.initCart();
    }
}
