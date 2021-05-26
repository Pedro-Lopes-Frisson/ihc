package com.example.whatsinmyfridge2.ui.Recipe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Fragment;

import com.example.whatsinmyfridge2.MainActivity;
import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Item;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {
    private ArrayList<Item> items;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fridge, container, false);
        Bundle bundle = getArguments();
        Log.i("Recipe", "" + bundle.getParcelableArrayList("itemsInsideFridge"));
        items = bundle.getParcelableArrayList("itemsInsideFridge");
        items.remove(1);


        return root;
    }
}
