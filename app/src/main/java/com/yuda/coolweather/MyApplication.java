package com.yuda.coolweather;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuda on 2016/12/17
 */

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext(){
        return sContext;
    }
}
