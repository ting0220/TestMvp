package com.example.z_ting.testmvp.presenter;

import com.example.z_ting.testmvp.MainView.MainContract;
import com.example.z_ting.testmvp.app.OnListener;
import com.example.z_ting.testmvp.model.StoryModel;
import com.example.z_ting.testmvp.model.StoryModelImpl;

/**
 * Created by zhaoting on 16/7/7.
 */
public class MainPresenter implements MainContract.MainPresenter {
    private MainContract.MainView mView;
    private StoryModel mStoryModel;


    public MainPresenter(MainContract.MainView view) {
        mView = view;
        mStoryModel = new StoryModelImpl();
    }


    @Override
    public void start() {
        String url = "http://news-at.zhihu.com/api/4/news/latest";
        mStoryModel.getStoryModel(url, new OnListener() {
            @Override
            public void onSuccess(Object object) {
                mView.onSuccess(object);
            }

            @Override
            public void onNoConnected() {
                mView.onNoConnected();
            }

            @Override
            public void onError() {
                mView.onError();
            }
        });
    }

    @Override
    public void delete() {
        mStoryModel.cancelTask();

    }
}
