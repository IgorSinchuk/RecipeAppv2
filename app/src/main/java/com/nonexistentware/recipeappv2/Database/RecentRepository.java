package com.nonexistentware.recipeappv2.Database;

import com.nonexistentware.recipeappv2.Interface.RecentDatSource;

import java.util.List;

import io.reactivex.Flowable;

public class RecentRepository implements RecentDatSource {

    private RecentDataSource localDataSource;
    private static RecentRepository instance;

    public RecentRepository(RecentDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    public static RecentRepository getInstance(RecentDataSource localDataSource) {
        if (instance == null)
            instance = new RecentRepository(localDataSource);
        return instance;
    }

    @Override
    public Flowable<List<Recent>> getAllRecent() {
        return localDataSource.getAllRecent();
    }

    @Override
    public void insertRecent(Recent... recent) {
        localDataSource.insertRecent(recent);
    }

    @Override
    public void updateRecent(Recent... recent) {
        localDataSource.updateRecent(recent);
    }

    @Override
    public void deleteRecent(Recent... recent) {
        localDataSource.deleteRecent(recent);
    }

    @Override
    public void deleteAllRecent() {
        localDataSource.deleteAllRecent();
    }
}
