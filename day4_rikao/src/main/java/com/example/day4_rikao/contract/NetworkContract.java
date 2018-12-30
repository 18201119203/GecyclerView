package com.example.day4_rikao.contract;

import com.example.day4_rikao.bean.NewsInfo;
import com.example.day4_rikao.bean.UserInfoBean;
import com.example.day4_rikao.model.UserInfoModel;

import java.util.HashMap;

public interface NetworkContract {


    //presenter层抽样方法
    public abstract class getUserInfo{

       public abstract void upload();
       public abstract void getInfoUser(HashMap<String,String> params);
       public abstract void getInfoNews(HashMap<String,String> params);


    }

    interface newsRequsetNetWork{
        void success(NewsInfo newsInfo);
        void successMsg(String msg);
        void failure(String msg);
    }

    interface newsResposeNetWork{
        void success(NewsInfo newsInfo);
        void successMsg(String msg);
        void failure(String msg);

    }

    //view接口回调
    interface requestNetwork{
            void success(UserInfoBean infoBean);
            void successMsg(String msg);
            void failure(String msg);
    }
    //model接口回调
    interface resposeNetwork{
        void success(UserInfoBean infoBean);
        void successMsg(String msg);
        void failure(String msg);

    }

}
