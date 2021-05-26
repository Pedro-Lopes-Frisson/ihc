package com.example.whatsinmyfridge2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

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


        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(navListener);
        getFragmentManager().beginTransaction().replace(R.id.frag_container, new FridgeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag = null;


            switch (item.getItemId()) {
                case R.id.navigation_fridge:
                    selectedFrag = new FridgeFragment();
                    break;
                case R.id.navigation_cart:
                    selectedFrag = new CartFragment();
                    break;
                case R.id.navigation_list:
                    selectedFrag = new ShoppingFragment();
                    break;
                case R.id.navigation_recipe:
                    selectedFrag = new RecipeFragment();
                    break;
                default:
                    Log.e(TAG, "FDs que miséria!");
                    break;
            }
            items.add(new Item("Bife de Vaca", 1000, 2, 1, "CARNE", getString(R.string.skyImg)));
            items.add(new Item("Bife de Frango", 1001, 4, 1, "CARNE", getString(R.string.skyImg)));
            items.add(new Item("Arroz", 809, 5, 1, "CEREAL", getString(R.string.skyImg)));
            items.add(new Item("Massa", 1080, 9, 2, "CEREAL", getString(R.string.skyImg)));
            items.add(new Item("Chocolate", 10, 1, 1, "SOBREMESA", getString(R.string.skyImg)));

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("itemsInsideFridge", items);

            selectedFrag.setArguments(bundle);

            getFragmentManager().beginTransaction().replace(R.id.frag_container, selectedFrag).commit();
            return true;

        }
    };

    @Override
    public void sendItems(ArrayList<Item> itemsInsideFridge) {
        this.items = itemsInsideFridge;
    }
}