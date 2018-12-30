package com.example.day4_rikao.contract;

import com.example.day4_rikao.bean.UserInfoBean;

public interface OkHttpContract {

    void success(Object infoBean);
    void failure(String msg);

}
