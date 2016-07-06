package com.example.z_ting.testmvp.app;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

/**
 * Created by zhaoting on 16/7/6.
 */
public abstract class BaseFragmentActivity extends BaseActivity {

    private BaseFragment currentFragment;


    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(BaseFragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public static <T extends BaseFragment> T newInstanceFragment(@Nullable FragmentManager fragmentManager, Class<T> clazz) {
        return T.newInstance(fragmentManager, clazz);
    }

    public <T extends BaseFragment> void changeFragment(Class clazz) {
        changeFragment(clazz, null);
    }

    public <T extends BaseFragment> void changeFragment(Class clazz, String tag) {
        changeFragment(clazz, tag, 0);
    }


    public <T extends BaseFragment> void changeFragment(Class clazz, String tag, int res) {
        changeFragment(clazz, tag, res, null);
    }

    public <T extends BaseFragment> void changeFragment(Class clazz, String tag, int res, Bundle bundle) {
        if (res == 0) {
            res = getFragmentLayoutId();
            if (res == 0) {
                throw new Resources.NotFoundException("FragmentContainerId is null");
            }
        }

        tag = TextUtils.isEmpty(tag) ? clazz.getName() : tag;
        if (currentFragment != null) {
            if (currentFragment.getClass().getName().equals(tag)) {
                return;
            }
        }

        T fragment = (T) newInstanceFragment(getSupportFragmentManager(), clazz);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            transaction.add(res, fragment, tag);
        }
        if (fragment.isHidden()) {
            transaction.show(fragment);
        }
        if (currentFragment != null && currentFragment.isVisible()) {
            transaction.hide(currentFragment);
        }
        transaction.commit();
        currentFragment = fragment;
    }


    //获取activity中fragment所在的布局Id
    protected abstract int getFragmentLayoutId();
}
