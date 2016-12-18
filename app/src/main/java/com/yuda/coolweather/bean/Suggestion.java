package com.yuda.coolweather.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yuda on 2016/12/18
 */

public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;

    public class Comfort {

        @SerializedName("txt")
        public String info;

    }

    @SerializedName("cw")
    public CarWash carwash;

    public class CarWash {

        @SerializedName("txt")
        public String info;

    }

    public Sport sport;

    public class Sport {

        @SerializedName("txt")
        public String info;

    }

}
