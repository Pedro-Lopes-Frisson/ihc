package com.example.whatsinmyfridge2.ui.Recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.whatsinmyfridge2.objects.Fridge;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.R;
/*
import com.example.whatsinmyfridge2.databinding.ActivityMainBinding; // whats this? aH okey pensei que
import com.example.whatsinmyfridge2.databinding.IngredientRowBinding;
import com.example.whatsinmyfridge2.databinding.RecipePageBinding;
JUST INT CASE
 */
import com.example.whatsinmyfridge2.objects.RecipeCard;

import java.util.ArrayList;

public class RecipePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);
        Intent intent = getIntent();
        int pos = getIntent().getIntExtra("pos", 0);


        String imageRes = Fridge.getRecipes().get(pos).getmImageResource(); //yahp
        String name = Fridge.getRecipes().get(pos).getRecipeName();
        String dif = Fridge.getRecipes().get(pos).getDifficulty();
        String time = Fridge.getRecipes().get(pos).getTimeToCook();
        int xPeople = Fridge.getRecipes().get(pos).getxPeople();
        ArrayList<Item> ing = Fridge.getRecipes().get(pos).getIngredients();
        /*Log.i("teste", String.valueOf(pos));
        Log.i("teste", Fridge.getRecipes().get(pos).getRecipeName());
        Log.i("teste", String.valueOf(ing.size()));
        Log.i("teste", ing.get(0).getName());
        Log.i("teste", ing.get(1).getName());
        Log.i("teste", ing.get(2).getName());*/


        //Ir buscar os ingredientes todos do fridge, depois verificar se os ings da receita estao no frigorifico e se sim, a quantidade se chega ou nao e depois mete-los em available ou missing consoante

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

        int countavail = 0;
        int countmissing = 0;
        for(int i = 0; i < ing.size(); i++){
            Item ingRecipe = ing.get(i);
            boolean inFridge = false;
            for(int j = 0; j < Fridge.getItems().size(); j++) {
                Item ingFridge = Fridge.getItem(j);
                if (ingFridge.getID() == ingRecipe.getID()) {
                    inFridge = true;
                    Log.i("Fridge", ingFridge.getName());
                    Log.i("Fridge", ingRecipe.getName());
                    Log.i("Fridge", String.valueOf(countavail));
                    Log.i("Fridge", String.valueOf(countmissing));
                    if (ingFridge.getWeight() > ingRecipe.getWeight()) {
                        ImageView imageView1;
                        TextView textView;
                        if (countavail == 0) {
                            imageView1 = findViewById(R.id.ingredient_image1);
                            textView = findViewById(R.id.ingredient_name1);
                        } else if (countavail == 1) {
                            imageView1 = findViewById(R.id.ingredient_image2);
                            textView = findViewById(R.id.ingredient_name2);
                        } else {
                            imageView1 = findViewById(R.id.ingredient_image3);
                            textView = findViewById(R.id.ingredient_name3);
                        }
                        Glide.with(this).asBitmap().load(ingRecipe.getImage()).into(imageView1);
                        String teste = ingRecipe.getName() + "                     " + ingRecipe.getWeight();
                        textView.setText(teste);
                        countavail++;
                        break;
                    } else {
                        ImageView imageView1;
                        TextView textView;
                        if (countmissing == 0) {
                            imageView1 = findViewById(R.id.ingredient_image4);
                            textView = findViewById(R.id.ingredient_name4);
                        } else if (countmissing == 1) {
                            imageView1 = findViewById(R.id.ingredient_image5);
                            textView = findViewById(R.id.ingredient_name5);
                        } else {
                            imageView1 = findViewById(R.id.ingredient_image6);
                            textView = findViewById(R.id.ingredient_name6);
                        }
                        Glide.with(this).asBitmap().load(ingRecipe.getImage()).into(imageView1);
                        String teste = ingRecipe.getName() + "                     " + (ingRecipe.getWeight() - ingFridge.getWeight());
                        textView.setText(teste);
                        countmissing++;
                        break;
                    }
                }
            }
            if(!inFridge){
                ImageView imageView1;
                TextView textView;
                if (countmissing == 0) {
                    imageView1 = findViewById(R.id.ingredient_image4);
                    textView = findViewById(R.id.ingredient_name4);
                } else if (countmissing == 1) {
                    imageView1 = findViewById(R.id.ingredient_image5);
                    textView = findViewById(R.id.ingredient_name5);
                } else {
                    imageView1 = findViewById(R.id.ingredient_image6);
                    textView = findViewById(R.id.ingredient_name6);
                }
                Glide.with(this).asBitmap().load(ingRecipe.getImage()).into(imageView1);
                String teste = ingRecipe.getName() + "                     " + ingRecipe.getWeight();
                textView.setText(teste);
                countmissing++;
            }
        }

        TextView ing1 = findViewById(R.id.ingredient_name1);
        TextView ing2 = findViewById(R.id.ingredient_name2);
        TextView ing3 = findViewById(R.id.ingredient_name3);
        TextView ing4 = findViewById(R.id.ingredient_name4);
        TextView ing5 = findViewById(R.id.ingredient_name5);
        TextView ing6 = findViewById(R.id.ingredient_name6);
        Log.i("tessss", "Jola");
        Log.i("tessss", ing3.getText().toString());
        if(ing1.getText().toString() == "" || ing1.getText() == null){
            ing1.setVisibility(View.GONE);
        }
        if(ing2.getText().toString() == "" || ing2.getText() == null){
            ing2.setVisibility(View.GONE);
        }
        if(ing3.getText().toString() == "" || ing3.getText() == null){
            ing3.setVisibility(View.GONE);
        }
        if(ing4.getText().toString() == "" || ing4.getText() == null){
            ing4.setVisibility(View.GONE);
        }
        if(ing5.getText().toString() == "" || ing5.getText() == null){
            ing5.setVisibility(View.GONE);
        }
        if(ing6.getText().toString() == "" || ing6.getText() == null){
            ing6.setVisibility(View.GONE);
        }
    }

}