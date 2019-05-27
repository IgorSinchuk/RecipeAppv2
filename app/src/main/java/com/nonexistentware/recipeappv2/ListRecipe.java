package com.nonexistentware.recipeappv2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nonexistentware.recipeappv2.Common.Common;
import com.nonexistentware.recipeappv2.Interface.ItemClickListener;
import com.nonexistentware.recipeappv2.Model.RecipeItem;
import com.nonexistentware.recipeappv2.ViewHolder.ListRecipeViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ListRecipe extends AppCompatActivity {

    Query query;
    FirebaseRecyclerOptions<RecipeItem> options;
    FirebaseRecyclerAdapter<RecipeItem, ListRecipeViewHolder> adapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Common.CATEGORY_SELECTED);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.recycler_list_recipe);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        loadRecipeList();
    }

    private void loadRecipeList() {
        query = FirebaseDatabase.getInstance().getReference(Common.STR_RECIPE)
                .orderByChild("categoryId").equalTo(Common.CATEGORY_ID_SELECTED);
        options = new FirebaseRecyclerOptions.Builder<RecipeItem>()
                .setQuery(query, RecipeItem.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<RecipeItem, ListRecipeViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ListRecipeViewHolder holder, int position, @NonNull final RecipeItem model) {
                Picasso.with(getBaseContext())
                        .load(model.getImageLink())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.recipeImg, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(getBaseContext())
                                        .load(model.getImageLink())
                                        .error(R.drawable.ic_error_outline_white_24dp)
                                        .networkPolicy(NetworkPolicy.OFFLINE)
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

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                    }
                });
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
