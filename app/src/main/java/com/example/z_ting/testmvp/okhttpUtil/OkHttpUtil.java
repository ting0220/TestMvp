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
import okhttp3.RequestBody;
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

    /**
     * 没有参数的get请求
     *
     * @param url      请求网址
     * @param callback
     */
    public void get(String url, HttpCallback callback, String tag) {
        final HttpCallback httpCallBack = callback;

        if (NetUtils.getInstance().isNetConnected()) {
            Request request = new Request.Builder()
                    .url(url)
                    .tag(tag)
                    .build();
            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    String errorMessage = e.getMessage();
                    onError(httpCallBack, errorMessage);
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
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

    /**
     * 有参数的get请求
     *
     * @param url
     * @param map
     * @param callback
     */
    public void get(String url, Map<String, String> map, HttpCallback callback, String tag) {
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
        get(stringBuilder.toString(), callback, tag);
    }


    /**
     * post 提交
     *
     * @param url
     * @param body
     * @param responseCallback
     */
    public void post(String url, RequestBody body, HttpCallback responseCallback, String tag) {
        final HttpCallback httpCallback = responseCallback;
        if (NetUtils.getInstance().isNetConnected()) {
            Request request = new Request.Builder().url(url)
                    .addHeader("Accept", "application/json")
                    .post(body)
                    .tag(tag)
                    .build();
            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    String errorMessage = e.getMessage();
                    onError(httpCallback, errorMessage);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        onSuccess(httpCallback, body);
                    } else {
                        onError(httpCallback, response.message());
                    }
                }
            });
        } else {
            onNoConnected(httpCallback);
        }
    }

    /**
     * 取消某个请求
     *
     * @param tag
     */
    public void cancle(String tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
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

    public static abstract class HttpCallback {
        public abstract void onNoConnected();

        public abstract void onSuccess(Object object);

        public abstract void onError(String s);
    }
}
