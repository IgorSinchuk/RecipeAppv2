package com.nonexistentware.recipeappv2.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nonexistentware.recipeappv2.Interface.ItemClickListener;
import com.nonexistentware.recipeappv2.R;

public class UsefulListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tipItemName;
    public ImageView tipItemImage;

    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    public UsefulListViewHolder(@NonNull View itemView) {
        super(itemView);
        tipItemName = itemView.findViewById(R.id.tip_name);
        tipItemImage = itemView.findViewById(R.id.tip_image);
        tipItemImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
