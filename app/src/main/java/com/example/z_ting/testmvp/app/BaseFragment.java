package com.example.z_ting.testmvp.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Z_TING on 2016/7/5.
 */
public abstract class BaseFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayoutId(), container, false);
        initViews(view);
        return view;
    }

    //新建fragment
    public static <T extends BaseFragment> T newInstance(@Nullable FragmentManager fragmentManager, Class<T> clazz) {
        T fragment = null;
        if (fragmentManager != null) {
            fragment = (T) fragmentManager.findFragmentByTag(clazz.getName());
        } else {
            throw new NullPointerException("fragmentManager is null");
        }
        if (fragment == null) {
            try {
                fragment = clazz.newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return fragment;
    }

    //打开新的fragment
    public <T extends BaseFragment> void replaceFragment(Class<T> clazz) {
        replaceFragment(clazz, null);
    }

    public <T extends BaseFragment> void replaceFragment(Class<T> clazz, String tag) {
        replaceFragment(clazz, tag, 0);
    }

    public <T extends BaseFragment> void replaceFragment(Class<T> clazz, String tag, int res) {
        replaceFragment(clazz, tag, res, null);
    }

    public <T extends BaseFragment> void replaceFragment(Class<T> clazz, String tag, int res, Bundle bundle) {
        if (res == 0) {
            res = ((BaseFragmentActivity) getContext()).getFragmentLayoutId();
            if (res == 0) {
                throw new NullPointerException("The id of container is null");
            }
        }

        tag = TextUtils.isEmpty(tag) ? clazz.getName() : tag;

        T currentFragment = (T) ((BaseFragmentActivity) getContext()).getCurrentFragment();
        if (currentFragment != null) {
            if (currentFragment.getClass().getName().equals(clazz.getName())) {
                return;
            }
        }

        T fragment = T.newInstance(getFragmentManager(), clazz);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        getFragmentManager().beginTransaction()
                .replace(res, fragment)
                .addToBackStack(tag)
                .commit();

        ((BaseFragmentActivity) getContext()).setCurrentFragment(fragment);
    }


    protected abstract void initViews(View view);


    protected abstract int getFragmentLayoutId();
}
