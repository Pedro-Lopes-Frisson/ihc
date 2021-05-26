package com.example.whatsinmyfridge.ui.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.whatsinmyfridge.ObjectDeclaration.Item;
import com.example.whatsinmyfridge.R;
import com.example.whatsinmyfridge.databinding.ActivityMainBinding;
import com.example.whatsinmyfridge.databinding.IngredientRowBinding;
import com.example.whatsinmyfridge.databinding.RecipePageBinding;
import com.example.whatsinmyfridge.objects.RecipeCard;

import java.util.ArrayList;

public class RecipePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        Intent intent = getIntent();
        RecipeCard recipeCard = intent.getParcelableExtra("Recipe");


        String imageRes = recipeCard.getmImageResource();
        String name = recipeCard.getRecipeName();
        String dif = recipeCard.getDifficulty();
        String time = recipeCard.getTimeToCook();
        int xPeople = recipeCard.getxPeople();
        ArrayList<Item> ing = recipeCard.getIngredients();

        ImageView imageView = findViewById(R.id.Recipe_Image);
        Glide.with(this).asBitmap().load(imageRes).into(imageView);
        //imageView.setImageResource(imageRes);

        TextView recipe_name = findViewById(R.id.Recipe_Name);
        recipe_name.setText(name);

        TextView recipe_dif = findViewById(R.id.recipe_dif);
        recipe_dif.setText(dif);

        TextView recipe_time = findViewById(R.id.time_to_cook);
        recipe_time.setText(time);

        TextView recipe_people = findViewById(R.id.person_per_recipe);
        recipe_people.setText(String.valueOf(xPeople));
    }

}