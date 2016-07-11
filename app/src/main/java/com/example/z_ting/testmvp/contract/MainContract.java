package com.example.z_ting.testmvp.contract;

import com.example.z_ting.testmvp.app.BasePresenter;
import com.example.z_ting.testmvp.app.BaseView;

public interface MainContract {
    interface MainView extends BaseView<MainPresenter> {

    }


    interface MainPresenter extends BasePresenter {

    }

}
