package com.example.whatsinmyfridge.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.whatsinmyfridge.R;

public class FridgeFragment extends Fragment {

    private FridgeViewModel fridgeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fridgeViewModel =
                new ViewModelProvider(this).get(FridgeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_fridge, container, false);

        final TextView textView = root.findViewById(R.id.text_dashboard12);

        fridgeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        View line1 = root.findViewById(R.id.l1);
        View line2 = root.findViewById(R.id.l2);
        View line3 = root.findViewById(R.id.l3);

        TextView t1 = line1.findViewById(R.id.editTextTextPersonName);
        TextView t2 = line2.findViewById(R.id.editTextTextPersonName);
        TextView t3 = line3.findViewById(R.id.editTextTextPersonName);

        t1.setText("Cereais");
        t2.setText("Carne");
        t3.setText("Peixe");


        TextView label1 = line1.findViewById(R.id.label1); //first label from first line
        TextView label2 = line1.findViewById(R.id.label2);

        TextView label3 = line2.findViewById(R.id.label1); //first label from first line
        TextView label4 = line2.findViewById(R.id.label2);

        PopupMenu myPopup = new PopupMenu(this.getContext(), root);
        PopupWindow mp = new PopupWindow();
        mp.setHeight(200);
        mp.setWidth(200);


        label1.setText("Chocapics");
        label2.setText("Estrelitas");
        label3.setText("Bife de vaca");
        label4.setText("Bife de frango");

        label1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPopup.show();
            }
        });

        View cardView = line1.findViewById(R.id.card0);
        cardView.findViewById(R.id.card0).findViewById(R.id.img1).setOnClickListener(v -> {
            t2.setText("App goes BRrrr");
            myPopup.show();
            mp.showAtLocation(root.findViewById(R.id.fridge), 20, 20, 20);
        });

        return root;
    }
}