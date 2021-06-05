package com.example.whatsinmyfridge2.objects;

import android.os.Build;
import android.text.format.Time;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCard that = (RecipeCard) o;
        return recipeName.equals(that.recipeName);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(recipeName);
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

    public String getTimeForFilter() {
        Date date;
        String[] parse;
        parse = timeToCook.split("h");
        if(parse[0].equals("0")){
            return "time1";
        }else if(parse[0].equals("1") || (parse[0].equals("2") && Double.parseDouble(parse[1]) <= 30)){
            return "time2";
        }else{
            return "time3";
        }
    }

}
