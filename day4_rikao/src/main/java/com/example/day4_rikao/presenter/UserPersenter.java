package com.example.day4_rikao.presenter;

import com.example.day4_rikao.bean.NewsInfo;
import com.example.day4_rikao.bean.UserInfoBean;
import com.example.day4_rikao.contract.NetworkContract;
import com.example.day4_rikao.model.UserInfoModel;

import java.util.HashMap;

import okhttp3.Response;

public class UserPersenter extends NetworkContract.getUserInfo{

    private UserInfoModel userInfoModel;
    private NetworkContract.requestNetwork requestNetwork;
    private NetworkContract.newsRequsetNetWork newsRequsetNetWork;

    public UserPersenter(NetworkContract.requestNetwork requestNetwork) {
        userInfoModel = new UserInfoModel();
        this.requestNetwork = requestNetwork;
    }
    public UserPersenter(NetworkContract.newsRequsetNetWork newsRequsetNetWork) {
        userInfoModel = new UserInfoModel();
        this.newsRequsetNetWork = newsRequsetNetWork;
    }

    @Override
    public void upload() {

    }

    @Override
    public void getInfoUser(HashMap<String,String> params) {
        userInfoModel.requestUserInfo(params);
        userInfoModel.setResposeNetwork(new NetworkContract.resposeNetwork() {
            @Override
            public void success(UserInfoBean infoBean) {
                if (requestNetwork!=null){
                    requestNetwork.success(infoBean);
                }
            }

            @Override
            public void successMsg(String msg) {
                if (requestNetwork!=null) {
                    requestNetwork.successMsg(msg);
                }
            }

            @Override
            public void failure(String msg) {
                if (requestNetwork!=null) {
                    requestNetwork.failure(msg);
                }
            }
        });
    }

    @Override
    public void getInfoNews(HashMap<String, String> params) {
        userInfoModel.requestNewsInfo(params);
        userInfoModel.setNewsResposeNetwork(new NetworkContract.newsResposeNetWork() {
            @Override
            public void success(NewsInfo newsInfo) {
                newsRequsetNetWork.success(newsInfo);
            }

            @Override
            public void successMsg(String msg) {
                requestNetwork.successMsg(msg);
            }

            @Override
            public void failure(String msg) {
                requestNetwork.failure(msg);
            }
        });

    }
}
