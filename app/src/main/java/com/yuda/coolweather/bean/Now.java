package com.yuda.coolweather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yuda on 2016/12/18
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {

        @SerializedName("txt")
        public String info;

    }
}
