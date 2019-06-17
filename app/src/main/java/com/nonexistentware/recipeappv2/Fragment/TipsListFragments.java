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
import com.nonexistentware.recipeappv2.Model.UsefulItem;
import com.nonexistentware.recipeappv2.R;
import com.nonexistentware.recipeappv2.UsefulView;
import com.nonexistentware.recipeappv2.ViewHolder.UsefulListViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class TipsListFragments extends Fragment {

    FirebaseDatabase database;
    DatabaseReference tipItemBase;

    RecyclerView recyclerView;

    FirebaseRecyclerOptions<UsefulItem> options;
    FirebaseRecyclerAdapter<UsefulItem, UsefulListViewHolder> adapter;

    private static TipsListFragments INSTANCE = null;

    public TipsListFragments() {
        database = FirebaseDatabase.getInstance();
        tipItemBase = database.getReference(Common.STR_USEFUL);

        options = new FirebaseRecyclerOptions.Builder<UsefulItem>()
                .setQuery(tipItemBase, UsefulItem.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<UsefulItem, UsefulListViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final UsefulListViewHolder holder, int position, @NonNull final UsefulItem model) {
                Picasso.with(getActivity())
                        .load(model.getuImageLink())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.tipItemImage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(getActivity())
                                        .load(model.getuImageLink())
                                        .error(R.drawable.ic_error_outline_white_24dp)
                                        .into(holder.tipItemImage, new Callback() {
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

                holder.tipItemName.setText(model.getuName());
                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), UsefulView.class);
                        Common.select_tip_item = model;
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public UsefulListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_useful_item, parent, false);
                return new UsefulListViewHolder(itemView);
            }
        };
    }

    public static TipsListFragments getInstance() {
        if (INSTANCE == null)
            INSTANCE = new TipsListFragments();
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_tips_fragments, container, false);
       recyclerView = view.findViewById(R.id.recycler_list_useful);
       recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        setUsefulCategory();
        return view;
    }

    private void setUsefulCategory() {
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
