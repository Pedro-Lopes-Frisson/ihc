package com.example.whatsinmyfridge2.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsinmyfridge2.R;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ItemRecViewAdapter extends RecyclerView.Adapter<ItemRecViewAdapter.ViewHolder> {
    private ArrayList<Item> items = new ArrayList<>();
    private Context context;
    private OnCardListener onCardListener;
    private int layoutToUse;

    public ItemRecViewAdapter(Context context, OnCardListener onCardListener, int layoutToUse) {
        this.onCardListener = onCardListener;
        this.context = context;
        this.layoutToUse = layoutToUse;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutToUse, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onCardListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getName());
        Glide.with(context).asBitmap().load(items.get(position).getImage()).into(holder.itemImage);
        if (holder.itemQty != null) {
            holder.itemQty.setText(Double.toString(items.get(position).getWeight()));
        }
        if (holder.isChecked != null) {
            if (items.get(position).isChecked() == true) {
                holder.isChecked.setVisibility(View.VISIBLE);
            } else {
                holder.isChecked.setVisibility(View.GONE);
            }
            //holder.isChecked.setVisibility( (items.get(position).isChecked()) ? View.VISIBLE : View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void deleteItem(int i) {
        items.remove(i);
    }

    public interface OnCardListener {
        void onCardClick(int position);
    }

    // Something important stuff

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageButton isChecked = null;
        private TextView itemName;
        private ImageView itemImage;
        OnCardListener onCardListener;
        private TextView itemQty = null;

        public ViewHolder(@NonNull View itemView, OnCardListener cardListener) {
            super(itemView);
            itemName = itemView.findViewById(R.id.md_description);
            itemImage = itemView.findViewById(R.id.item_img);
            itemView.setOnClickListener(this);
            onCardListener = cardListener;
            try {
                itemQty = itemView.findViewById(R.id.som).findViewById(R.id.item_qty_card);
            } catch (Exception e) {

            }
            try {
                isChecked = itemView.findViewById(R.id.checked);
            } catch (Exception e) {

            }

        }

        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }
}
