package com.nonexistentware.recipeappv2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nonexistentware.recipeappv2.Common.Common;
import com.nonexistentware.recipeappv2.Database.Recent;
import com.nonexistentware.recipeappv2.Interface.ItemClickListener;
import com.nonexistentware.recipeappv2.Model.RecipeItem;
import com.nonexistentware.recipeappv2.R;
import com.nonexistentware.recipeappv2.ViewHolder.ListRecipeViewHolder;
import com.nonexistentware.recipeappv2.ViewRecipe;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<ListRecipeViewHolder> {

    private Context context;
    private List<Recent> recents;
    RecipeItem recipeItem = new RecipeItem();

    public MyRecyclerAdapter(Context context, List<Recent> recents) {
        this.context = context;
        this.recents = recents;
    }

    @NonNull
    @Override
    public ListRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recipe_item, parent, false);
        int height = parent.getMeasuredHeight()/2;
        itemView.setMinimumHeight(height);
        return new ListRecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListRecipeViewHolder holder, final int position) {
        Picasso.with(context)
                .load(recents.get(position).getImageLink())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.recipeImg, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context)
                                .load(recents.get(position).getImageLink())
                                .error(R.drawable.ic_error_outline_white_24dp)
                                .into(holder.recipeImg, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Log.e("ERROR_BASE", "Could not fetch image");
                                    }
                                });

                    }
                });
            holder.itemName.setText(recents.get(position).getItemName());
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(context, ViewRecipe.class);
                    RecipeItem recipeItem = new RecipeItem();
                    recipeItem.setCategoryId(recents.get(position).getCategoryId());
                    recipeItem.setImageLink(recents.get(position).getImageLink());
                    recipeItem.setDescription(recents.get(position).getDescription());
                    recipeItem.setItemName(recents.get(position).getItemName());
                    recipeItem.setIngredients(recents.get(position).getIngredients());
                    Common.select_recipe = recipeItem;
                    Common.select_image_key = recents.get(position).getKey();
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return recents.size();
    }
}
