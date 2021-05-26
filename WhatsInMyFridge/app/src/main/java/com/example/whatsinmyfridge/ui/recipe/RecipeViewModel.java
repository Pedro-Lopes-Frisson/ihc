package com.example.whatsinmyfridge.ui.recipe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsinmyfridge.ObjectDeclaration.Item;

public class RecipeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    //private MutableLiveData<Item> items;

    public RecipeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}