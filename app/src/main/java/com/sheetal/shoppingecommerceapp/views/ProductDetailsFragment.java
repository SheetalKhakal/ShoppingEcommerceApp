package com.sheetal.shoppingecommerceapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sheetal.shoppingecommerceapp.R;
import com.sheetal.shoppingecommerceapp.databinding.FragmentProductDetailsBinding;
import com.sheetal.shoppingecommerceapp.viewmodels.ShopViewModel;

public class ProductDetailsFragment extends Fragment
{
    FragmentProductDetailsBinding fragmentProductDetailsBinding;
    ShopViewModel shopViewModel;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentProductDetailsBinding = FragmentProductDetailsBinding.inflate(inflater,container,false);
        return fragmentProductDetailsBinding.getRoot();
     }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Always intialize view model inside onViewCreated()
        shopViewModel= new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentProductDetailsBinding.setShopViewModel(shopViewModel);
    }
}