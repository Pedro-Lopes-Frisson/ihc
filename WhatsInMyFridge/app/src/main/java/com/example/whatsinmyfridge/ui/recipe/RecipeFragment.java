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
        final TextView textView = root.findViewById(R.id.text_notifications);

        ArrayList<RecipeCard> recipeCards = new ArrayList<>();
        ArrayList<Item> bifeComArroz = new ArrayList<Item>();
        bifeComArroz.add(new Item("Bife de Vaca", 1000, 1.5, 1, "Carne", getString(R.string.skyImg)));
        bifeComArroz.add(new Item("Arroz", 809, 1, 1, "Cereal", getString(R.string.skyImg)));
        bifeComArroz.add(new Item("Cebola", 34, 0.5, 1, "Vegetal", getString(R.string.skyImg)));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with potato", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice2", "2h30", "Meddium", 4, bifeComArroz)); //Falta arranjar as receitas
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice3", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice4", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice5", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice6", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice7", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice8", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice9", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice10", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice11", "2h30", "Meddium", 4, bifeComArroz));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice12", "2h30", "Meddium", 4, bifeComArroz));

        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mAdapter = new RecipeAdapter(recipeCards);

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