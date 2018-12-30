package com.example.day4_rikao.net;

import android.os.Handler;
import com.example.day4_rikao.contract.OkHttpContract;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {

    private static OkhttpUtils okhttpUtils;
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler();

    private OkhttpUtils() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    //单例模式
    public static OkhttpUtils getinstance(){
        if (okhttpUtils==null){
            synchronized (OkhttpUtils.class){
                if (okhttpUtils==null){
                    okhttpUtils = new OkhttpUtils();
                }
            }
        }
        return okhttpUtils;
    }

    public void doGet(String url, final Class clazz, final OkHttpContract okHttpContract){

        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okHttpContract!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpContract.failure("网络有问题,请稍后再试");
                        }
                    });

                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (okHttpContract!=null){
                    if (response.code()==200){
                        paserResult(response.body().string(),clazz,okHttpContract);
                    }
                }

            }
        });

    }



    public void doPost(String url, HashMap<String,String> params, final Class clazz, final OkHttpContract okHttpContract){

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()) {
            builder.add(p.getKey(),p.getValue());
        }

        final Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okHttpContract!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpContract.failure("网络有问题,请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (okHttpContract!=null){
                    if (response.code()==200){
                        paserResult(response.body().string(),clazz,okHttpContract);
                    }
                }
            }
        });
    }

    private <T>void paserResult(String string, Class<T> clazz, final OkHttpContract okHttpContract){

        final T t = new Gson().fromJson(string, clazz);
        handler.post(new Runnable() {
            @Override
            public void run() {
                okHttpContract.success(t);
            }
        });
    }

    public void cancelAllTask(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }

    }


}
