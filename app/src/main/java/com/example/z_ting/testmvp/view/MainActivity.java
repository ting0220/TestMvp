package com.example.z_ting.testmvp.view;

import android.util.Log;

import com.example.z_ting.testmvp.MainView.MainContract;
import com.example.z_ting.testmvp.R;
import com.example.z_ting.testmvp.app.BaseActivity;
import com.example.z_ting.testmvp.presenter.MainPresenter;
import com.example.zhaoting.utils.NetUtils;

public class MainActivity extends BaseActivity implements MainContract.MainView {
    private MainPresenter mPresenter;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        NetUtils.getInstance().init(this);
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void onNoConnected() {
        Log.i("tag", "onNoConnected: ");

    }

    @Override
    public void onError() {
        Log.i("tag", "onError: ");

    }

    @Override
    public void onSuccess(Object object) {
        Log.i("tag", "onSuccess: ");

    }
}
