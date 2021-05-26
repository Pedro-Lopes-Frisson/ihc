package com.example.whatsinmyfridge.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsinmyfridge.ObjectDeclaration.Item;

import java.util.ArrayList;

public class FridgeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Item>> itemsInsideFridge = new MutableLiveData<ArrayList<Item>>();

    public MutableLiveData<ArrayList<Item>> getItemsInsideFridge() {
        return itemsInsideFridge;
    }

    public void setItemsInsideFridge(ArrayList<Item> itemsInsideFridge) {
        this.itemsInsideFridge.setValue(itemsInsideFridge);
    }

    public FridgeViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}