package com.nonexistentware.recipeappv2;

import android.content.Intent;
import android.icu.util.LocaleData;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nonexistentware.recipeappv2.Common.Common;
import com.nonexistentware.recipeappv2.Database.LocalDataBase;
import com.nonexistentware.recipeappv2.Database.Recent;
import com.nonexistentware.recipeappv2.Database.RecentDataSource;
import com.nonexistentware.recipeappv2.Database.RecentRepository;
import com.squareup.picasso.Picasso;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ViewRecipe extends AppCompatActivity {

    CoordinatorLayout rootLayout;
    ImageView imageView, arrowBack;
    TextView description_txt, itemName, ingredients, backBtn;
    CollapsingToolbarLayout collapsingToolbarLayout;

    CompositeDisposable compositeDisposable;
    RecentRepository recentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        compositeDisposable = new CompositeDisposable();
        LocalDataBase dataBase = LocalDataBase.getInstance(this);
        recentRepository = RecentRepository.getInstance(RecentDataSource.getInstance(dataBase.recentDao()));


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

        //to recent
        addToRecent();

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

    private void addToRecent() {
        Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                Recent recent = new Recent(Common.select_recipe.getImageLink(),
                        Common.select_recipe.getCategoryId(),
                        Common.select_recipe.getDescription(),
                        Common.select_recipe.getItemName(),
                        Common.select_recipe.getIngredients(),
                        String.valueOf(System.currentTimeMillis()),
                        Common.select_image_key);
                        recentRepository.insertRecent(recent);
                        e.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("ERROR", throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });

        compositeDisposable.add(disposable);

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
