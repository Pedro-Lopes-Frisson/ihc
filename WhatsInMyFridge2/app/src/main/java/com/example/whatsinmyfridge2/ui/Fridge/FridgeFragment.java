package com.example.whatsinmyfridge2.ui.Fridge;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.ItemRecViewAdapter;
import com.example.whatsinmyfridge2.objects.Type;

import java.util.ArrayList;

public class FridgeFragment extends Fragment implements ItemRecViewAdapter.OnCardListener {

    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    ArrayList<Item> items = new ArrayList<>();
    // add a callback and check if Main implemented this intterfacae


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fridge,container,false);

        relativeLayout = root.findViewById(R.id.parentFridge);
        recyclerView = relativeLayout.findViewById(R.id.ItemRecycler);

        items.add(new Item("Bife de Vaca", 1000, 2, 1, Type.CARNE, getString(R.string.skyImg)));
        items.add(new Item("Bife de Frango", 1001, 4, 1, Type.CARNE, getString(R.string.skyImg)));
        items.add(new Item("Arroz", 809, 5, 1, Type.CARNE, getString(R.string.skyImg)));
        //items.add(new Item("Massa", 1080, 9, 2, "Massa", getString(R.string.skyImg)));
        // items.add(new Item("Chocolate", 10, 1, 1, "Sobremesa", getString(R.string.skyImg)));


        //
        ItemRecViewAdapter itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this, R.layout.item_card);
        itemRecViewAdapter.setItems(items);

        recyclerView.setAdapter(itemRecViewAdapter);
        GridLayoutManager a = new GridLayoutManager(root.getContext(), 2);
        recyclerView.setLayoutManager(a);

        return root;
    }

    // click and get the item
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCardClick(int position) {
        items.get(position);
        Toast.makeText(this.getContext(), "Hello " + items, Toast.LENGTH_LONG).show();
    }

    public interface TransferData {
        public void sendItems(ArrayList<Item> itemsInsideFridge);
    }
}
