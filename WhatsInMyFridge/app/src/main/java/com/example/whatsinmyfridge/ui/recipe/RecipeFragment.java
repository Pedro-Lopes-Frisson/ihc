package com.example.whatsinmyfridge.ui.recipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsinmyfridge.R;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {

    private RecipeViewModel recipeViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipeViewModel =
                new ViewModelProvider(this).get(RecipeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_recipe, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);

        ArrayList<RecipeCard> recipeCards = new ArrayList<>();
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice2"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz,"Beef with rice3"));

        mRecyclerView = root.findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mAdapter = new RecipeAdapter(recipeCards);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }
}