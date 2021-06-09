package com.example.whatsinmyfridge2.ui.Cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Fridge;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.ItemRecViewAdapter;

import java.util.ArrayList;

public class CartFragment extends Fragment implements ItemRecViewAdapter.OnCardListener {
    private ConstraintLayout relativeLayout;
    private RecyclerView recyclerView;
    private ItemRecViewAdapter itemRecViewAdapter;
    private Button btnUpdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        relativeLayout = root.findViewById(R.id.cartParent);
        recyclerView = relativeLayout.findViewById(R.id.recyclerView);
        itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this, R.layout.item_line_with_checked);
        ArrayList<Item> arrayListItem = Fridge.getCartItems();
        arrayListItem.removeAll(Fridge.getItems());
        itemRecViewAdapter.setItems(arrayListItem);
        itemRecViewAdapter.notifyDataSetChanged();

        recyclerView.setAdapter(itemRecViewAdapter);
        LinearLayoutManager a = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(a);
        // falta adicionar um botao e esta feito
        btnUpdate = root.findViewById(R.id.UpdateFridge);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Item> itensss = itemRecViewAdapter.getItems();
                ArrayList<Integer> integerArrayList = new ArrayList<>();
                for (int i = 0; i < itensss.size(); i++){
                    if(itensss.get(i).isChecked()){
                        Fridge.addItem(itensss.get(i));
                        integerArrayList.add(i);
                        // remover do adapter
                    }
                }
                ArrayList<Item> arrayListItem = Fridge.getCartItems();
                arrayListItem.removeAll(Fridge.getItems());
                itemRecViewAdapter.setItems(arrayListItem);
                itemRecViewAdapter.notifyDataSetChanged();
                Toast.makeText(root.getContext(), " "+integerArrayList, Toast.LENGTH_SHORT).show();
                itemRecViewAdapter.notifyDataSetChanged();
            }
        });



        return root;
    }


    @Override
    public void onCardClick(int position) {
        Fridge.cartItems.get(position).setChecked(!Fridge.cartItems.get(position).isChecked());
        itemRecViewAdapter.notifyDataSetChanged();
    }
}
