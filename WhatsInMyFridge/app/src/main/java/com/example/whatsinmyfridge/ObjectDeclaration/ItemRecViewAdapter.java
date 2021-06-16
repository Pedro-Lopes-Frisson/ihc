package com.example.whatsinmyfridge.ObjectDeclaration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsinmyfridge.R;

import java.util.ArrayList;

public class ItemRecViewAdapter extends RecyclerView.Adapter<ItemRecViewAdapter.ViewHolder> {
    private ArrayList<Item> items = new ArrayList<>();
    private Context context;
    private OnCardListener onCardListener;

    public ItemRecViewAdapter(Context context, OnCardListener onCardListener) {
        this.onCardListener = onCardListener;
        this.context = context;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onCardListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getName());
        Glide.with(context).asBitmap().load(items.get(position).getImage()).into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnCardListener {
        void onCardClick(int position);
    }

    // Something important stuff

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView itemName;
        private ImageView itemImage;
        OnCardListener onCardListener;

        public ViewHolder(@NonNull View itemView, OnCardListener cardListener) {
            super(itemView);
            itemName = itemView.findViewById(R.id.md_description);
            itemImage = itemView.findViewById(R.id.item_img);
            itemView.setOnClickListener(this);
            onCardListener = cardListener;
        }

        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getBindingAdapterPosition());
        }
    }
}
