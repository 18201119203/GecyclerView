package com.example.day2.presenter;

import com.example.day2.Bean.NewsBean;
import com.example.day2.model.NewsModel;
import com.example.day2.net.NetRequest;
import com.example.day2.view.NewsView;

public class NewsPresenter {

    private NewsModel newsModel;
    private NewsView newsView;

    public NewsPresenter(NewsView newsView) {
        this.newsView = newsView;
        newsModel = new NewsModel();
    }

    public void NewsRequest(){

        newsModel.inifNewsModel(new NetRequest() {
            @Override
            public void success(NewsBean newsBean) {
                newsView.success(newsBean);
            }

            @Override
            public void faile(String s) {
                newsView.Faile(s);
            }

            @Override
            public void successMsg(String s) {
                newsView.successMsg(s);
            }
        });

    }



}
