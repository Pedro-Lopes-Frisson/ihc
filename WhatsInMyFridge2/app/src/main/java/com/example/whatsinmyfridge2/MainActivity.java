package com.example.whatsinmyfridge2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.annotation.SuppressLint;
import android.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.whatsinmyfridge2.objects.Fridge;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.Type;
import com.example.whatsinmyfridge2.ui.Cart.CartFragment;
import com.example.whatsinmyfridge2.ui.Fridge.FridgeFragment;
import com.example.whatsinmyfridge2.ui.Recipe.RecipeFragment;
import com.example.whatsinmyfridge2.ui.ShoppingList.ShoppingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FridgeFragment.TransferData{
    private static String TAG = "MAIN";
    private ArrayList<Item> items = new ArrayList<>();
    private MutableLiveData<ArrayList<Item>> itemsInsideFridge = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Fridge");

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(navListener);
        getFragmentManager().beginTransaction().replace(R.id.frag_container, new FridgeFragment()).commit();

        Fridge.addItemToDb(new Item("Steak", 1000, 0, 1, "CARNE", getString(R.string.beefImg),"kg"));
        Fridge.addItemToDb(new Item("White Rice", 1002, 0, 1, "CEREAL", getString(R.string.rice),"kg"));
        Fridge.addItemToDb(new Item("Onion", 1005, 0.0, 1, "VEGETAL", getString(R.string.onion),"kg"));
        Fridge.addItemToDb(new Item("Cod", 1006, 0.0, 1, "PEIXE", getString(R.string.codImg),"kg"));
        Fridge.addItemToDb(new Item("Potato", 1007, 0, 1, "CEREAL", getString(R.string.potatoImg),"kg"));
        Fridge.addItemToDb(new Item("Olive Oil", 1008, 0.0, 1, "VEGETAL", getString(R.string.oliveOilImg),"kg"));
        Fridge.addItemToDb(new Item("Bream Fish", 1009, 000, 1, "PEIXE", getString(R.string.codImg),"kg"));
        Fridge.addItemToDb(new Item("Corn Oil", 1011, 0.0, 1, "VEGETAL", getString(R.string.cornOilImg),"kg"));
        Fridge.addItemToDb(new Item("Minced Meat", 1012, 0.0, 1, "CARNE", getString(R.string.mincedMeatImg),"kg"));
        Fridge.addItemToDb(new Item("Spaghetti", 1003, 0, 1, "CEREAL", getString(R.string.spaghetti),"g"));
        Fridge.addItemToDb(new Item("Salt", 1013, 0.0, 1, "VEGETAL", getString(R.string.saltImg),"g"));
        Fridge.addItemToDb(new Item("Lasagna Noodles", 1014, 0.0, 1, "CARNE", getString(R.string.lasagnaNoddleImg),"g"));
        Fridge.addItemToDb(new Item("Minced Meat", 1015, 0, 1, "CEREAL", getString(R.string.mincedMeatImg),"kg"));
        Fridge.addItemToDb(new Item("Cheese", 1016, 0.0, 1, "VEGETAL", getString(R.string.cheeseImg),"g"));
        Fridge.addItemToCart(new Item("Potato", 1007, 0, 1, "CEREAL", getString(R.string.potatoImg),"kg"));
        Fridge.addItemToCart(new Item("Corn Oil", 1011, 0.0, 1, "VEGETAL", getString(R.string.cornOilImg)));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag = null;


            switch (item.getItemId()) {
                case R.id.navigation_fridge:
                    setTitle("Fridge");
                    selectedFrag = new FridgeFragment();
                    break;
                case R.id.navigation_cart:
                    setTitle("Cart");
                    selectedFrag = new CartFragment();
                    break;
                case R.id.navigation_list:
                    setTitle("Shopping List");
                    selectedFrag = new ShoppingFragment();
                    break;
                case R.id.navigation_recipe:
                    setTitle("Recipes");
                    selectedFrag = new RecipeFragment();
                    break;
                default:
                    Log.e(TAG, "FDs que miséria!");
                    break;
            }


            getFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFrag).commit();
            return true;

        }
    };

    @Override
    public void sendItems(ArrayList<Item> itemsInsideFridge) {
        this.items = itemsInsideFridge;
    }
}