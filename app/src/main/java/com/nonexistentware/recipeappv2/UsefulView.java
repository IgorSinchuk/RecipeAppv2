package com.nonexistentware.recipeappv2;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nonexistentware.recipeappv2.Common.Common;
import com.squareup.picasso.Picasso;

public class UsefulView extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    ImageView imageView;
    TextView descriptionTxt, tipName;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useful_view);

//        Toolbar toolbar = findViewById(R.id.t_toolbar);
//        setSupportActionBar(toolbar);
        if (getActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = findViewById(R.id.useful_root_layout);
//        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
//        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        descriptionTxt = findViewById(R.id.tip_description);
        imageView = findViewById(R.id.useful_image);
        tipName = findViewById(R.id.tip_name);

        Picasso.with(this)
                .load(Common.select_tip_item.getuImageLink())
                .into(imageView);

        descriptionTxt.setText(Common.select_tip_item.uDescription);
        tipName.setText(Common.select_tip_item.uName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
