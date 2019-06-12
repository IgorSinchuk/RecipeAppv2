package com.nonexistentware.recipeappv2;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.nonexistentware.recipeappv2.Common.Common;
import com.squareup.picasso.Picasso;


public class ViewRecipe extends AppCompatActivity {

    CoordinatorLayout rootLayout;
    ImageView imageView, arrowBack;
    TextView description_txt, itemName, ingredients, backBtn;
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = findViewById(R.id.rootLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setTitle(Common.CATEGORY_SELECTED);
        description_txt = findViewById(R.id.description_txt);
        itemName = findViewById(R.id.item_name);
        ingredients = findViewById(R.id.ingredients_txt);
        backBtn = findViewById(R.id.btn_back);
//        arrowBack = findViewById(R.id.arrow_back);

               imageView = findViewById(R.id.imageThumb);
        Picasso.with(this)
                .load(Common.select_recipe.getImageLink())
                .into(imageView);

        description_txt.setText(Common.select_recipe.description); // get current item description
        itemName.setText(Common.select_recipe.itemName);
        ingredients.setText(Common.select_recipe.ingredients);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toBack = new Intent(getApplicationContext(), ListRecipe.class);
                toBack.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(toBack);
            }
        });

//        arrowBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ViewRecipe.this, ListRecipe.class));
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
