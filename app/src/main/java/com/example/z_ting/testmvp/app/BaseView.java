package com.example.z_ting.testmvp.app;

import com.example.z_ting.testmvp.NoConnected;

/**
 * Created by zhaoting on 16/7/6.
 */
public interface BaseView<T> extends NoConnected{
    void setPresenter(T presenter);

    void onError();


    void onSuccess(Object object);
}
