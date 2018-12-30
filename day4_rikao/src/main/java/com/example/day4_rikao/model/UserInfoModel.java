package com.example.day4_rikao.model;

import com.example.day4_rikao.api.UserApi;
import com.example.day4_rikao.bean.NewsInfo;
import com.example.day4_rikao.bean.UserInfoBean;
import com.example.day4_rikao.contract.NetworkContract;
import com.example.day4_rikao.contract.OkHttpContract;
import com.example.day4_rikao.net.OkhttpUtils;
import java.util.HashMap;

import okhttp3.OkHttpClient;

public class UserInfoModel {
    private NetworkContract.resposeNetwork resposeNetwork;
    private NetworkContract.newsResposeNetWork newsResposeNetWork;

    public void requestUserInfo(HashMap<String, String> params) {
        OkhttpUtils.getinstance().doPost(UserApi.USERINFO, params, UserInfoBean.class, new OkHttpContract() {
            @Override
            public void success(Object infoBean) {

                if (resposeNetwork!=null){
                    resposeNetwork.success((UserInfoBean) infoBean);
                }
            }
            @Override
            public void failure(String msg) {
                if (resposeNetwork!=null){
                    resposeNetwork.failure(msg);
                }

            }
        });
    }
    public void setResposeNetwork(NetworkContract.resposeNetwork resposeNetwork){
        this.resposeNetwork = resposeNetwork;
    }


    public void requestNewsInfo(HashMap<String, String> params) {

        OkhttpUtils.getinstance().doPost(UserApi.REQUSET_DATA, params, NewsInfo.class, new OkHttpContract() {
            @Override
            public void success(Object infoBean) {
                if (newsResposeNetWork!=null){
                    newsResposeNetWork.success((NewsInfo) infoBean);
                }
            }

            @Override
            public void failure(String msg) {
                if (newsResposeNetWork!=null){
                    newsResposeNetWork.failure(msg);
                }
            }
        });

    }
    public void setNewsResposeNetwork(NetworkContract.newsResposeNetWork newsResposeNetWork){
        this.newsResposeNetWork = newsResposeNetWork;
    }
}
