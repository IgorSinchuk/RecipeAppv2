package com.nonexistentware.recipeappv2;

import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nonexistentware.recipeappv2.Common.Common;
import com.nonexistentware.recipeappv2.Model.RecipeItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class ViewRecipe extends AppCompatActivity {

    CoordinatorLayout rootLayout;
    ImageView imageView;
    TextView description_txt, itemName;
    CollapsingToolbarLayout collapsingToolbarLayout;
    DatabaseReference reference;
    Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final RecipeItem recipeItem = new RecipeItem();

        rootLayout = findViewById(R.id.rootLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setTitle(Common.CATEGORY_SELECTED);
        description_txt = findViewById(R.id.description_txt);
        itemName = findViewById(R.id.item_name);

               imageView = findViewById(R.id.imageThumb);
        Picasso.with(this)
                .load(Common.select_recipe.getImageLink())
                .into(imageView);

        description_txt.setText(Common.select_recipe.description); // get current item description
        itemName.setText(Common.select_recipe.itemName);



//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String description = dataSnapshot.child("description").getValue(String.class);
//                recipeItem.setDescription(description);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
