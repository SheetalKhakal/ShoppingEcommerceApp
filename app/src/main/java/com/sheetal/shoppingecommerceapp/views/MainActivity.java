package com.sheetal.shoppingecommerceapp.views;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sheetal.shoppingecommerceapp.R;
import com.sheetal.shoppingecommerceapp.models.CartItem;
import com.sheetal.shoppingecommerceapp.repositories.SharedPrefManager;
import com.sheetal.shoppingecommerceapp.viewmodels.ShopViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    NavController navController;
    ShopViewModel shopViewModel;
    SharedPrefManager sharedPrefManager;
    private int cartQuantity= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

        shopViewModel= new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems)
            {
                int quantity= 0;
                for (CartItem cartItem: cartItems){
                    quantity += cartItem.getQuantity();
                }
                cartQuantity = quantity;
                invalidateOptionsMenu();
                Log.d(TAG,"onChanged: "+ cartItems.size());
            }
        });

    }

    //<- code for back arrow button
    @Override
    public boolean onSupportNavigateUp()
    {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem= menu.findItem(R.id.cartFragment);
        View actionView =menuItem.getActionView();

        TextView cartBadgeTextView= actionView.findViewById(R.id.cart_badge_textView);
        //set quantity on cartbadgeTextview
        cartBadgeTextView.setText(String.valueOf(cartQuantity));
        cartBadgeTextView.setVisibility(cartQuantity == 0 ? View.GONE : View.VISIBLE);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.logout){
            sharedPrefManager.logoutUser();
            Intent intent=new Intent(MainActivity.this, LoginFragment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            Toast.makeText(this, "You have been logged out.", Toast.LENGTH_SHORT).show();
        }
        return NavigationUI.onNavDestinationSelected(item,navController) || super.onOptionsItemSelected(item);


    }
}