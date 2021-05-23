package com.example.whatsinmyfridge.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.proto.ProtoOutputStream;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    ArrayList<Item> items = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fridgeViewModel =
                new ViewModelProvider(this).get(FridgeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_fridge, container, false);

        final TextView textView = root.findViewById(R.id.text_Fridge);

        fridgeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        relativeLayout = root.findViewById(R.id.parentFridge);
        recyclerView = relativeLayout.findViewById(R.id.ItemRecycler);

        items.add(new Item("Bife de Vaca", 1000, 2, 1, "Carne", getString(R.string.skyImg)));
        items.add(new Item("Bife de Frango", 1001, 4, 1, "Carne", getString(R.string.skyImg)));
        items.add(new Item("Arroz", 809, 5, 1, "Cereal", getString(R.string.skyImg)));
        items.add(new Item("Massa", 1080, 9, 2, "Massa", getString(R.string.skyImg)));
        items.add(new Item("Chocolate", 10, 1, 1, "Sobremesa", getString(R.string.skyImg)));

        //

        ItemRecViewAdapter itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this);
        itemRecViewAdapter.setItems(items);

        recyclerView.setAdapter(itemRecViewAdapter);
        GridLayoutManager a = new GridLayoutManager(root.getContext(), 2);
        recyclerView.setLayoutManager(a);


        return root;
    }

    @Override
    public void onCardClick(int position) {
        items.get(position);
        Toast.makeText(this.getContext(), "Hello " + items, Toast.LENGTH_LONG).show();
    }
}