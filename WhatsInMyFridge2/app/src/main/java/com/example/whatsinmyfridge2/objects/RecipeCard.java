package com.example.whatsinmyfridge2.objects;

import java.util.ArrayList;

public class RecipeCard {
    private String mImageResource;
    private String recipeName;
    private String timeToCook;
    private String difficulty;
    private int xPeople;
    private ArrayList<Item> ingredients;

    public RecipeCard(String image, String name, String time, String diff, int x, ArrayList<Item> ingredients) {
        mImageResource = image;
        recipeName = name;
        timeToCook = time;
        difficulty = diff;
        xPeople = x;
        this.ingredients = ingredients;
    }

    public String getmImageResource() {
        return mImageResource;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public ArrayList<Item> getIngredients() {
        return ingredients;
    }

    public int getxPeople() {
        return xPeople;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getTimeToCook() {
        return timeToCook;
    }

}
