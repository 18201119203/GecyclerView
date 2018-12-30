package com.example.day2.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.day2.Bean.NewsBean;
import com.example.day2.net.NetRequest;
import com.example.day2.url.NewsUrl;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NewsModel implements InewsModel{


    private int page = 1;


    @SuppressLint("StaticFieldLeak")
    @Override
    public void inifNewsModel(final NetRequest netRequest) {

        new AsyncTask<String,Void,NewsBean>(){
            private NewsBean newsBean;
            private String data;

            @Override
            protected NewsBean doInBackground(String... strings) {

                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode==HttpURLConnection.HTTP_OK){
                        data = getData(urlConnection.getInputStream());
                        newsBean = new Gson().fromJson(data, NewsBean.class);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (newsBean!=null){
                    return newsBean;
                }
                return null;
            }

            @Override
            protected void onPostExecute(NewsBean newsBean) {

                if (newsBean==null){
                    netRequest.faile("请求网络有问题,请稍后再试");
                }
                if ("0".equals(newsBean.getCode())&&"请求成功".equals(newsBean.getMsg())){
                    netRequest.success(newsBean);
                }else{
                    netRequest.successMsg(newsBean.getMsg());
                }

            }


        }.execute(NewsUrl.murl+page);
    }


    private String getData(InputStream inputStream) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        for (String tmp = bufferedReader.readLine(); tmp != null ; tmp = bufferedReader.readLine()){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }


}
