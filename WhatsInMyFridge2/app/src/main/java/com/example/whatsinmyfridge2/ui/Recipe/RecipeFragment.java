package com.example.whatsinmyfridge2.ui.Recipe;

import android.app.Activity;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.whatsinmyfridge2.objects.Fridge;

import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.RecipeCard;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RecipeFragment extends Fragment {
    private ArrayList<Item> items;
    //private RecipeViewModel recipeViewModel;
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton filterButton;
    private ArrayList<RecipeCard> recipes;
    private ArrayList<RecipeCard> filteredRecipes = new ArrayList<>();
    private ConstraintLayout constraintLayout;
    private RelativeLayout relativeLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe, container, false);
        ArrayList<Item> ingredients = new ArrayList<>();
        ArrayList<Item> ingredients2 = new ArrayList<>();
        ArrayList<Item> ingredients3 = new ArrayList<>();
        ArrayList<Item> ingredients4 = new ArrayList<>();
        ArrayList<Item> ingredients5 = new ArrayList<>();
        relativeLayout = root.findViewById(R.id.big_page);
        filterButton = root.findViewById(R.id.filterbutton);

        ingredients.add(new Item("Steak", 1000, 1.5, 1, "CARNE", getString(R.string.beefImg)));
        ingredients.add(new Item("White Rice", 1002, 1, 1, "CEREAL", getString(R.string.rice)));
        ingredients.add(new Item("Onion", 1005, 0.5, 1, "VEGETAL", getString(R.string.onion)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ingredients.forEach((item -> Fridge.addItemToDb(item)));
        }
        Fridge.addRecipe(new RecipeCard(getString(R.string.beefWithRice), "Steak with Rice", "2h30", "Medium", 4, ingredients, getString(R.string.SteakWithRice)));
        Fridge.addFilteredRecipe(new RecipeCard(getString(R.string.beefWithRice), "Steak with Rice", "2h30", "Medium", 4, ingredients, getString(R.string.SteakWithRice)));

        ingredients2.add(new Item("Cod", 1006, 1.5, 1, "PEIXE", getString(R.string.codImg)));
        ingredients2.add(new Item("Potato", 1007, 1, 1, "CEREAL", getString(R.string.potatoImg)));
        ingredients2.add(new Item("Olive Oil", 1008, 0.5, 1, "VEGETAL", getString(R.string.oliveOilImg)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ingredients2.forEach((item -> Fridge.addItemToDb(item)));
        }
        Fridge.addRecipe(new RecipeCard(getString(R.string.baked_cod), "Baked Cod with Potato", "1h30", "Hard", 4, ingredients2, getString(R.string.BakedCod)));
        Fridge.addFilteredRecipe(new RecipeCard(getString(R.string.baked_cod), "Baked Cod with Potato", "1h30", "Hard", 4, ingredients2, getString(R.string.BakedCod)));

        ingredients3.add(new Item("Bream Fish", 1009, 1.5, 1, "PEIXE", getString(R.string.codImg)));
        ingredients3.add(new Item("Potato", 1010, 1, 1, "CEREAL", getString(R.string.potatoImg)));
        ingredients3.add(new Item("Corn Oil", 1011, 0.5, 1, "VEGETAL", getString(R.string.cornOilImg)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ingredients3.forEach((item -> Fridge.addItemToDb(item)));
        }
        Fridge.addRecipe(new RecipeCard(getString(R.string.fish_and_chips), "Fish and Chips", "0h45", "Easy", 2, ingredients3, getString(R.string.FishAndChips)));
        Fridge.addFilteredRecipe(new RecipeCard(getString(R.string.fish_and_chips), "Fish and Chips", "0h45", "Easy", 2, ingredients3, getString(R.string.FishAndChips)));

        ingredients4.add(new Item("Minced Meat", 1012, 1.5, 1, "CARNE", getString(R.string.mincedMeatImg)));
        ingredients4.add(new Item("Spaghetti", 1003, 1, 1, "CEREAL", getString(R.string.spaghetti)));
        ingredients4.add(new Item("Salt", 1013, 0.5, 1, "VEGETAL", getString(R.string.saltImg)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ingredients4.forEach((item -> Fridge.addItemToDb(item)));
        }
        Fridge.addRecipe(new RecipeCard(getString(R.string.spaghetti_bolognese), "Spaghetti Bolognese", "2h00", "Medium", 2, ingredients4, getString(R.string.SpaghettiBolognese)));
        Fridge.addFilteredRecipe(new RecipeCard(getString(R.string.spaghetti_bolognese), "Spaghetti Bolognese", "2h00", "Medium", 2, ingredients4, getString(R.string.SpaghettiBolognese)));

        ingredients5.add(new Item("Lasagna Noodles", 1014, 1.5, 1, "CARNE", getString(R.string.lasagnaNoddleImg)));
        ingredients5.add(new Item("Minced Meat", 1015, 1, 1, "CEREAL", getString(R.string.mincedMeatImg)));
        ingredients5.add(new Item("Cheese", 1016, 0.5, 1, "VEGETAL", getString(R.string.cheeseImg)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ingredients5.forEach((item -> Fridge.addItemToDb(item)));
        }
        Fridge.addRecipe(new RecipeCard(getString(R.string.lasagna), "Lasagna", "3h00", "Hard", 6, ingredients5, getString(R.string.Lasagna)));
        Fridge.addFilteredRecipe(new RecipeCard(getString(R.string.lasagna), "Lasagna", "3h00", "Hard", 6, ingredients5, getString(R.string.Lasagna)));

        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mAdapter = new RecipeAdapter(Fridge.getRecipes(), root.getContext());
        mAdapter.notifyDataSetChanged();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecipeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), RecipePage.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }

            @Override
            public void onFavoriteClick(int position) {
                ArrayList<RecipeCard> recipes = Fridge.getRecipes();
                ArrayList<RecipeCard> filteredRecipes = Fridge.getFilteredRecipes();
                if(recipes.get(position).getIsFavorite()){
                    recipes.get(position).setIsFavorite(false);
                    filteredRecipes.get(position).setIsFavorite(false);
                }else{
                    recipes.get(position).setIsFavorite(true);
                    filteredRecipes.get(position).setIsFavorite(true);
                }
                Fridge.setRecipes(recipes);
                Fridge.setFilteredRecipes(filteredRecipes);
                mAdapter.setDataSet(filteredRecipes);
                mAdapter.notifyDataSetChanged();
            }
        });

        recipes = Fridge.getRecipes();
        constraintLayout = relativeLayout.findViewById(R.id.filter_layout);

        // Set on background click leave
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setVisibility(View.GONE);
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setElevation(1234);
                constraintLayout.setVisibility(View.VISIBLE);
            }
        });

        // close Btn
        ImageButton closeBttn = (ImageButton) constraintLayout.findViewById(R.id.closeBtn);
        closeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setVisibility(View.GONE);
            }
        });

        Button btn = (Button) constraintLayout.findViewById(R.id.ApplyBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                CheckBox easyDif = constraintLayout.findViewById(R.id.easyDifficulty);
                CheckBox medDif = constraintLayout.findViewById(R.id.mediumDifficulty);
                CheckBox hardDif = constraintLayout.findViewById(R.id.hardDifficulty);
                CheckBox time1 = constraintLayout.findViewById(R.id.timeBox1);
                CheckBox time2 = constraintLayout.findViewById(R.id.timeBox2);
                CheckBox time3 = constraintLayout.findViewById(R.id.timeBox3);
                CheckBox favorite = constraintLayout.findViewById(R.id.FavoriteBox);
                ArrayList<RecipeCard> easyRecipes = new ArrayList<>();
                ArrayList<RecipeCard> medRecipes = new ArrayList<>();
                ArrayList<RecipeCard> hardRecipes = new ArrayList<>();
                ArrayList<RecipeCard> time1Recipes = new ArrayList<>();
                ArrayList<RecipeCard> time2Recipes = new ArrayList<>();
                ArrayList<RecipeCard> time3Recipes = new ArrayList<>();
                ArrayList<RecipeCard> favoriteRecipes = new ArrayList<>();
                ArrayList<RecipeCard> recipesFilter = new ArrayList<>();


                if(!easyDif.isChecked() && !medDif.isChecked() && !hardDif.isChecked() && !time1.isChecked() && !time2.isChecked() && !time3.isChecked() && !favorite.isChecked()){
                    recipesFilter = recipes;
                }else {

                    if(easyDif.isChecked()){
                        easyRecipes = (ArrayList<RecipeCard>) recipes.stream().filter(f -> {
                            return f.getDifficulty().equals("Easy");
                        }).collect(Collectors.toList());
                    }
                    if(medDif.isChecked()){
                        medRecipes = (ArrayList<RecipeCard>) recipes.stream().filter(f -> {
                            return f.getDifficulty().equals("Medium");
                        }).collect(Collectors.toList());
                    }
                    if(hardDif.isChecked()){
                        hardRecipes = (ArrayList<RecipeCard>) recipes.stream().filter(f -> {
                            return f.getDifficulty().equals("Hard");
                        }).collect(Collectors.toList());
                    }
                    if(time1.isChecked()){
                        time1Recipes = (ArrayList<RecipeCard>) recipes.stream().filter(f -> {
                            return f.getTimeForFilter().equals("time1");
                        }).collect(Collectors.toList());
                    }
                    if(time2.isChecked()){
                        time2Recipes = (ArrayList<RecipeCard>) recipes.stream().filter(f -> {
                            return f.getTimeForFilter().equals("time2");
                        }).collect(Collectors.toList());
                    }
                    if(time3.isChecked()){
                        time3Recipes = (ArrayList<RecipeCard>) recipes.stream().filter(f -> {
                            return f.getTimeForFilter().equals("time3");
                        }).collect(Collectors.toList());
                    }

                    if(favorite.isChecked()){
                        favoriteRecipes = (ArrayList<RecipeCard>) recipes.stream().filter(f -> {
                            return f.getIsFavoriteFilter().equals("true");
                        }).collect(Collectors.toList());
                    }

                    for (RecipeCard r: easyRecipes) {
                        if(!recipesFilter.contains(r)){
                            recipesFilter.add(r);
                        }
                    }
                    for (RecipeCard r: medRecipes) {
                        if(!recipesFilter.contains(r)){
                            recipesFilter.add(r);
                        }
                    }
                    for (RecipeCard r: hardRecipes) {
                        if(!recipesFilter.contains(r)){
                            recipesFilter.add(r);
                        }
                    }
                    for (RecipeCard r: time1Recipes) {
                        if(!recipesFilter.contains(r)){
                            recipesFilter.add(r);
                        }
                    }
                    for (RecipeCard r: time2Recipes) {
                        if(!recipesFilter.contains(r)){
                            recipesFilter.add(r);
                        }
                    }

                    for (RecipeCard r: time3Recipes) {
                        if(!recipesFilter.contains(r)){
                            recipesFilter.add(r);
                        }
                    }

                    for (RecipeCard r: favoriteRecipes){
                        if(!recipesFilter.contains(r)){
                            recipesFilter.add(r);
                        }
                    }
                }
                Fridge.setFilteredRecipes(recipesFilter);
                mAdapter.setDataSet(recipesFilter);
                mAdapter.notifyDataSetChanged();
                constraintLayout.setVisibility(View.GONE);
            }
        });

        SearchView searchView = root.findViewById(R.id.search_bar);
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

    @Override
    public void onResume() {
        super.onResume();
        CheckBox easyDif = constraintLayout.findViewById(R.id.easyDifficulty);
        CheckBox medDif = constraintLayout.findViewById(R.id.mediumDifficulty);
        CheckBox hardDif = constraintLayout.findViewById(R.id.hardDifficulty);
        CheckBox time1 = constraintLayout.findViewById(R.id.timeBox1);
        CheckBox time2 = constraintLayout.findViewById(R.id.timeBox2);
        CheckBox time3 = constraintLayout.findViewById(R.id.timeBox3);
        CheckBox favorite = constraintLayout.findViewById(R.id.FavoriteBox);
        Button applyButton = constraintLayout.findViewById(R.id.ApplyBtn);
        easyDif.setChecked(false);
        medDif.setChecked(false);
        hardDif.setChecked(false);
        time1.setChecked(false);
        time2.setChecked(false);
        time3.setChecked(false);
        favorite.setChecked(false);
        applyButton.callOnClick();
        mAdapter.notifyDataSetChanged();
    }
}