package com.example.day3_rikao.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentId(),container,false);
    }

    protected abstract int getFragmentId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        inifView(view);
        inifData();

    }

    protected abstract void inifView(View view);
    protected abstract void inifData();


}
