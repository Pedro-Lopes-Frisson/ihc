package com.example.whatsinmyfridge.ui.recipe;

public class RecipeCard {
    private int mImageResource;
    private String mText1;

    public RecipeCard(int image, String text1){
        mImageResource = image;
        mText1 = text1;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmText1() {
        return mText1;
    }
}
