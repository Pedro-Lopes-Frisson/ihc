package com.example.whatsinmyfridge2.ui.Recipe;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Fridge;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.RecipeCard;

import java.util.ArrayList;

/*
import com.example.whatsinmyfridge2.databinding.ActivityMainBinding; // whats this? aH okey pensei que
import com.example.whatsinmyfridge2.databinding.IngredientRowBinding;
import com.example.whatsinmyfridge2.databinding.RecipePageBinding;
JUST INT CASE
 */

public class RecipePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        int pos = getIntent().getIntExtra("pos", 0);
        String imageRes = Fridge.getFilteredRecipes().get(pos).getmImageResource();
        String name = Fridge.getFilteredRecipes().get(pos).getRecipeName();
        String dif = Fridge.getFilteredRecipes().get(pos).getDifficulty();
        String time = Fridge.getFilteredRecipes().get(pos).getTimeToCook();
        int xPeople = Fridge.getFilteredRecipes().get(pos).getxPeople();
        ArrayList<Item> ing = Fridge.getFilteredRecipes().get(pos).getIngredients();
        boolean favorite = Fridge.getFilteredRecipes().get(pos).getIsFavorite();
        String instructions = Fridge.getFilteredRecipes().get(pos).getInstructions();

        ImageView imageView = findViewById(R.id.Recipe_Image);
        Glide.with(this).asBitmap().load(imageRes).into(imageView);

        TextView recipe_name = findViewById(R.id.Recipe_Name);
        recipe_name.setText(name);

        TextView recipe_dif = findViewById(R.id.recipe_dif);
        recipe_dif.setText(dif);

        TextView recipe_time = findViewById(R.id.time_to_cook);
        recipe_time.setText(time);

        TextView recipe_people = findViewById(R.id.person_per_recipe);
        recipe_people.setText(String.valueOf(xPeople));

        TextView instructionsText = findViewById(R.id.instructionsText);
        instructionsText.setText(instructions);
        instructionsText.setTypeface(Typeface.MONOSPACE);

        ImageView favoriteStar = findViewById(R.id.favorite_button);
        if(favorite){
            favoriteStar.setImageResource(R.drawable.ic_favorite_selected);
        }else{
            favoriteStar.setImageResource(R.drawable.ic_favorite_not_selected);
        }

        favoriteStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<RecipeCard> recipes = Fridge.getRecipes();
                ArrayList<RecipeCard> filteredRecipes = Fridge.getFilteredRecipes();
                if(recipes.get(pos).getIsFavorite()){
                    recipes.get(pos).setIsFavorite(false);
                    filteredRecipes.get(pos).setIsFavorite(false);
                    favoriteStar.setImageResource(R.drawable.ic_favorite_not_selected);
                }else{
                    recipes.get(pos).setIsFavorite(true);
                    filteredRecipes.get(pos).setIsFavorite(true);
                    favoriteStar.setImageResource(R.drawable.ic_favorite_selected);
                }
                Fridge.setRecipes(recipes);
                Fridge.setFilteredRecipes(filteredRecipes);
            }
        });

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        TextView ing1 = findViewById(R.id.ingredient_name1);
        TextView ing2 = findViewById(R.id.ingredient_name2);
        TextView ing3 = findViewById(R.id.ingredient_name3);
        TextView ing4 = findViewById(R.id.ingredient_name4);
        TextView ing5 = findViewById(R.id.ingredient_name5);
        TextView ing6 = findViewById(R.id.ingredient_name6);
        ing1.setTypeface(Typeface.MONOSPACE);
        ing2.setTypeface(Typeface.MONOSPACE);
        ing3.setTypeface(Typeface.MONOSPACE);
        ing4.setTypeface(Typeface.MONOSPACE);
        ing5.setTypeface(Typeface.MONOSPACE);
        ing6.setTypeface(Typeface.MONOSPACE);
        ImageView ing1Im = findViewById(R.id.ingredient_image1);
        ImageView ing2Im = findViewById(R.id.ingredient_image2);
        ImageView ing3Im = findViewById(R.id.ingredient_image3);
        ImageView ing4Im = findViewById(R.id.ingredient_image4);
        ImageView ing5Im = findViewById(R.id.ingredient_image5);
        ImageView ing6Im = findViewById(R.id.ingredient_image6);


        int countavail = 0;
        int countmissing = 0;
        for(int i = 0; i < ing.size(); i++){
            Item ingRecipe = ing.get(i);
            boolean inFridge = false;
            for(int j = 0; j < Fridge.getItems().size(); j++) {
                Item ingFridge = Fridge.getItem(j);
                if (ingFridge.getID() == ingRecipe.getID()) {
                    inFridge = true;
                    if (ingFridge.getWeight() > ingRecipe.getWeight()) {
                        ImageView imageView1;
                        TextView textView;
                        if (countavail == 0) {
                            imageView1 = ing1Im;
                            textView = ing1;
                        } else if (countavail == 1) {
                            imageView1 = ing2Im;
                            textView = ing2;
                        } else {
                            imageView1 = ing3Im;
                            textView = ing3;
                        }
                        Glide.with(this).asBitmap().load(ingRecipe.getImage()).into(imageView1);
                        String teste = String.format("  %-20s %-20.1f", ingRecipe.getName(), ingRecipe.getWeight());
                        textView.setText(teste);
                        countavail++;
                    } else {
                        ImageView imageView1;
                        TextView textView;
                        if (countmissing == 0) {
                            imageView1 = ing4Im;
                            textView = ing4;
                        } else if (countmissing == 1) {
                            imageView1 = ing5Im;
                            textView = ing5;
                        } else {
                            imageView1 = ing6Im;
                            textView = ing6;
                        }
                        Glide.with(this).asBitmap().load(ingRecipe.getImage()).into(imageView1);
                        String teste = String.format("  %-20s %-20.1f", ingRecipe.getName(), (ingRecipe.getWeight() - ingFridge.getWeight()));
                        textView.setText(teste);
                        countmissing++;
                    }
                    break;
                }
            }
            if(!inFridge){
                ImageView imageView1;
                TextView textView;
                if (countmissing == 0) {
                    imageView1 = ing4Im;
                    textView = ing4;
                } else if (countmissing == 1) {
                    imageView1 = ing5Im;
                    textView = ing5;
                } else {
                    imageView1 = ing6Im;
                    textView = ing6;
                }
                Glide.with(this).asBitmap().load(ingRecipe.getImage()).into(imageView1);
                String teste = String.format("  %-20s %-20.1f", ingRecipe.getName(), ingRecipe.getWeight());
                textView.setText(teste);
                countmissing++;
            }
        }


        if(ing1.getText().toString().equals("") || ing1.getText() == null){
            ing1.setVisibility(View.GONE);
            ing1Im.setVisibility(View.GONE);
        }
        if(ing2.getText().toString().equals("") || ing2.getText() == null){
            ing2.setVisibility(View.GONE);
            ing2Im.setVisibility(View.GONE);
        }
        if(ing3.getText().toString().equals("") || ing3.getText() == null){
            ing3.setVisibility(View.GONE);
            ing3Im.setVisibility(View.GONE);
        }
        if(ing4.getText().toString().equals("") || ing4.getText() == null){
            ing4.setVisibility(View.GONE);
            ing4Im.setVisibility(View.GONE);
        }
        if(ing5.getText().toString().equals("") || ing5.getText() == null){
            ing5.setVisibility(View.GONE);
            ing5Im.setVisibility(View.GONE);
        }
        if(ing6.getText().toString().equals("") || ing6.getText() == null){
            ing6.setVisibility(View.GONE);
            ing6Im.setVisibility(View.GONE);
        }
    }

}