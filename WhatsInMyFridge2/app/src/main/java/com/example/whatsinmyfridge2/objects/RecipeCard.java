package com.example.whatsinmyfridge2.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RecipeCard implements Parcelable {
    private String mImageResource;
    private String recipeName;
    private String timeToCook;
    private String difficulty;
    private int xPeople;
    ArrayList<com.example.whatsinmyfridge2.objects.Item> ingredients;

    public RecipeCard(String image, String name, String time, String diff, int x, ArrayList<com.example.whatsinmyfridge2.objects.Item> ing){
        mImageResource = image;
        recipeName = name;
        timeToCook = time;
        difficulty = diff;
        xPeople = x;
        ingredients = ing;
    }


    protected RecipeCard(Parcel in) {
        mImageResource = in.readString();
        recipeName = in.readString();
        timeToCook = in.readString();
        difficulty = in.readString();
        xPeople = in.readInt();
        if (in.readByte() == 0x01) {
            ingredients = in.readArrayList(Item.class.getClassLoader());
        } else {
            ingredients = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImageResource);
        dest.writeString(recipeName);
        dest.writeString(timeToCook);
        dest.writeString(difficulty);
        dest.writeInt(xPeople);
        if (ingredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredients);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecipeCard> CREATOR = new Parcelable.Creator<RecipeCard>() {
        @Override
        public RecipeCard createFromParcel(Parcel in) {
            return new RecipeCard(in);
        }

        @Override
        public RecipeCard[] newArray(int size) {
            return new RecipeCard[size];
        }
    };
    public String getmImageResource() {
        return mImageResource;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public ArrayList<com.example.whatsinmyfridge2.objects.Item> getIngredients() {
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
