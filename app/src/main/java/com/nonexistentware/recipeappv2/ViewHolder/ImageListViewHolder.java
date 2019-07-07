package com.nonexistentware.recipeappv2.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nonexistentware.recipeappv2.R;

public class ImageListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView imageListScroll;

    public ImageListViewHolder(@NonNull View itemView) {
        super(itemView);
        imageListScroll = itemView.findViewById(R.id.image_recipe_list);
    }

    @Override
    public void onClick(View v) {

    }
}
