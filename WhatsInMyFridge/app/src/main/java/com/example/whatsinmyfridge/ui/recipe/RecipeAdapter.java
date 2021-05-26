package com.example.whatsinmyfridge.ui.recipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsinmyfridge.R;
import com.example.whatsinmyfridge.objects.RecipeCard;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<RecipeCard> mRecipeCards;
    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;

        public RecipeViewHolder(@NonNull @NotNull View itemView, onItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public RecipeAdapter(ArrayList<RecipeCard> recipeCards){
        mRecipeCards = recipeCards;
    }

    @NonNull
    @NotNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        RecipeViewHolder rvh = new RecipeViewHolder(v, mListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecipeViewHolder holder, int position) {
        RecipeCard currentCard = mRecipeCards.get(position);
        holder.mImageView.setImageResource(currentCard.getmImageResource());
        holder.mTextView.setText(currentCard.getRecipeName());
    }

    @Override
    public int getItemCount() {
        return mRecipeCards.size();
    }
}
