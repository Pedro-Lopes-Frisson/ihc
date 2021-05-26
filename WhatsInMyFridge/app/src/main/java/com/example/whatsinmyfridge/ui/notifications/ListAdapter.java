package com.example.whatsinmyfridge.ui.notifications;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsinmyfridge.R;
import com.example.whatsinmyfridge.objects.ListCard;
import com.example.whatsinmyfridge.objects.RecipeCard;
import com.example.whatsinmyfridge.ui.recipe.RecipeAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private ArrayList<ListCard> lListCards;
    Dialog listDialog;
    public static class ListViewHolder extends RecyclerView.ViewHolder {

        public ImageView lImageView;
        public TextView lTextView1;
        public TextView lTextView2;
        public CardView dialog_item; //Provavelmente errado

        public ListViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            dialog_item = (CardView) itemView.findViewById(R.id.fragListID);
            lImageView = itemView.findViewById(R.id.listimageView);
            lTextView1 = itemView.findViewById(R.id.listtextView1);
            lTextView2 = itemView.findViewById(R.id.listtextView2);

        }
    }

    public ListAdapter(ArrayList<ListCard> listCards){ lListCards = listCards;
    }

    @NonNull
    @NotNull
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card, parent, false);
        ListAdapter.ListViewHolder rvh = new ListAdapter.ListViewHolder(v);

        /*rvh.dialog_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(),"Test Click"+String.valueOf(rvh.getBindingAdapterPosition()),Toast.LENGTH_SHORT );
            }
        });*/



        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListAdapter.ListViewHolder holder, int position) {
        ListCard currentCard = lListCards.get(position);
        holder.lImageView.setImageResource(currentCard.getlImageResource());
        holder.lTextView1.setText(currentCard.getlText1());
        holder.lTextView2.setText(currentCard.getlText2());
    }

    @Override
    public int getItemCount() {
        return lListCards.size();
    }
}

