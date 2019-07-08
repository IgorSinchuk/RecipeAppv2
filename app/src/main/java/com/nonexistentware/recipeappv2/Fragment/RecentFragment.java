package com.nonexistentware.recipeappv2.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nonexistentware.recipeappv2.Adapter.MyRecyclerAdapter;
import com.nonexistentware.recipeappv2.Database.LocalDataBase;
import com.nonexistentware.recipeappv2.Database.Recent;
import com.nonexistentware.recipeappv2.Database.RecentDataSource;
import com.nonexistentware.recipeappv2.Database.RecentRepository;
import com.nonexistentware.recipeappv2.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecentFragment extends Fragment {

    private static RecentFragment INSTANCE = null;

    RecyclerView recyclerView;
    List<Recent> recentList;

    MyRecyclerAdapter adapter;
    Context context;

    CompositeDisposable compositeDisposable;
    RecentRepository recentRepository;

    @SuppressLint("ValidFragment")
    public RecentFragment(Context context) {
        this.context = context;

        compositeDisposable = new CompositeDisposable();
        LocalDataBase dataBase = LocalDataBase.getInstance(context);
        recentRepository = RecentRepository.getInstance(RecentDataSource.getInstance(dataBase.recentDao()));
    }

    public static RecentFragment getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new RecentFragment(context);
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recents, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_recent);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recentList = new ArrayList<>();
        adapter = new MyRecyclerAdapter(context, recentList);
        recyclerView.setAdapter(adapter);

        loadRecent();

        return view;
    }

    private void loadRecent() {
        Disposable disposable = recentRepository.getAllRecent()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Recent>>() {
                               @Override
                               public void accept(List<Recent> recents) throws Exception {
                                    onGetAllRecentSuccess(recents);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.d("ERROR", throwable.getMessage());
                            }
                        });
                compositeDisposable.add(disposable);
    }

    private void onGetAllRecentSuccess(List<Recent> recents) {
        recentList.clear();
        recentList.addAll(recents);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
