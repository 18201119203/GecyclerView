package com.example.day2.view;

import com.example.day2.Bean.NewsBean;

public interface NewsView {

    void success(NewsBean newsBean);
    void Faile(String s);
    void successMsg(String s);

}
