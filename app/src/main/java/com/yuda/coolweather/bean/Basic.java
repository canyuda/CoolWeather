package com.yuda.coolweather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yuda on 2016/12/18
 */

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {

        @SerializedName("loc")

        public String updateTime;
    }
}
