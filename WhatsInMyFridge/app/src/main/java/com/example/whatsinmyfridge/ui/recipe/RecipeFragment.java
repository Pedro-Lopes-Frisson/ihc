package com.example.whatsinmyfridge.ui.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsinmyfridge.ObjectDeclaration.Item;
import com.example.whatsinmyfridge.R;
import com.example.whatsinmyfridge.objects.RecipeCard;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {

    private RecipeViewModel recipeViewModel;
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipeViewModel =
                new ViewModelProvider(this).get(RecipeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recipe, container, false);

        ArrayList<RecipeCard> recipeCards = new ArrayList<>();
        ArrayList<Item> ingredients = new ArrayList<>();
        ingredients.add(new Item("Steak", 1000, 1.5, 1, "Carne", getString(R.string.skyImg)));
        ingredients.add(new Item("Rice", 809, 1, 1, "Cereal", getString(R.string.skyImg)));
        ingredients.add(new Item("Onion", 34, 0.5, 1, "Vegetal", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(getString(R.string.skyImg),"Beef with Rice", "2h30", "Meddium", 4, ingredients));
        ingredients.clear();
        ingredients.add(new Item("Cod", 1000, 1.5, 1, "Peixe", getString(R.string.skyImg)));
        ingredients.add(new Item("Potato", 809, 1, 1, "Cereal", getString(R.string.skyImg)));
        ingredients.add(new Item("Olive Oil", 34, 0.5, 1, "Vegetal", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(getString(R.string.baked_cod),"Baked Cod with Potato", "1h30", "Difficult", 4, ingredients));
        ingredients.clear();
        ingredients.add(new Item("Bream Fish", 1000, 1.5, 1, "Peixe", getString(R.string.skyImg)));
        ingredients.add(new Item("Potato", 809, 1, 1, "Cereal", getString(R.string.skyImg)));
        ingredients.add(new Item("Corn Oil", 34, 0.5, 1, "Vegetal", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(getString(R.string.fish_and_chips),"Fish and Chips", "1h00", "Easy", 2, ingredients));
        ingredients.clear();
        ingredients.add(new Item("Minced Meat", 1000, 1.5, 1, "Carne", getString(R.string.skyImg)));
        ingredients.add(new Item("Spaghetti", 809, 1, 1, "Cereal", getString(R.string.skyImg)));
        ingredients.add(new Item("Salt", 34, 0.5, 1, "Vegetal", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(getString(R.string.spaghetti_bolognese),"Spaghetti Bolognese", "2h", "Meddium", 2, ingredients));
        ingredients.clear();
        ingredients.add(new Item("Lasagna Noodles", 1000, 1.5, 1, "Carne", getString(R.string.skyImg)));
        ingredients.add(new Item("Minced Meat", 809, 1, 1, "Cereal", getString(R.string.skyImg)));
        ingredients.add(new Item("Cheese", 34, 0.5, 1, "Vegetal", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(getString(R.string.lasagna),"Lasagna", "3h00", "Hard", 6, ingredients));

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
                intent.putExtra("Recipe", recipeCards.get(position));
                startActivity(intent);
            }
        });

        SearchView searchView = (SearchView) root.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return root;
    }
}