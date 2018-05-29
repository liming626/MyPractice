package com.example.administrator.myapplication.utils;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2018/5/17.
 */

public class RetrofitUtils {
    private static Retrofit singleton;

    public static <T> T createRetrofit(Context context, Class<T> clazz) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
                    okHttpClientBuilder.addInterceptor(new HeaderInterceptor());
                    okHttpClientBuilder.addInterceptor(logging);
                    okHttpClientBuilder.readTimeout(15L, TimeUnit.SECONDS);
                    OkHttpClient client = okHttpClientBuilder.build();
                    Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
                    retrofitBuilder.baseUrl("http://api.shenjian.io/");
                    retrofitBuilder.addConverterFactory(ScalarsConverterFactory.create());
                    retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
                    retrofitBuilder.client(client);
                    singleton = retrofitBuilder.build();
                }
            }
        }

        return singleton.create(clazz);
    }
    private static class HeaderInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            String token = "";
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder();
            request = requestBuilder
                    .addHeader("Authorization", token)
                    .build();
            return chain.proceed(request);
        }
    }
}
