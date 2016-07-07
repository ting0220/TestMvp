package com.example.z_ting.testmvp.okhttpUtil;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.zhaoting.utils.NetUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by zhaoting on 16/7/6.
 */
public class OkHttpUtil {
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;


    /**
     * 使用内部类方式实现单例模式
     */
    private static class SingletonHolder {
        private static OkHttpUtil instance = new OkHttpUtil();
    }

    private OkHttpUtil() {
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpUtil getInstance() {
        return SingletonHolder.instance;
    }

    public void get(String url, HttpCallback callback) {
        final HttpCallback httpCallBack = callback;

        if (NetUtils.getInstance().isNetConnected()) {
            final Request request = new Request.Builder().url(url).build();
            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    final String errorMessage = e.getMessage();
                    onError(httpCallBack, errorMessage);
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (response.isSuccessful()) {
                        final String body = response.body().string();
                        onSuccess(httpCallBack, body);
                    } else {
                        onError(httpCallBack, response.message());
                    }
                }
            });

        } else {
            onNoConnected(httpCallBack);
        }
    }

    public void get(String url, Map<String, String> map, HttpCallback callback) {
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("?");
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        for (int i = 0; i < map.size(); i++) {
            String key = iterator.next();
            String value = map.get(key);
            if (!TextUtils.isEmpty(value)) {
                stringBuilder.append(key);
                stringBuilder.append("=");
                stringBuilder.append(value);
            }
            if (!TextUtils.isEmpty(value) && i < map.size() - 1) {
                stringBuilder.append("&");
            }
        }
        get(stringBuilder.toString(), callback);
    }


    private void onNoConnected(HttpCallback callback) {
        callback.onNoConnected();
    }

    private void onSuccess(final HttpCallback callback, Object object) {
        final String s = (String) object;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //需要在主线程操作
                callback.onSuccess(s);
            }
        });
    }


    private void onError(final HttpCallback callback, final String message) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //需要在主线程操作
                callback.onError(message);
            }
        });
    }

    public static abstract class HttpCallback implements Callback {
        public abstract void onNoConnected();

        public abstract void onSuccess(Object object);

        public abstract void onError(String s);
    }
}
