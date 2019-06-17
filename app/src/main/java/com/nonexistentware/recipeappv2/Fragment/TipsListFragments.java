package com.nonexistentware.recipeappv2.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nonexistentware.recipeappv2.R;

public class TipsListFragments extends Fragment {

    private static TipsListFragments INSTANCE = null;

    public TipsListFragments() {

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
        return inflater.inflate(R.layout.fragment_list_tips_fragments, container, false);
    }
}
