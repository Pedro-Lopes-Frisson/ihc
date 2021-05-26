package com.example.whatsinmyfridge;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.whatsinmyfridge.ObjectDeclaration.Item;
import com.example.whatsinmyfridge.ui.Cart.CartFragment;
import com.example.whatsinmyfridge.ui.home.FridgeFragment;
import com.example.whatsinmyfridge.ui.recipe.RecipeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FridgeFragment.TransferData {
    static String TAG = "MainActivity";
    public static String FRIDGETAG = "FridgeTag";
    public static String CARTAG = "CartTag";
    public static String LISTTAG = "ListTag";
    public static String RECIPETAG = "RecipeTag";
    private FridgeFragment fridgeFragment;
    private RecipeFragment recipeFragment;
    ArrayList<Item> items = new ArrayList<>(); // Store static data
    private ListFragment listFragment;
    private CartFragment cartFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.



        items.add(new Item("Bife de Vaca", 1000, 2, 1, "Carne", getString(R.string.skyImg)));
        items.add(new Item("Bife de Frango", 1001, 4, 1, "Carne", getString(R.string.skyImg)));
        items.add(new Item("Arroz", 809, 5, 1, "Cereal", getString(R.string.skyImg)));
        items.add(new Item("Massa", 1080, 9, 2, "Massa", getString(R.string.skyImg)));
        items.add(new Item("Chocolate", 10, 1, 1, "Sobremesa", getString(R.string.skyImg)));

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_fridge, R.id.navigation_cart, R.id.navigation_list, R.id.navigation_recipe)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    @Override
    public ArrayList<Item> sendItems(ArrayList<Item> itemsInsideFridge) {
        return itemsInsideFridge;

    }

    /*
    @Override
    public void sendItems(ArrayList<Item> itemsInsideFridge) {
        // No clue
        // TODO tudo : (
        // Feito tenei encontrar a tag quer da maneira como o fabio disse quer de outra maneira
        // quando fui à fragmentFridge e fiz root.getTag() devolveu FridgeTag
        // Mas isso é  porque é a default view.......
        // Iam sry did my best
        String tag = "android:switcher:" + R.id.nav_view + ":" + 1;
        recipeFragment = (RecipeFragment) getSupportFragmentManager().findFragmentByTag(tag);
        // but normally this will always be null
        recipeFragment = (RecipeFragment) fragmentManager.findFragmentById(R.id.recipeParentUnique);
        Log.i(this.TAG, "First Log" + recipeFragment + " <----     adder was ->"  );
        ArrayList<String> a = new ArrayList<>();
        a.add(FRIDGETAG);
        a.add(CARTAG);
        a.add(LISTTAG);
        a.add(RECIPETAG);
        //recipeFragment = (RecipeFragment) fragmentManager.findFragmentById(Integer.parseInt(RECIPETAG));
        Log.i(this.TAG, "Quitted while loop and " + recipeFragment + " <----     adder was ->"  );
        int adder = 0;
        tag = "android:switcher:" + R.id.nav_view + ":" + adder;
        while (recipeFragment == null && adder < 6) {
            tag = "android:switcher:" + R.id.nav_view + ":" + adder;
            Log.i(this.TAG, tag);
            recipeFragment = (RecipeFragment) getSupportFragmentManager().findFragmentByTag(tag);
            adder++;
        }
        Log.i(this.TAG, "Quitted while loop and " + recipeFragment + " <----     adder was ->" + adder);

        adder = 0;
        while (recipeFragment == null && adder < 6) {
            tag = "android:switcher:" + R.id.nav_host_fragment + ":" + adder;
            Log.i(this.TAG, tag);
            recipeFragment = (RecipeFragment) getSupportFragmentManager().findFragmentByTag(tag);
            adder++;
        }
        Log.i(this.TAG, "Quitted while loop and " + recipeFragment + " <----     adder was ->" + adder);
        if (recipeFragment == null) {
            Log.i(this.TAG, getString(R.string.erro1) + tag);
            tag = "android:switcher:" + R.id.nav_view + ":" + 2;
            recipeFragment = (RecipeFragment) getSupportFragmentManager().findFragmentByTag(tag);
            if (recipeFragment == null) {

                Log.i(this.TAG, getString(R.string.erro1) + tag);
            }
            // so we create a new one here
            recipeFragment = new RecipeFragment(); // No clue if this is viable
        }
        //and then this works
        Log.i(this.TAG, "Received: " + itemsInsideFridge + "!!\nRedirecting to " + this.RECIPETAG);
        recipeFragment.storeItems(itemsInsideFridge);
    }
    */

}