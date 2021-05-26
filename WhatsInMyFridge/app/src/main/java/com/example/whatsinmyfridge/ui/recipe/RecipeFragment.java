package com.example.whatsinmyfridge.ui.recipe;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsinmyfridge.MainActivity;
import com.example.whatsinmyfridge.ObjectDeclaration.Item;
import com.example.whatsinmyfridge.R;
import com.example.whatsinmyfridge.objects.RecipeCard;
import com.example.whatsinmyfridge.ui.home.FridgeViewModel;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {

    private RecipeViewModel recipeViewModel;
    private FridgeViewModel fridgeViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Item> itemInsideFridge;
    private Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipeViewModel =
                new ViewModelProvider(this).get(RecipeViewModel.class);

        fridgeViewModel = new ViewModelProvider((requireActivity())).get(FridgeViewModel.class);
        if(fridgeViewModel == null){
            Log.i(MainActivity.RECIPETAG, "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
            System.exit(-1);
        }

        View root = inflater.inflate(R.layout.fragment_recipe, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);


        String androidIsCrap = "Update my items inside my fridge are";
        Log.i(MainActivity.RECIPETAG, "Update my items inside my fridge are" +
                fridgeViewModel.getItemsInsideFridge());

        itemInsideFridge = fridgeViewModel.getItemsInsideFridge().getValue();


        Toast.makeText(getContext(), MainActivity.RECIPETAG + androidIsCrap +
                itemInsideFridge, Toast.LENGTH_LONG).show();

        ArrayList<RecipeCard> recipeCards = new ArrayList<>();
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice2")); //Falta arranjar as receitas
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice3"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice4"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice5"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice6"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice7"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice8"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice9"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice10"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice11"));
        recipeCards.add(new RecipeCard(R.drawable.beef_com_arroz, "Beef with rice12"));

        mRecyclerView = root.findViewById(R.id.recyclerView);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mAdapter = new RecipeAdapter(recipeCards);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        button = root.findViewById(R.id.btn_recipe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(MainActivity.RECIPETAG, "got this with a get bundle" + savedInstanceState.getParcelableArrayList("ItemsInsideFridge"));
                Log.i(MainActivity.RECIPETAG, "Got this items stored: " + itemInsideFridge);
            }
        });

        return root;
    }

    // Secalhar e melhor usar algo mais geral mas so far so good
    public void storeItems(ArrayList<Item> itemInsideFridge) {
        this.itemInsideFridge.addAll(itemInsideFridge);
        Log.i(MainActivity.RECIPETAG, "Data received and Stored:\n" + this.itemInsideFridge);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}