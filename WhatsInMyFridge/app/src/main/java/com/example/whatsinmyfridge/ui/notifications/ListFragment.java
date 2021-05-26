package com.example.whatsinmyfridge.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsinmyfridge.R;
import com.example.whatsinmyfridge.objects.ListCard;
import com.example.whatsinmyfridge.objects.RecipeCard;
import com.example.whatsinmyfridge.ui.recipe.RecipeAdapter;
import com.example.whatsinmyfridge.ui.recipe.RecipeViewModel;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ListViewModel listViewModel;
    private RecyclerView lRecyclerView;
    private RecyclerView.Adapter lAdapter;
    private RecyclerView.LayoutManager lLayoutManager;
    ArrayList<ListCard> listCards = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listViewModel =
                new ViewModelProvider(this).get(ListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        final TextView textView = root.findViewById(R.id.text);

        listCards.add(new ListCard(R.drawable.arroz,"Arroz","Em gramas"));
        listCards.add(new ListCard(R.drawable.azeite,"Azeite","Em ml"));
        listCards.add(new ListCard(R.drawable.batatas,"Batatas","Em gramas"));
        listCards.add(new ListCard(R.drawable.cebola,"Cebola","Em gramas"));
        listCards.add(new ListCard(R.drawable.ketchup,"Ketchup","Em ml"));
        listCards.add(new ListCard(R.drawable.massa,"Massa","Em gramas"));
        listCards.add(new ListCard(R.drawable.mayo,"Mayo","Em gramas"));
        listCards.add(new ListCard(R.drawable.ovos,"Ovos","Em gramas"));
        listCards.add(new ListCard(R.drawable.pimenta,"Pimenta","Em gramas"));
        listCards.add(new ListCard(R.drawable.tomate,"Tomate","Em gramas"));
        listCards.add(new ListCard(R.drawable.vaca,"Bife de Vaca","Em gramas"));


        lRecyclerView = root.findViewById(R.id.recyclerView);
        lLayoutManager = new GridLayoutManager(getContext(), 2);
        lAdapter = new ListAdapter(listCards);

        lRecyclerView.setLayoutManager(lLayoutManager);
        lRecyclerView.setAdapter(lAdapter);

        return root;

    }
    @Override
    public void onCardClick(int position) {
        listCards.get(position);
        Toast.makeText(this.getContext(), "Hello " + listCards, Toast.LENGTH_LONG).show();
    }
}