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
import com.example.whatsinmyfridge2.objects.Type;

import java.util.ArrayList;

public class FridgeFragment extends Fragment implements ItemRecViewAdapter.OnCardListener {

    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;
    private Double updatedWeight;
    private View root;
    private ItemRecViewAdapter itemRecViewAdapter;
    // add a callback and check if Main implemented this intterfacae


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_fridge, container, false);
        relativeLayout = root.findViewById(R.id.parentFridge);
        recyclerView = relativeLayout.findViewById(R.id.ItemRecycler);

        constraintLayout = relativeLayout.findViewById(R.id.expandable_Layout);

        // Set on background click leave
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setVisibility(View.GONE);

            }
        });


        Fridge.addItem(new Item("Bife de Vaca", 1000, 2, 1, "CARNE", getString(R.string.skyImg)));
        Fridge.addItem(new Item("Bife de Frango", 1001, 4, 1, "CARNE", getString(R.string.skyImg)));
        Fridge.addItem(new Item("Arroz", 809, 5, 1, "CARNE", getString(R.string.skyImg)));
        Fridge.addItem(new Item("Massa", 1080, 9, 2, "Massa", getString(R.string.skyImg)));
        Fridge.addItem(new Item("Chocolate", 10, 1, 1, "Sobremesa", getString(R.string.skyImg)));


        //
        itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this, R.layout.item_card);
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
        Button btn = (Button) constraintLayout.findViewById(R.id.SaveBtn);
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
