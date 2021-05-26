package com.example.whatsinmyfridge.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsinmyfridge.MainActivity;
import com.example.whatsinmyfridge.ObjectDeclaration.Item;
import com.example.whatsinmyfridge.ObjectDeclaration.ItemRecViewAdapter;
import com.example.whatsinmyfridge.R;

import java.util.ArrayList;

public class FridgeFragment extends Fragment implements ItemRecViewAdapter.OnCardListener {

    private FridgeViewModel fridgeViewModel;
    private RelativeLayout relativeLayout;     // parent layout
    private RecyclerView recyclerView;         // recycler view for data to be displayed on
    private TransferData mCallback;           // store send method
    private Button btn;
    private ArrayList<Item> items = new ArrayList<>();


    public interface TransferData {
        public ArrayList<Item> sendItems(ArrayList<Item> itemsInsideFridge);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fridgeViewModel =
                new ViewModelProvider(this).get(FridgeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_fridge, container, false);

        final TextView textView = root.findViewById(R.id.text_Fridge); // Set the title

        fridgeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            } // set the title
        });

        btn = (Button) root.findViewById(R.id.btn); // get the btn
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.sendItems(items); // hopeffully send the data to recipe fragment
            }
        });
        relativeLayout = root.findViewById(R.id.parentFridge);   // sget the relative layout
        recyclerView = relativeLayout.findViewById(R.id.ItemRecycler);  // get the recycler view

        // add some item
        // TODO add a certain7
        items.add(new Item("Bife de Vaca", 1000, 2, 1, "Carne", getString(R.string.skyImg)));
        items.add(new Item("Bife de Frango", 1001, 4, 1, "Carne", getString(R.string.skyImg)));
        items.add(new Item("Arroz", 809, 5, 1, "Cereal", getString(R.string.skyImg)));
        items.add(new Item("Massa", 1080, 9, 2, "Massa", getString(R.string.skyImg)));
        items.add(new Item("Chocolate", 10, 1, 1, "Sobremesa", getString(R.string.skyImg)));
        // Set Mutable data inside the fridge view model
        fridgeViewModel.setItemsInsideFridge(items);

        // set up the items view
        ItemRecViewAdapter itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this);
        itemRecViewAdapter.setItems(items);

        recyclerView.setAdapter(itemRecViewAdapter);
        GridLayoutManager a = new GridLayoutManager(root.getContext(), 2);
        recyclerView.setLayoutManager(a);
        String i = "My tag is: ->" + this.getTag() + " Root tag -> " + root.getTag();
        Log.w(MainActivity.FRIDGETAG, i);
        // return Layout
        return root;
    }

    // make sure no one forgot to implement this interface on the main activity
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FridgeFragment.TransferData) {
            mCallback = (TransferData) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement TransferData");
        }
    }

    // click and get the item
    @Override
    public void onCardClick(int position) {
        items.get(position);
        Toast.makeText(this.getContext(), "Hello " + items, Toast.LENGTH_LONG).show();
    }

    // on detach removes mCallback security era cp paste
    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}