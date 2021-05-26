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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.widget.Toast;

import com.example.whatsinmyfridge2.R;
import com.example.whatsinmyfridge2.objects.Item;
import com.example.whatsinmyfridge2.objects.ItemRecViewAdapter;

import java.util.ArrayList;

public class ShoppingFragment extends Fragment  implements ItemRecViewAdapter.OnCardListener  {
    private RecyclerView lRecyclerView;
    private RecyclerView.Adapter lAdapter;
    private RecyclerView.LayoutManager lLayoutManager;
    ArrayList<Item> listCards = new ArrayList<>(); // Usar o que ja esta feito

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list,container,false);


        Bundle bundle = getArguments(); // PAra ver se ainda funciona
        listCards = bundle.getParcelableArrayList("itemsInsideFridge"); //OI
        Log.i("SHOPPING", ""+ listCards);

        lRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        //lLayoutManager = new LinearLayoutManager(getContext());
        lLayoutManager = new GridLayoutManager(getContext(), 1);
        ItemRecViewAdapter itemRecViewAdapter = new ItemRecViewAdapter(root.getContext(), this, R.layout.item_line);
        itemRecViewAdapter.setItems(listCards); // Faltava isto

        lRecyclerView.setLayoutManager(lLayoutManager);
        lRecyclerView.setAdapter(itemRecViewAdapter);

        return root;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCardClick(int position) {
        listCards.get(position);
        Toast.makeText(this.getContext(), "Hello " + listCards, Toast.LENGTH_LONG).show();
    }
}
