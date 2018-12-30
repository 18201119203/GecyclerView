package com.example.day2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.day2.Bean.NewsBean;
import com.example.day2.R;
import com.example.day2.adapter.NewsAdapter;
import com.example.day2.presenter.NewsPresenter;
import com.example.day2.view.NewsView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MainActivity extends AppCompatActivity implements NewsView {

    private PullToRefreshListView lv;
    private NewsPresenter newsPresenter;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsRequest();

    }

    private void NewsRequest() {

        lv = findViewById(R.id.lv);
        newsPresenter = new NewsPresenter(this);
        newsPresenter.NewsRequest();
        newsAdapter = new NewsAdapter(this);
        lv.setAdapter(newsAdapter);

    }

    @Override
    public void success(NewsBean newsBean) {
        newsAdapter.setNews(newsBean.getData());
    }

    @Override
    public void Faile(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void successMsg(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
