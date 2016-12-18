package com.yuda.coolweather.utils;

import android.text.TextUtils;

import com.yuda.coolweather.db.City;
import com.yuda.coolweather.db.CityDao;
import com.yuda.coolweather.db.County;
import com.yuda.coolweather.db.CountyDao;
import com.yuda.coolweather.db.DaoMaster;
import com.yuda.coolweather.db.DaoSession;
import com.yuda.coolweather.db.Provice;
import com.yuda.coolweather.db.ProviceDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yuda on 2016/12/17
 */

public class Utility {
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                DaoMaster master = new DaoMaster
                        (DBManager.newInstance().openHelper.getWritableDatabase());
                DaoSession session = master.newSession();
                ProviceDao proviceDao = session.getProviceDao();
                JSONArray allProvices = new JSONArray(response);
                for (int i = 0; i<allProvices.length();i++){
                    JSONObject provinceObject = allProvices.getJSONObject(i);
                    Provice provice = new Provice();
                    provice.setProvinceName(provinceObject.getString("name"));
                    provice.setProvinceCode(provinceObject.getInt("id"));
                    proviceDao.save(provice);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean handleCityResponse(String response,Long provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                DaoMaster master = new DaoMaster
                        (DBManager.newInstance().openHelper.getWritableDatabase());
                DaoSession session = master.newSession();
                CityDao cityDao = session.getCityDao();
                JSONArray allCities = new JSONArray(response);
                for(int i = 0;i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProviceId(provinceId.intValue());
                    cityDao.save(city);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String response,Long cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                DaoMaster master = new DaoMaster
                        (DBManager.newInstance().openHelper.getWritableDatabase());
                DaoSession session = master.newSession();
                CountyDao countyDao = session.getCountyDao();
                JSONArray allCounties = new JSONArray(response);
                for (int i =0;i<allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId.intValue());
                    countyDao.save(county);
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
