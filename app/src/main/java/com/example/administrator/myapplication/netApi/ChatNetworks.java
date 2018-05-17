package com.example.administrator.myapplication.netApi;

import android.support.annotation.StringRes;

import com.example.administrator.myapplication.bean.ChatReceiveInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/5/17.
 */

public interface ChatNetworks {
    @POST("weather/city?appid=392737cc72dd689fc7ed2ab9bf0e5212")
    Call<ChatReceiveInfo> sendMessage(@Query("city_name") String cityName );

}
