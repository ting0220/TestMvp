package com.example.z_ting.testmvp.model;

import com.example.z_ting.testmvp.app.OnListener;
import com.example.z_ting.testmvp.okhttpUtil.OkHttpUtil;

/**
 * Created by zhaoting on 16/7/7.
 */
public class StoryModelImpl implements StoryModel {
    @Override
    public void getStoryModel(String url, final OnListener listener) {

        OkHttpUtil.getInstance().get(url, new OkHttpUtil.HttpCallback() {
            @Override
            public void onNoConnected() {
                listener.onNoConnected();
            }

            @Override
            public void onSuccess(Object object) {
                listener.onSuccess(object);

            }

            @Override
            public void onError(String s) {
                listener.onError();
            }

        }, "getStory");
    }

    @Override
    public void cancelTask() {
        OkHttpUtil.getInstance().cancle("getStory");
    }
}
