package com.example.z_ting.testmvp.MainView;

import com.example.z_ting.testmvp.app.BasePresenter;
import com.example.z_ting.testmvp.app.BaseView;

/**
 * Created by zhaoting on 16/7/6.
 */
public interface MainContract {
    interface View extends BaseView<Presenter>{

    }


    interface Presenter extends BasePresenter{
        void editTask();
        void deleteTask();
    }

}
