package com.example.whatsinmyfridge.ui.recipe;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsinmyfridge.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<RecipeCard> mRecipeCards;

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;

        public RecipeViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);

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
        RecipeViewHolder rvh = new RecipeViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecipeViewHolder holder, int position) {
        RecipeCard currentCard = mRecipeCards.get(position);
        holder.mImageView.setImageResource(currentCard.getmImageResource());
        holder.mTextView.setText(currentCard.getmText1());
    }

    @Override
    public int getItemCount() {
        return mRecipeCards.size();
    }
}
