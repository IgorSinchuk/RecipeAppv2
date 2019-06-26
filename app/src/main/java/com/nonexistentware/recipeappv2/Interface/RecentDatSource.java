package com.nonexistentware.recipeappv2.Interface;

import com.nonexistentware.recipeappv2.Database.Recent;

import java.util.List;

import io.reactivex.Flowable;

public interface RecentDatSource {
    Flowable<List<Recent>> getAllRecent();
    void insertRecent(Recent...recent);
    void updateRecent(Recent...recent);
    void deleteRecent(Recent...recent);
    void deleteAllRecent();
}
