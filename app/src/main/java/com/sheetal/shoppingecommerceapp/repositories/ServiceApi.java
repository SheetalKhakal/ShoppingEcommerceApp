package com.sheetal.shoppingecommerceapp.repositories;

import com.sheetal.shoppingecommerceapp.models.Product;
import com.sheetal.shoppingecommerceapp.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi
{

    @GET("https://fakestoreapi.com/products/")
    Call<List<Product>> getProductsList();
}
