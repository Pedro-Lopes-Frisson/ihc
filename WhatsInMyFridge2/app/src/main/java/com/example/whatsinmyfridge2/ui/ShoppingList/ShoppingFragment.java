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
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Fridge;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.ItemRecViewAdapter;

import java.util.ArrayList;

public class ShoppingFragment extends Fragment implements ItemRecViewAdapter.OnCardListener {
    private View root;
    private RecyclerView lRecyclerView;
    private RecyclerView recyclerViewInsert;
    private ConstraintLayout constraintLayout;
    private ItemRecViewAdapter itemRecViewAdapter;
    private RecyclerView.Adapter lAdapter;
    private RecyclerView.LayoutManager lLayoutManager;
    private Double updatedWeight;
    private SearchView searchView;
    ArrayList<Item> listCards = new ArrayList<>(); // Usar o que ja esta feito

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_list, container, false);


        Log.i("SHOPPING", "" + listCards);

        lRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        lLayoutManager = new GridLayoutManager(getContext(), 1);
        ItemRecViewAdapter itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this, R.layout.item_line_no_qty);
        listCards = Fridge.getDatabaseOfItems();
        itemRecViewAdapter.setItems(listCards);
        lRecyclerView.setLayoutManager(lLayoutManager);
        lRecyclerView.setAdapter(itemRecViewAdapter);
        constraintLayout = root.findViewById(R.id.expandable_Layout);
        searchView = root.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                itemRecViewAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCardClick(int position) {
        searchView.clearFocus();
        Toast.makeText(root.getContext(), "this daf",Toast.LENGTH_LONG);
        Item selected = null;
        try {
            selected = (Item) listCards.get(position).clone();
            Toast.makeText(root.getContext(), "this daf",Toast.LENGTH_LONG);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            Toast.makeText(root.getContext(), "this daf",Toast.LENGTH_LONG);
        }
        Item finalSelected = selected;
        Toast.makeText(root.getContext(),"this : A " +finalSelected,Toast.LENGTH_LONG);
        TextView tview = (TextView) constraintLayout.findViewById(R.id.item_title);
        updatedWeight = selected.getWeight();
        ImageButton incButton = (ImageButton) constraintLayout.findViewById(R.id.incBtn);
        ImageButton decButton = (ImageButton) constraintLayout.findViewById(R.id.decBtn);
        ImageButton closeBttn = (ImageButton) constraintLayout.findViewById(R.id.closeBtn);


        // set spinner
        Spinner spinner = (Spinner) constraintLayout.findViewById(R.id.SpinnerQuantity);
        ArrayAdapter<Double> adapter = new ArrayAdapter<Double>(root.getContext(), android.R.layout.simple_spinner_item, selected.getJumps());
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
                finalSelected.setWeight(updatedWeight);
                constraintLayout.setVisibility(View.GONE);
                if (finalSelected.getWeight() != 0) {
                    Toast.makeText(root.getContext(),"this : " + finalSelected,Toast.LENGTH_LONG);
                    Fridge.addItemToCart(finalSelected);
                }
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
                updatedWeight += finalSelected.getWeightJump();
                adapter.clear();
                adapter.addAll(finalSelected.getJumps(updatedWeight));
                adapter.notifyDataSetChanged();
            }
        });
        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedWeight -= finalSelected.getWeightJump();
                adapter.clear();
                adapter.addAll(finalSelected.getJumps(updatedWeight));
                adapter.notifyDataSetChanged();
            }
        });

        //set img
        Glide.with(root.getContext()).asBitmap().load(finalSelected.getImage()).into((ImageView) constraintLayout.findViewById(R.id.item_img));
        //set title
        //tview.setText(finalSelected.getName());
        // elevate Card
        constraintLayout.setElevation(1234);
        // Show card
        constraintLayout.setVisibility(View.VISIBLE);

    }
}
