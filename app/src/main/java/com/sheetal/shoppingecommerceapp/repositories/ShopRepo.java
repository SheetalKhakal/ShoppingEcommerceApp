package com.sheetal.shoppingecommerceapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sheetal.shoppingecommerceapp.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRepo
{
    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts()
    {
        if (mutableProductList == null){
            mutableProductList=new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts() {
        List<Product> productList= new ArrayList<>();

        Call<List<Product>> call=RetrofitClient.getInstance().getApi().getProductsList();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    productList.addAll(products);
                    mutableProductList.setValue(productList);

                } else {
                    // Handle API error
                    Log.e("TAG", "Response not successful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("TAG", "Error fetching data: " + t.getMessage());

            }
        });

    }
}
