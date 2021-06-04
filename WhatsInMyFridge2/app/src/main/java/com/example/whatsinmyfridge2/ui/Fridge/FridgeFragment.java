package com.example.whatsinmyfridge2.ui.Fridge;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Fridge;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.ItemRecViewAdapter;
import com.example.whatsinmyfridge2.objects.Type;

import java.util.ArrayList;

public class FridgeFragment extends Fragment implements ItemRecViewAdapter.OnCardListener {

    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;
    // add a callback and check if Main implemented this intterfacae


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fridge, container, false);

        relativeLayout = root.findViewById(R.id.parentFridge);
        recyclerView = relativeLayout.findViewById(R.id.ItemRecycler);

        constraintLayout = relativeLayout.findViewById(R.id.expandable_Layout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setVisibility(View.GONE);

            }
        });


        Fridge.addItem(new Item("Steak", 1000, 2, 1, "Meat", getString(R.string.skyImg)));
        Fridge.addItem(new Item("Chicken Breast", 1001, 4, 1, "Meat", getString(R.string.skyImg)));
        Fridge.addItem(new Item("White Rice", 1002, 5, 1, "Cereal", getString(R.string.skyImg)));
        Fridge.addItem(new Item("Spaghetti", 1003, 9, 2, "Pasta", getString(R.string.skyImg)));
        Fridge.addItem(new Item("Chocolate", 1004, 1, 1, "Dessert", getString(R.string.skyImg)));


        //
        ItemRecViewAdapter itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this, R.layout.item_card);
        itemRecViewAdapter.setItems(Fridge.getItems());

        recyclerView.setAdapter(itemRecViewAdapter);
        GridLayoutManager a = new GridLayoutManager(root.getContext(), 2);
        recyclerView.setLayoutManager(a);

        return root;
    }

    // click and get the item
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCardClick(int position) {
        Item selected = Fridge.getItem(position);
        TextView tview = (TextView) constraintLayout.findViewById(R.id.item_title);
        tview.setText(selected.getName());
        constraintLayout.setElevation(1234);
        constraintLayout.setVisibility(View.VISIBLE);
    }

    public interface TransferData {
        public void sendItems(ArrayList<Item> itemsInsideFridge);
    }
}
