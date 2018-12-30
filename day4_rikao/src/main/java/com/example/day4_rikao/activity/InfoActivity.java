package com.example.day4_rikao.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day4_rikao.R;
import com.example.day4_rikao.adapter.NewsAdapter;
import com.example.day4_rikao.bean.NewsInfo;
import com.example.day4_rikao.bean.UserInfoBean;
import com.example.day4_rikao.contract.NetworkContract;
import com.example.day4_rikao.net.OkhttpUtils;
import com.example.day4_rikao.presenter.UserPersenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InfoActivity extends AppCompatActivity implements NetworkContract.requestNetwork,NetworkContract.newsRequsetNetWork {

    private TextView user_yong;
    private TextView user_name;
    private ImageView image;
    private String uid="71";
    private String keywords = "笔记本";
    private String page = "1";
    private RecyclerView lv;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inifView();
        inifData();

    }

    //初始化数据
    private void inifData() {
        UserInfo();
        NewsInfo();
    }

    private void UserInfo() {
        UserPersenter userPersenter = new UserPersenter((NetworkContract.requestNetwork) this);
        HashMap<String,String> params = new HashMap<>();
        params.put("uid",uid);
        userPersenter.getInfoUser(params);

    }

    private void NewsInfo(){
        //调用p层的获取信息的方法
        UserPersenter userPersenter = new UserPersenter((NetworkContract.newsRequsetNetWork) this);
        HashMap<String,String> params = new HashMap<>();
        params.put("keywords",keywords);
        params.put("page",page);
        userPersenter.getInfoNews(params);
    }


    private void inifView() {
        image = findViewById(R.id.image);
        user_yong = findViewById(R.id.user_yong);
        user_name = findViewById(R.id.user_name);
        lv = findViewById(R.id.lv);
        lv.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this);
        lv.setAdapter(newsAdapter);
    }

    //接口回调展示数据
    @Override
    public void success(UserInfoBean infoBean) {
        user_name.setText(infoBean.data.nickname);
        user_yong.setText(infoBean.data.mobile);

    }

    @Override
    public void success(NewsInfo newsInfo) {
        newsAdapter.setData(newsInfo.getData());
    }

    @Override
    public void successMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkhttpUtils.getinstance().cancelAllTask();
    }
}
