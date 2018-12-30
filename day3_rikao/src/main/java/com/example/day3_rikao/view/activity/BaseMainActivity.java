package com.example.day3_rikao.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseMainActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityId());
        
        inifView();
        inifData();
        
    }

    protected abstract void inifView();

    protected abstract void inifData();

    protected abstract int getActivityId();
    
    
    
    
}
