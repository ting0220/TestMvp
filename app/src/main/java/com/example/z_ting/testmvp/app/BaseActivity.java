package com.example.z_ting.testmvp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhaoting on 16/7/6.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutId());
        initViews();
    }

    //activity跳转，不带参数
    public void jumpActivity(Class to, boolean isFinish) {
        jumpActivity(to, null, isFinish);
    }

    //activity跳转，带参数
    public void jumpActivity(Class to, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(this, to);
        startActivity(intent);
        if (isFinish) {
            this.finish();
        }
    }

    //获取activity所在的布局
    protected abstract int getActivityLayoutId();

    protected abstract void initViews();
}
