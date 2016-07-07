package com.example.z_ting.testmvp.app;

/**
 * Created by zhaoting on 16/7/6.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);

    void onError();


    void onSuccess(Object object);
}
