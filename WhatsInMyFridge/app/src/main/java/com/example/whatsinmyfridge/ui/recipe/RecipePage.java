package com.example.whatsinmyfridge.ui.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.whatsinmyfridge.ObjectDeclaration.Item;
import com.example.whatsinmyfridge.R;
import com.example.whatsinmyfridge.objects.RecipeCard;

import java.util.ArrayList;

public class RecipePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);

        Intent intent = getIntent();
        RecipeCard recipeCard = intent.getParcelableExtra("Recipe");

        int imageRes = recipeCard.getmImageResource();
        String name = recipeCard.getRecipeName();
        String dif = recipeCard.getDifficulty();
        String time = recipeCard.getTimeToCook();
        int xPeople = recipeCard.getxPeople();
        ArrayList<Item> ing = recipeCard.getIngredients();

        ImageView imageView = findViewById(R.id.Recipe_Image);
        imageView.setImageResource(imageRes);

        TextView recipe_name = findViewById(R.id.Recipe_Name);
        recipe_name.setText(name);

        TextView recipe_dif = findViewById(R.id.recipe_dif);
        recipe_dif.setText(dif);

        TextView recipe_time = findViewById(R.id.time_to_cook);
        recipe_time.setText(time);

        TextView recipe_people = findViewById(R.id.person_per_recipe);
        recipe_people.setText(String.valueOf(xPeople));

        LinearLayout available_ing = findViewById(R.id.available_ingredients_cards);

    }
}