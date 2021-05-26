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

import com.example.whatsinmyfridge2.objects.Type;

import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.RecipeCard;
import  com.example.whatsinmyfridge2.ui.Recipe.RecipePage;

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
        ArrayList<RecipeCard> recipeCards = new ArrayList<>();

        ingredients.add(new Item("Steak", 1000, 1.5, 1, "CARNE", getString(R.string.skyImg)));
        ingredients.add(new Item("Rice", 809, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients.add(new Item("Onion", 34, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));

        recipeCards.add(new RecipeCard(getString(R.string.skyImg), "Beef with Rice", "2h30", "Meddium", 4, ingredients));
        ingredients.clear();
        ingredients.add(new Item("Cod", 1000, 1.5, 1, "PEIXE", getString(R.string.skyImg)));
        ingredients.add(new Item("Potato", 809, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients.add(new Item("Olive Oil", 34, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(getString(R.string.baked_cod), "Baked Cod with Potato", "1h30", "Difficult", 4, ingredients));
        ingredients.clear();
        ingredients.add(new Item("Bream Fish", 1000, 1.5, 1, "PEIXE", getString(R.string.skyImg)));
        ingredients.add(new Item("Potato", 809, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients.add(new Item("Corn Oil", 34, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(getString(R.string.fish_and_chips), "Fish and Chips", "1h00", "Easy", 2, ingredients));
        ingredients.clear();
        ingredients.add(new Item("Minced Meat", 1000, 1.5, 1, "CARNE", getString(R.string.skyImg)));
        ingredients.add(new Item("Spaghetti", 809, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients.add(new Item("Salt", 34, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(getString(R.string.spaghetti_bolognese), "Spaghetti Bolognese", "2h", "Meddium", 2, ingredients));
        ingredients.clear();
        ingredients.add(new Item("Lasagna Noodles", 1000, 1.5, 1, "CARNE", getString(R.string.skyImg)));
        ingredients.add(new Item("Minced Meat", 809, 1, 1, "CEREAL", getString(R.string.skyImg)));
        ingredients.add(new Item("Cheese", 34, 0.5, 1, "VEGETAL", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(getString(R.string.lasagna), "Lasagna", "3h00", "Hard", 6, (ArrayList<Item>) ingredients.clone()));

        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mAdapter = new RecipeAdapter(recipeCards, root.getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecipeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), RecipePage.class);
                Bundle b = new Bundle();
                b.putParcelable("options", recipeCards.get(position));
                intent.putExtra("bundle", b);
                startActivity(intent);
            }
        });
        return root;
    }

}