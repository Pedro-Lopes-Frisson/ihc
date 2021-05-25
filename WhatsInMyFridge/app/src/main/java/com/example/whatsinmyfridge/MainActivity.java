package com.example.whatsinmyfridge;

import android.os.Bundle;
import android.util.Log;

import com.example.whatsinmyfridge.ObjectDeclaration.Item;
import com.example.whatsinmyfridge.ui.Cart.CartFragment;
import com.example.whatsinmyfridge.ui.home.FridgeFragment;
import com.example.whatsinmyfridge.ui.recipe.RecipeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FridgeFragment.TransferData {
    static String TAG = "MainActivity";
    static String  FRIDGETAG   = "FridgeTag";
    static String  CARTAG      = "CartTag";
    static String  LISTTAG     = "ListTag";
    public static String  RECIPETAG    = "RecipeTag";
    private FridgeFragment fridgeFragment;
    private RecipeFragment recipeFragment;
    private ListFragment listFragment;
    private CartFragment cartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_fridge, R.id.navigation_cart, R.id.navigation_list, R.id.navigation_recipe)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        FragmentManager fragmentManager = getSupportFragmentManager();

        fridgeFragment = (FridgeFragment) fragmentManager.findFragmentByTag(FRIDGETAG);
        if (fridgeFragment == null) {
            fridgeFragment = new FridgeFragment();
            fragmentManager.beginTransaction().add(R.id.parentFridge, fridgeFragment, FRIDGETAG).commit();
        }

        recipeFragment = (RecipeFragment) fragmentManager.findFragmentByTag(FRIDGETAG);
        if (recipeFragment == null) {
            recipeFragment = new RecipeFragment();
            fragmentManager.beginTransaction().add(R.id.recipeParent, recipeFragment, RECIPETAG).commit();
        }
        listFragment = (ListFragment) fragmentManager.findFragmentByTag(LISTTAG);
        if (listFragment == null) {
            listFragment = new ListFragment();
            fragmentManager.beginTransaction().add(R.id.ListParent, listFragment, LISTTAG).commit();
        }

        cartFragment = (CartFragment) fragmentManager.findFragmentByTag(CARTAG);
        if (cartFragment == null) {
            cartFragment = new CartFragment();
            fragmentManager.beginTransaction().add(R.id.cartParent, cartFragment, CARTAG).commit();
        }

    }

    @Override
    public void sendItems(ArrayList<Item> itemsInsideFridge) {
        Log.i(this.TAG, "Received: " + itemsInsideFridge + "!!\nRedirecting to " + this.RECIPETAG);
        recipeFragment.storeItems(itemsInsideFridge);
    }

}