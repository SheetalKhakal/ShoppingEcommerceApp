package com.sheetal.shoppingecommerceapp.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
    private static String BASE_URL= "https://fakestoreapi.com/";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;
    private OkHttpClient.Builder builder= new OkHttpClient.Builder();
    private HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();

    private RetrofitClient()
    {

        //(We can see response and request in Logcat)
        Gson gson = new GsonBuilder().setLenient().create();

        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())        //(We can see response and request in Logcat)
                .build();
    }
    public static synchronized RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public ServiceApi getApi()
    {
        ServiceApi api=retrofit.create(ServiceApi.class);
        return api;
    }
}
