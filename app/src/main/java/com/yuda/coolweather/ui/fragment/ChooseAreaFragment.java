package com.yuda.coolweather.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.yuda.coolweather.MainActivity;
import com.yuda.coolweather.MyApplication;
import com.yuda.coolweather.R;
import com.yuda.coolweather.db.City;
import com.yuda.coolweather.db.CityDao;
import com.yuda.coolweather.db.County;
import com.yuda.coolweather.db.CountyDao;
import com.yuda.coolweather.db.DaoMaster;
import com.yuda.coolweather.db.DaoSession;
import com.yuda.coolweather.db.Provice;
import com.yuda.coolweather.db.ProviceDao;
import com.yuda.coolweather.ui.activity.WeatherActivity;
import com.yuda.coolweather.utils.DBManager;
import com.yuda.coolweather.utils.HttpUtil;
import com.yuda.coolweather.utils.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseAreaFragment extends Fragment {
    
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    
    private ProgressDialog mProgressDialog;
    
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.back_button)
    Button backButton;
    @BindView(R.id.list_view)
    ListView listView;
    
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();
    
    private List<Provice> mProviceList;
    private List<City> mCityList;
    private List<County> mCountyList;
    
    private Provice selectedProvince;
    private City selectedCity;
    private int currentLevel;
    
    @Nullable
    @Override
    public View onCreateView
            (LayoutInflater inflater,
             @Nullable ViewGroup container,
             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_area,container,false);
        ButterKnife.bind(this,view);
        adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(currentLevel == LEVEL_PROVINCE){
                    selectedProvince = mProviceList.get(i);
                    queryCities();
                } else if(currentLevel == LEVEL_CITY){
                    selectedCity = mCityList.get(i);
                    queryCounties();
                } else if (currentLevel == LEVEL_COUNTY){
                    String weatherId = mCountyList.get(i).getWeatherId();
                    if (getActivity() instanceof MainActivity){
                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        intent.putExtra("weather_id",weatherId);
                        startActivity(intent);
                        getActivity().finish();
                    } else if (getActivity() instanceof WeatherActivity){
                        WeatherActivity activity = (WeatherActivity) getActivity();
                        activity.drawerlayout.closeDrawers();
                        activity.swipeRefresh.setRefreshing(true);
                        activity.requestWeather(weatherId);
                    }
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentLevel == LEVEL_COUNTY){
                      queryCities();
                } else if(currentLevel == LEVEL_CITY){
                    queryProvices();
                }
            }
        });
        queryProvices();
    }

    private void  queryProvices(){
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        DaoMaster master = new DaoMaster
                (DBManager.newInstance().openHelper.getWritableDatabase());
        DaoSession session = master.newSession();
        ProviceDao proviceDao = session.getProviceDao();
        mProviceList = proviceDao.queryBuilder().list();
        if(mProviceList.size() > 0){
            dataList.clear();
            for (Provice provice:mProviceList){
                dataList.add(provice.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            String address = "http://guolin.tech/api/china";
            queryFromServer(address,"province");
        }
    }

    private void queryCities(){
        titleText.setText(selectedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        DaoMaster master = new DaoMaster
                (DBManager.newInstance().openHelper.getWritableDatabase());
        DaoSession session = master.newSession();
        CityDao cityDao = session.getCityDao();
        mCityList =  cityDao.queryBuilder().where(CityDao.Properties.ProviceId.eq(selectedProvince.getId())).list();
        if (mCityList.size()>0){
            dataList.clear();
            for (City city: mCityList){
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            String address = "http://guolin.tech/api/china/"+provinceCode;
            queryFromServer(address,"city");
        }
    }

    private void queryCounties(){
        titleText.setText(selectedCity.getCityName());
        backButton.setVisibility(View.VISIBLE);
        DaoMaster master = new DaoMaster
                (DBManager.newInstance().openHelper.getWritableDatabase());
        DaoSession session = master.newSession();
        CountyDao countyDao = session.getCountyDao();
        mCountyList = countyDao.queryBuilder().where(CountyDao.Properties.CityId.eq(selectedCity.getId())).list();
        if (mCountyList.size() > 0){
            dataList.clear();
            for (County county: mCountyList){
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            int cityCode = selectedCity.getCityCode();
            String address = "http://guolin.tech/api/china/"+provinceCode+"/"+cityCode;
            queryFromServer(address,"county");
        }
    }

    private void queryFromServer(String address,final String type) {
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                boolean result = false;
                if("province".equals(type)){
                    result = Utility.handleProvinceResponse(responseText);
                } else if ("city".equals(type)){
                    result = Utility.handleCityResponse(responseText,selectedProvince.getId());
                } else if ("county".equals(type)){
                    result = Utility.handleCountyResponse(responseText,selectedCity.getId());
                }
                if (result){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            if ("province".equals(type)){
                                queryProvices();
                            } else if ("city".equals(type)){
                                queryCities();
                            } else if ("county".equals(type)){
                                queryCounties();
                            }
                        }
                    });
                }
            }
        });
    }

    private void closeProgressDialog() {
        if (mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
         if (mProgressDialog == null){
             mProgressDialog = new ProgressDialog(getActivity());
             mProgressDialog.setMessage("正在加载...");
             mProgressDialog.setCanceledOnTouchOutside(false);
         }
        mProgressDialog.show();
    }

}
