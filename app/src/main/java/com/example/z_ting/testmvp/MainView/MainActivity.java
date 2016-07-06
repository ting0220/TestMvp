package com.example.z_ting.testmvp.MainView;

import com.example.z_ting.testmvp.R;
import com.example.z_ting.testmvp.app.BaseFragmentActivity;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.id.id_fragment_layout;
    }
}
