package com.example.whatsinmyfridge.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.whatsinmyfridge.ObjectDeclaration.Item;

import java.util.ArrayList;

public class RecipeCard implements Parcelable {
    private int mImageResource;
    private String recipeName;
    private String timeToCook;
    private String difficulty;
    private int xPeople;
    ArrayList<Item> ingredients;

    public RecipeCard(int image, String name, String time, String diff, int x, ArrayList<Item> ing){
        mImageResource = image;
        recipeName = name;
        timeToCook = time;
        difficulty = diff;
        xPeople = x;
        ingredients = ing;
    }

    protected RecipeCard(Parcel in) {
        mImageResource = in.readInt();
        recipeName = in.readString();
        timeToCook = in.readString();
        difficulty = in.readString();
        xPeople = in.readInt();
        ingredients = in.readArrayList(null);
    }

    public static final Creator<RecipeCard> CREATOR = new Creator<RecipeCard>() {
        @Override
        public RecipeCard createFromParcel(Parcel in) {
            return new RecipeCard(in);
        }

        @Override
        public RecipeCard[] newArray(int size) {
            return new RecipeCard[size];
        }
    };

    public int getmImageResource() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResource);
        dest.writeString(recipeName);
        dest.writeString(timeToCook);
        dest.writeString(difficulty);
        dest.writeInt(xPeople);
        dest.writeList(ingredients);

    }
}
