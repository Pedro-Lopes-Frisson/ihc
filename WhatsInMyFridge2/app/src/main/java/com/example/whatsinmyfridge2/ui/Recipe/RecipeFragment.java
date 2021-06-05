package com.example.whatsinmyfridge2.ui.Recipe;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;

import com.example.whatsinmyfridge2.objects.Fridge;

import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.RecipeCard;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {
    private ArrayList<Item> items;
    //private RecipeViewModel recipeViewModel;
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe, container, false);
        Bundle bundle = getArguments();
        Log.i("Recipe", "" + bundle.getParcelableArrayList("itemsInsideFridge"));
        ArrayList<Item> ingredients = new ArrayList<>();
        ArrayList<Item> ingredients2 = new ArrayList<>();
        ArrayList<Item> ingredients3 = new ArrayList<>();
        ArrayList<Item> ingredients4 = new ArrayList<>();
        ArrayList<Item> ingredients5 = new ArrayList<>();
        //ArrayList<RecipeCard> recipeCards = new ArrayList<>();

        ingredients.add(new Item("Steak", 1000, 1.5, 1, "CARNE", getString(R.string.skyImg)));
        ingredients.add(new Item("White Rice", 1002, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients.add(new Item("Onion", 1005, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));
        Fridge.addRecipe(new RecipeCard(getString(R.string.skyImg), "Beef with Rice", "2h30", "Medium", 4, ingredients));

        ingredients2.add(new Item("Cod", 1006, 1.5, 1, "PEIXE", getString(R.string.skyImg)));
        ingredients2.add(new Item("Potato", 1007, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients2.add(new Item("Olive Oil", 1008, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));
        Fridge.addRecipe(new RecipeCard(getString(R.string.baked_cod), "Baked Cod with Potato", "1h30", "Difficult", 4, ingredients2));

        ingredients3.add(new Item("Bream Fish", 1009, 1.5, 1, "PEIXE", getString(R.string.skyImg)));
        ingredients3.add(new Item("Potato", 1010, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients3.add(new Item("Corn Oil", 1011, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));
        Fridge.addRecipe(new RecipeCard(getString(R.string.fish_and_chips), "Fish and Chips", "1h00", "Easy", 2, ingredients3));

        ingredients4.add(new Item("Minced Meat", 1012, 1.5, 1, "CARNE", getString(R.string.skyImg)));
        ingredients4.add(new Item("Spaghetti", 1003, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients4.add(new Item("Salt", 1013, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));
        Fridge.addRecipe(new RecipeCard(getString(R.string.spaghetti_bolognese), "Spaghetti Bolognese", "2h", "Medium", 2, ingredients4));

        ingredients5.add(new Item("Lasagna Noodles", 1014, 1.5, 1, "CARNE", getString(R.string.skyImg)));
        ingredients5.add(new Item("Minced Meat", 1015, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients5.add(new Item("Cheese", 1016, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));
        Fridge.addRecipe(new RecipeCard(getString(R.string.lasagna), "Lasagna", "3h00", "Hard", 6, ingredients5));

        /*for(int i  = 0; i < 5; i++){
            ArrayList<Item> teste = Fridge.getRecipes().get(i).getIngredients();
            Log.i("Total", Fridge.getRecipes().get(i).getRecipeName());
            Log.i("Total", teste.get(0).getName());
            Log.i("Total", teste.get(1).getName());
            Log.i("Total", teste.get(2).getName());
        }*/
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mAdapter = new RecipeAdapter(Fridge.getRecipes(), root.getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecipeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), RecipePage.class);
                //Bundle b = new Bundle();
                //b.putParcelable("options", Fridge.getRecipes().get(position));
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
        return root;
    }

}