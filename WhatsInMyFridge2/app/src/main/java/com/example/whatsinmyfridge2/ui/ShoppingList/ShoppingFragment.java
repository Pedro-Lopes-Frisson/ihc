package com.example.whatsinmyfridge2.ui.ShoppingList;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Fridge;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.ItemRecViewAdapter;
import com.example.whatsinmyfridge2.ui.Fridge.FridgeFragment;

import java.util.ArrayList;

public class ShoppingFragment extends Fragment  implements ItemRecViewAdapter.OnCardListener  {
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
    private boolean flagInsert = false; // me not inserting new items


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_list, container, false);
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


        //
        itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this, R.layout.item_card);
        itemRecViewAdapter.setItems(Fridge.getItems());

        recyclerView.setAdapter(itemRecViewAdapter);
        GridLayoutManager a = new GridLayoutManager(root.getContext(), 2);
        recyclerView.setLayoutManager(a);


        // Add a new item listener
        btnAdd = (ImageButton) relativeLayout.findViewById(R.id.AddItem);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                itemRecViewAdapterToinsert = new ItemRecViewAdapter(root.getContext(), self, R.layout.item_line);
                ArrayList<Item> itemsThatAreNotOnTheFridge = Fridge.getDatabaseOfItems();
                itemsThatAreNotOnTheFridge.removeAll(Fridge.getItems());

                itemRecViewAdapterToinsert.setItems(itemsThatAreNotOnTheFridge);
                recyclerViewInsert.setAdapter(itemRecViewAdapterToinsert);
                itemRecViewAdapterToinsert.notifyDataSetChanged();
                LinearLayoutManager b = new LinearLayoutManager(root.getContext());
                recyclerViewInsert.setLayoutManager(b);
                constraintLayoutInsert.setElevation(1234);
                constraintLayoutInsert.setVisibility(View.VISIBLE);
                flagInsert = true; // I am inserting
            }
        });


        return root;

    }


     // Igual ao do frigorifico, tinha feito de outra forma.
    // click and get the item
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCardClick(int position) {
        Item selected;
        if (!flagInsert) {
            // not adding
            selected = Fridge.getItem(position);

        } else {
            selected = itemRecViewAdapterToinsert.getItems().get(position);
            itemRecViewAdapter.setItems(Fridge.getItems());
            itemRecViewAdapter.notifyDataSetChanged();
            constraintLayoutInsert.setVisibility(View.GONE);
            flagInsert = false;
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
        Button applyBtn = (Button) constraintLayout.findViewById(R.id.ApplyBtn);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected.setWeight(updatedWeight);
                constraintLayout.setVisibility(View.GONE);
                if (selected.getWeight() != 0) {
                    Fridge.addItem(selected);
                    itemRecViewAdapter.setItems(Fridge.getItems());
                    itemRecViewAdapter.notifyDataSetChanged();
                    itemRecViewAdapter.notifyItemChanged(position);// Notify recycler view that ammount changed
                }
            }
        });
        // close Btn
        closeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setVisibility(View.GONE);
                flagInsert = false;
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
    /*
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list,container,false);



        Log.i("SHOPPING", ""+ listCards);

        lRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        //lLayoutManager = new LinearLayoutManager(getContext());
        lLayoutManager = new GridLayoutManager(getContext(), 1);
        ItemRecViewAdapter itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this, R.layout.item_line);
        listCards = Fridge.getDatabaseOfItems();
        itemRecViewAdapter.setItems(listCards);

        lRecyclerView.setLayoutManager(lLayoutManager);
        lRecyclerView.setAdapter(itemRecViewAdapter);







        return root;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCardClick(int position) {
        listCards.get(position);
        Toast.makeText(this.getContext(), "Hello " + listCards, Toast.LENGTH_LONG).show();
    }*/
}
