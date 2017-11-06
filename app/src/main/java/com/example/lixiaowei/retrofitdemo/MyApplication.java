package com.example.lixiaowei.retrofitdemo;

import android.app.Application;

/**
 * @ author  LiXiaoWei
 * @ date  2017/10/31.
 * desc:
 */

public class MyApplication extends Application {
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }
}
