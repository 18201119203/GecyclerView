package com.example.day2.net;

import com.example.day2.Bean.NewsBean;

public interface NetRequest {

    void success(NewsBean newsBean);
    void faile(String s);
    void successMsg(String s);

}
