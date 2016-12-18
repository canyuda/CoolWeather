package com.yuda.coolweather.utils;

import android.content.Context;
import android.os.Bundle;

import com.yuda.coolweather.MyApplication;
import com.yuda.coolweather.db.DaoMaster;

/**
 * Created by yuda on 2016/12/17
 */

public class DBManager {

    private final static String dbName = "weather_db";
    public DaoMaster.DevOpenHelper openHelper;
    private static Context context;
    private static DBManager mInstance = new DBManager(MyApplication.getContext());

    private DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
    }

    public static DBManager newInstance() {
        return mInstance;
    }
}
