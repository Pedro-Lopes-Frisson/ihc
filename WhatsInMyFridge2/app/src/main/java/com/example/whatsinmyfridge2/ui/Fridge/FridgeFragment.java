package com.example.whatsinmyfridge2.ui.Fridge;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Fridge;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.ItemRecViewAdapter;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class FridgeFragment extends Fragment implements ItemRecViewAdapter.OnCardListener {

    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewInsert;
    private ConstraintLayout constraintLayout;
    private ConstraintLayout constraintLayoutInsert;
    private Double updatedWeight;
    private View root;
    private ItemRecViewAdapter itemRecViewAdapter;
    private ItemRecViewAdapter itemRecViewAdapterToinsert;
    private ImageButton btnAdd;
    private FridgeFragment self;
    private boolean flagInsert = true; // true means that i am not trying to insert in the fridge items
    // add a callback and check if Main implemented this intterfacae


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_fridge, container, false);
        relativeLayout = root.findViewById(R.id.parentFridge);
        recyclerView = relativeLayout.findViewById(R.id.ItemRecycler);

        constraintLayout = relativeLayout.findViewById(R.id.expandable_Layout);
        constraintLayoutInsert = relativeLayout.findViewById(R.id.expandable_Layout2);
        recyclerViewInsert = relativeLayout.findViewById(R.id.expandable_Layout2).findViewById(R.id.recyclerView);

        // Set on background click leave
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setVisibility(View.GONE);
            }
        });
        constraintLayoutInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on click gone
                constraintLayoutInsert.setVisibility(View.GONE);
                flagInsert = true; // we are not one the insertion part so this has to be gtrue
            }
        });

        Fridge.addItem(new Item("Steak", 1000, 2, 1, "Meat", getString(R.string.chickenBreast)));
        Fridge.addItem(new Item("Chicken Breast", 1001, 4, 1, "Meat", getString(R.string.beefImg)));
        Fridge.addItem(new Item("White Rice", 1002, 5, 1, "Cereal", getString(R.string.rice)));
        Fridge.addItem(new Item("Spaghetti", 1003, 0.5, 2, "Pasta", getString(R.string.spaghetti)));
        Fridge.addItem(new Item("Chocolate", 1004, 1, 1, "Dessert", getString(R.string.chocolate)));


        //
        itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this, R.layout.item_card);
        itemRecViewAdapter.setItems(Fridge.getItems());
        self = this;

        recyclerView.setAdapter(itemRecViewAdapter);
        GridLayoutManager a = new GridLayoutManager(root.getContext(), 2);
        recyclerView.setLayoutManager(a);
        // Add a new item listener
        btnAdd = (ImageButton) relativeLayout.findViewById(R.id.AddItem);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                flagInsert = false;
                itemRecViewAdapterToinsert = new ItemRecViewAdapter(root.getContext(), self, R.layout.item_line);
                ArrayList<Item> itemsThatAreNotOnTheFridge = new ArrayList<>();
                ArrayList<Item> itemsEvery = Fridge.getDatabaseOfItems();

                for (int i = 0; i < Fridge.getDatabaseOfItems().size(); i++) {
                    if (!Fridge.getItems().contains(itemsEvery.get(i))) {
                        itemsThatAreNotOnTheFridge.add(itemsEvery.get(i));
                    }
                }
                itemRecViewAdapterToinsert.setItems(Fridge.getDatabaseOfItems());

                recyclerViewInsert.setAdapter(itemRecViewAdapterToinsert);
                LinearLayoutManager b = new LinearLayoutManager(root.getContext());
                recyclerViewInsert.setLayoutManager(b);
                constraintLayoutInsert.setElevation(1234);
                constraintLayoutInsert.setVisibility(View.VISIBLE);
            }
        });


        return root;
    }

    // click and get the item
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCardClick(int position) {
        Item selected;
        if (flagInsert) {
            selected = Fridge.getItem(position);

        } else {
            selected = itemRecViewAdapterToinsert.getItems().get(position);
            Fridge.addItem(selected);
            itemRecViewAdapter.setItems(Fridge.getItems());
            itemRecViewAdapter.notifyDataSetChanged();
            constraintLayoutInsert.setVisibility(View.GONE);
        }

        TextView tview = (TextView) constraintLayout.findViewById(R.id.item_title);
        updatedWeight = selected.getWeight();
        ImageButton incButton = (ImageButton) constraintLayout.findViewById(R.id.incBtn);
        ImageButton decButton = (ImageButton) constraintLayout.findViewById(R.id.decBtn);
        ImageButton closeBttn = (ImageButton) constraintLayout.findViewById(R.id.closeBtn);


        // set spinner
        Spinner spinner = (Spinner) constraintLayout.findViewById(R.id.SpinnerQuantity);
        ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(getContext(), android.R.layout.simple_spinner_item, selected.getJumps());
        spinner.setAdapter(adapter);

        // spinner listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatedWeight = (Double) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // SAVE Button
        Button btn = (Button) constraintLayout.findViewById(R.id.ApplyBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected.setWeight(updatedWeight);
                constraintLayout.setVisibility(View.GONE);
                itemRecViewAdapter.notifyItemChanged(position);// Notify recycler view that ammount changed
            }
        });
        // close Btn
        closeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setVisibility(View.GONE);
            }
        });

        incButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedWeight += selected.getWeightJump();
                adapter.clear();
                adapter.addAll(selected.getJumps(updatedWeight));
                adapter.notifyDataSetChanged();
            }
        });
        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedWeight -= selected.getWeightJump();
                adapter.clear();
                adapter.addAll(selected.getJumps(updatedWeight));
                adapter.notifyDataSetChanged();
            }
        });

        //set img
        Glide.with(getContext()).asBitmap().load(selected.getImage()).into((ImageView) constraintLayout.findViewById(R.id.item_img));
        //set title
        tview.setText(selected.getName());
        // elevate Card
        constraintLayout.setElevation(1234);
        // Show card
        constraintLayout.setVisibility(View.VISIBLE);
    }

    public interface TransferData {
        public void sendItems(ArrayList<Item> itemsInsideFridge);
    }
}
