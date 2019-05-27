package com.nonexistentware.recipeappv2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nonexistentware.recipeappv2.Common.Common;
import com.nonexistentware.recipeappv2.Interface.ItemClickListener;
import com.nonexistentware.recipeappv2.ListRecipe;
import com.nonexistentware.recipeappv2.Model.CategoryItem;
import com.nonexistentware.recipeappv2.R;
import com.nonexistentware.recipeappv2.ViewHolder.CategoryViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class CategoryFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference categoryRecipe;

    RecyclerView recyclerView;

    FirebaseRecyclerOptions<CategoryItem> options;
    FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder> adapter;

    private static CategoryFragment INSTANCE = null;


    public CategoryFragment() {
        database = FirebaseDatabase.getInstance();
        categoryRecipe = database.getReference(Common.STR_CATEGORY_BACKGROUND );

       options = new FirebaseRecyclerOptions.Builder<CategoryItem>()
               .setQuery(categoryRecipe, CategoryItem.class)
               .build();

       adapter = new FirebaseRecyclerAdapter<CategoryItem, CategoryViewHolder>(options) {
           @Override
           protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position, @NonNull final CategoryItem model) {
               Picasso.with(getActivity())
                       .load(model.getImageLink())
                       .networkPolicy(NetworkPolicy.OFFLINE)
                       .into(holder.categoryImage, new Callback() {
                           @Override
                           public void onSuccess() {

                           }

                           @Override
                           public void onError() {
                                Picasso.with(getActivity())
                                        .load(model.getImageLink())
                                        .error(R.drawable.ic_error_outline_white_24dp)
                                        .into(holder.categoryImage, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Log.e("ERROR", "Couldn't fetch image");
                                            }
                                        });
                           }
                       });

               holder.categoryName.setText(model.getName());
               holder.setItemClickListener(new ItemClickListener() {
                   @Override
                   public void onClick(View view, int position) {
                       Common.CATEGORY_ID_SELECTED = adapter.getRef(position).getKey();
                       Common.CATEGORY_SELECTED = model.getName();
                       Intent intent = new Intent(getActivity(), ListRecipe.class);
                       startActivity(intent);
                   }
               });
           }

           @NonNull
           @Override
           public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View itemView = LayoutInflater.from(parent.getContext())
                       .inflate(R.layout.layout_category_item, parent, false);
               return new CategoryViewHolder(itemView);
           }
       };
    }


    public static CategoryFragment getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CategoryFragment();
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recycler_category);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        setCategory();
        return view;

    }

    private void setCategory() {
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        if (adapter != null)
            adapter.stopListening();
        super.onStop();
    }
}
