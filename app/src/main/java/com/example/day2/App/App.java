package com.example.day2.App;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer())
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSizePercentage(20)
                .diskCacheSize(50*1024*1024)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);

    }
}
