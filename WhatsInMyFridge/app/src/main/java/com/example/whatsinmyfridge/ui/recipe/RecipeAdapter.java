package com.example.whatsinmyfridge.ui.recipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsinmyfridge.R;
import com.example.whatsinmyfridge.objects.RecipeCard;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> implements Filterable {

    private ArrayList<RecipeCard> mRecipeCards;
    private ArrayList<RecipeCard> mRecipeCardsFull;
    private Context context;
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

    public RecipeAdapter(ArrayList<RecipeCard> recipeCards, Context con){
        mRecipeCards = recipeCards;
        mRecipeCardsFull = new ArrayList<>(recipeCards);
        context = con;
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
        Glide.with(context).asBitmap().load(currentCard.getmImageResource()).into(holder.mImageView);
        //holder.mImageView.setImageResource(currentCard.getmImageResource());
        holder.mTextView.setText(currentCard.getRecipeName());
    }

    @Override
    public int getItemCount() {
        return mRecipeCards.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RecipeCard> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mRecipeCardsFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (RecipeCard recipe : mRecipeCardsFull){
                    if (recipe.getRecipeName().toLowerCase().contains(filterPattern)){
                        filteredList.add(recipe);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mRecipeCards.clear();
            mRecipeCards.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
