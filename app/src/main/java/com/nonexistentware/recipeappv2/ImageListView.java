package com.nonexistentware.recipeappv2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nonexistentware.recipeappv2.Common.Common;
import com.nonexistentware.recipeappv2.Model.RecipeImageList;
import com.nonexistentware.recipeappv2.ViewHolder.ImageListViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ImageListView extends AppCompatActivity {

    Query query;
    FirebaseRecyclerOptions<RecipeImageList> options;
    FirebaseRecyclerAdapter<RecipeImageList, ImageListViewHolder> adapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list_view);

        recyclerView = findViewById(R.id.photo_list_recycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        loadImageList();
    }

    private void loadImageList() {

        query = FirebaseDatabase.getInstance().getReference(Common.STR_IMAGELIST)
                .orderByChild("categoryId");

        options = new FirebaseRecyclerOptions.Builder<RecipeImageList>()
                .setQuery(query, RecipeImageList.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<RecipeImageList, ImageListViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ImageListViewHolder holder, int position, @NonNull final RecipeImageList model) {
                Picasso.with(getBaseContext())
                        .load(model.imageListLink)
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.imageListScroll, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(getBaseContext())
                                        .load(model.imageListLink)
                                        .error(R.drawable.ic_error_outline_white_24dp)
                                        .networkPolicy(NetworkPolicy.OFFLINE)
                                        .into(holder.imageListScroll, new Callback() {
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
            }

            @NonNull
            @Override
            public ImageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.image_recipe_list, parent, false);
                int height = parent.getMeasuredHeight()/2;
                itemView.setMinimumHeight(height);
                return new ImageListViewHolder(itemView);

            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        if (adapter != null)
            adapter.stopListening();
        super.onStop();
    }
}
