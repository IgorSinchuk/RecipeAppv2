package com.nonexistentware.recipeappv2.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nonexistentware.recipeappv2.Interface.ItemClickListener;
import com.nonexistentware.recipeappv2.R;

public class ListRecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ItemClickListener itemClickListener;

    public ImageView recipeImg;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ListRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        recipeImg = itemView.findViewById(R.id.image_recipe);
        recipeImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
