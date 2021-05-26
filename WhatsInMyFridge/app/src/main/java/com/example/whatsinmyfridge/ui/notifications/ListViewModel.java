package com.example.whatsinmyfridge.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListViewModel extends ViewModel {

    private MutableLiveData<String> lText;

    public ListViewModel() {
        lText = new MutableLiveData<>();
        lText.setValue("This is Shoppning List fragment");
    }

    public LiveData<String> getText() {
        return lText;
    }
}

