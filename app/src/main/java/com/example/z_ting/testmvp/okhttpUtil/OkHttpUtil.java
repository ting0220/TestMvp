package com.example.z_ting.testmvp.okhttpUtil;

import com.example.z_ting.testmvp.NoConnected;
import com.example.zhaoting.utils.NetUtils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;


/**
 * Created by zhaoting on 16/7/6.
 */
public class OkHttpUtil {
    private OkHttpClient mOkHttpClient;


    /**
     * 使用内部类方式实现单例模式
     */
    private static class SingletonHolder {
        private static OkHttpUtil instance = new OkHttpUtil();
    }

    private OkHttpUtil() {
        mOkHttpClient = new OkHttpClient();
    }

    public static OkHttpUtil getInstance() {
        return SingletonHolder.instance;
    }

    public void get(String url, Callback responseCallback, NoConnected mNoConnected) {
        if (NetUtils.getInstance().isNetConnected()) {


        } else {
//else
        }
    }

}
