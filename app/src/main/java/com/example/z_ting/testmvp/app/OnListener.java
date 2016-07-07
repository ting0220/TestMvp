package com.example.z_ting.testmvp.app;

/**
 * Created by zhaoting on 16/7/7.
 */
public interface OnListener {
    void onSuccess(Object object);

    void onNoConnected();

    void onError();
}
