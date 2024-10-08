package com.sheetal.shoppingecommerceapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sheetal.shoppingecommerceapp.R;
import com.sheetal.shoppingecommerceapp.databinding.FragmentOrderBinding;
import com.sheetal.shoppingecommerceapp.viewmodels.ShopViewModel;

public class OrderFragment extends Fragment
{
    NavController navController;
    ShopViewModel shopViewModel;
    FragmentOrderBinding fragmentOrderBinding;

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     //   return inflater.inflate(R.layout.fragment_order, container, false);
       fragmentOrderBinding = FragmentOrderBinding.inflate(inflater,container,false);
       return fragmentOrderBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        navController= Navigation.findNavController(view);

        fragmentOrderBinding.continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopViewModel.resetCart();
                navController.navigate(R.id.action_orderFragment_to_shopFragment);
            }
        });
    }
}