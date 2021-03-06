package com.nonexistentware.recipeappv2.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nonexistentware.recipeappv2.R;


public class DailyPopularFragment extends Fragment {

    private static DailyPopularFragment INSTANCE = null;

    public DailyPopularFragment() {
    }

    public static DailyPopularFragment getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DailyPopularFragment();
        return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_popular, container, false);
    }
}
