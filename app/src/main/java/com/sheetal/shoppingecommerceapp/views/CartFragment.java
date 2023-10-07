package com.sheetal.shoppingecommerceapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sheetal.shoppingecommerceapp.R;
import com.sheetal.shoppingecommerceapp.adapters.CartListAdapter;
import com.sheetal.shoppingecommerceapp.databinding.FragmentCartBinding;
import com.sheetal.shoppingecommerceapp.models.CartItem;
import com.sheetal.shoppingecommerceapp.viewmodels.ShopViewModel;

import java.util.List;

public class CartFragment extends Fragment implements CartListAdapter.CartInterface
{
    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    NavController navController;
    FragmentCartBinding fragmentCartBinding;
    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding =FragmentCartBinding.inflate(inflater,container,false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize navController
        navController = Navigation.findNavController(view);
    //Intialise CartListAdapter here
        final CartListAdapter cartListAdapter=new CartListAdapter(this);
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        //Add divider in RecyclerView
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        //Initialize ShopViewModel
        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                //From this CartItem List we can submit it in cart adapter.
                cartListAdapter.submitList(cartItems);
                //If cart item is 0 then place orderButton will disable otherwise enabled.
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size() > 0);
            }
        });

        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
        //use of viewBinding here to set TotalPrice textView=orderTotalTextView
                fragmentCartBinding.orderTotalTextView.setText("Total:$ "+ aDouble.toString());
            }
        });

        //Handle Button PlaceOrder
        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_cartFragment_to_orderFragment);
            }
        });
    }

    @Override
    public void deleteItem(CartItem cartItem) {
        shopViewModel.removeItemFromCart(cartItem);
        Log.d(TAG,"delete item:" + cartItem.getProduct().getTitle());
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        shopViewModel.changeQuantity(cartItem,quantity);
    }
}